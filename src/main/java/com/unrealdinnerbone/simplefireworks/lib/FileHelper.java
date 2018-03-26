package com.unrealdinnerbone.simplefireworks.lib;

import com.unrealdinnerbone.simplefireworks.parsar.ObjectParser;
import net.minecraft.world.storage.ThreadedFileIOBase;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileHelper
{

    private static Charset UTF_8 = Charset.forName("utf-8");


    public static void writeStringToFileThered(String info, File file) {
        ThreadedFileIOBase.getThreadedIOInstance().queueIO(() -> {
            try {
                FileUtils.writeStringToFile(file, info, UTF_8);
            } catch (IOException ioException ) {

            }
            return false;
        });
    }
    public static void writeStringToFile(String info, File file) {
        try {
            FileUtils.writeStringToFile(file, info, UTF_8);
        } catch (IOException ioException) {

        }
    }
}
