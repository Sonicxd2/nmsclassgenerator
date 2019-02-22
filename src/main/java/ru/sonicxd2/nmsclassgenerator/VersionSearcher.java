package ru.sonicxd2.nmsclassgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VersionSearcher {
    public static String searchVersion(File classFile) throws FileNotFoundException {
        try (Scanner sc = new Scanner(classFile)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if ((line.startsWith("import net.minecraft.server")) || (line.startsWith("import org.bukkit.craftbukkit"))) {
                    return line.split("\\.")[3];
                }
            }
            throw new UnknownError("Version not found.");
        }
    }

}
