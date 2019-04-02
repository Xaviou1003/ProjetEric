package com.cnam.fichier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Logger {
	
	private static Path logFile = null;
	
	public static void init(){
		try {
			logFile = Paths.get(System.getProperty("user.home"), "traitement.log");
			Files.deleteIfExists(logFile);
			Files.createFile(logFile);
		} catch (IOException e) {
			System.err.println("Impossible de creer le fichier de log " + e.getMessage());
		}
	}
	
	public static void info(String message){
		try {
			message = message + "\n";
			Files.write(logFile, message.getBytes("UTF-8"), StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.err.println("Impossible d'écrire dans le fichier de log " + e.getMessage());
		}
	}

}
