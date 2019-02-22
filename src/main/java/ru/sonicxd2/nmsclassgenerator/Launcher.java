package ru.sonicxd2.nmsclassgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Template: java -jar <name>.jar <class name> <versions to generate>");
            System.out.println("Example usage: java -jar nms-generator.jar ClassName.java 1_8_R1 1_8_R2 1_8_R3 1_11_R1");
            return;
        }
        File classFile = new File(args[0]);
        String[] versions = new String[args.length - 1];
        System.arraycopy(args, 1, versions, 0, args.length - 1);
        String version = VersionSearcher.searchVersion(classFile);
        System.out.println("Original version: " + version);
        System.out.println("Starting generating");
        System.out.println();
        for (String newVersion : versions) {
            File versionFile = VersionReplacer.createReplacedVersionFile(classFile, version, newVersion);
            System.out.println("Generated file: " + versionFile.getAbsolutePath());
            System.out.println();
        }
    }
}
