package com.bichotitas.votacionesmvc.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;

public class Logger {
    /* Remove this comment to add a custom route to the logger
    private final String filePath;

    public Logger(String filePath) {
        this.filePath = filePath;
    } */

    public static void log(String className, String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/log.txt", true))) {
            String messageToLog = className + " - " + message + " - " + TimeUtils.getDate() + " - " + TimeUtils.getTime();

            System.out.println("Logger: " + messageToLog);
            writer.write(messageToLog + "\n");
        } catch (IOException error) {
            System.out.println("Oh no! An error occurred while writing to the file " + error.getMessage());
        }
    }
}
