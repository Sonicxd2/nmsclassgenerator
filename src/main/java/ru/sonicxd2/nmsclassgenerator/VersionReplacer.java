package ru.sonicxd2.nmsclassgenerator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class VersionReplacer {
    public static File createReplacedVersionFile(File classFile, String original, String newVersion) throws IOException {
        String originalClassName = classFile.getName().split("\\.")[0];
        String newClassName = originalClassName + "_" + newVersion;
        File dir = new File(newVersion);
        dir.mkdir();
        File modifiedClassFile = new File(newVersion, originalClassName + "_" + newVersion + ".java");

        modifiedClassFile.createNewFile();
        try (Scanner sc = new Scanner(classFile)) {
            try (PrintWriter writer = new PrintWriter(modifiedClassFile)) {
                String firstLine = sc.nextLine();
                if (firstLine.contains("package")) {
                    firstLine = firstLine.replace(";", "." + newVersion + ";");
                }
                writer.println(firstLine);
                while (sc.hasNextLine()) {
                    writer.println(sc.nextLine()
                            .replaceAll(original, newVersion)
                            .replaceAll(originalClassName, newClassName));
                }
            }
        }
        return modifiedClassFile;
    }
}
