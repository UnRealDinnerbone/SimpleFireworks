package com.unrealdinnerbone.simplefireworks.network;

import com.unrealdinnerbone.simplefireworks.SimpleFirework;
import com.unrealdinnerbone.simplefireworks.parsar.FireworkParser;
import com.unrealdinnerbone.yaum.api.firework.FireworkObject;
import com.unrealdinnerbone.yaum.api.network.ISimplePacket;
import com.unrealdinnerbone.yaum.libs.utils.FacingUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class PacketSpawnFireworkObject implements ISimplePacket<PacketSpawnFireworkObject> {

    private CharSequence charSequence;
    private BlockPos blockPos;
    private EnumFacing facing;

    private boolean isGood;
    private FireworkObject object;

    public PacketSpawnFireworkObject() {

    }


    public PacketSpawnFireworkObject(CharSequence objectName, BlockPos pos, EnumFacing facing) {
        this.charSequence = objectName;
        this.blockPos = pos;
        this.facing = facing;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int length = buf.readInt();
        charSequence = buf.readCharSequence(length, StandardCharsets.UTF_8);
        for (FireworkObject fireworkObject : SimpleFirework.getObjectParser().getFireworkObjects()) {
            if (fireworkObject.getID().equalsIgnoreCase(String.valueOf(charSequence))) {
                this.object = fireworkObject;
            }
        }
        isGood = object != null;
        this.blockPos = new BlockPos(buf.readInt(),buf.readInt(),buf.readInt());
        this.facing = FacingUtil.getFacingFormID(buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(charSequence.length());
        buf.writeCharSequence(charSequence, StandardCharsets.UTF_8);
        buf.writeInt(blockPos.getX());
        buf.writeInt(blockPos.getY());
        buf.writeInt(blockPos.getZ());
        buf.writeInt(facing.getIndex());
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public FireworkObject getObject() {
        return object;
    }

    public boolean isGood() {
        return isGood;
    }

    public EnumFacing getFacing() {
        return facing;
    }

    @Override
    public IMessage onMessage(PacketSpawnFireworkObject message, MessageContext ctx) {
        if (message.isGood()) {
            EnumFacing facing = message.getFacing();
            FireworkObject fireworkObject = message.getObject();
            BlockPos pos = message.getBlockPos().add(0, fireworkObject.getObjectArray().length * 5, 0);

            for (String[] row : fireworkObject.getObjectArray()) {
                pos = pos.add(0, -5, 0);
                for (String key : row) {
                    String fireworkName = fireworkObject.getFireworkNameFormIdentifier(key);
                    if(fireworkName != null) {
                        if(SimpleFirework.getFireworkParser().getFireworkObjects().containsKey(fireworkName)) {
                            FireworkParser.FireworkWrapper wrapper = SimpleFirework.getFireworkParser().getFireworkObjects().get(fireworkName);
                            wrapper.spawnAllFireworks(pos, 0, 0, 0);
                        }else {
                            //Todo Better Message
                            SimpleFirework.LOG_HELPER.error("Firework is not there " + fireworkName);
                        }
                    } else {

                        //Todo Better Message for when key is set but no identifer found
                        SimpleFirework.LOG_HELPER.debug("No ");
                    }
                    pos = pos.add(facing.getFrontOffsetX() * 5, 0, facing.getFrontOffsetZ() * 5);
                }
                pos = pos.add(row.length * -5 * facing.getFrontOffsetX(), 0, row.length * -5 * facing.getFrontOffsetZ());
            }
        } else {
            ctx.getClientHandler().handleChat(new SPacketChat(new TextComponentString("Error Test")));
        }
        return null;
    }
}
