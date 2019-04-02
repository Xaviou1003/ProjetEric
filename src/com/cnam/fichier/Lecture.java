package com.cnam.fichier;
import com.cnam.domaine.Utilisateur;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lecture {

	public List<Utilisateur> lireFichierCsv(String fileName){
		try (Stream<String> streamLines = Files.lines(Paths.get(getClass().getClassLoader().getResource(fileName).toURI()))) {
			AtomicInteger count = new AtomicInteger(0);
			return streamLines
					.filter(l -> !l.isEmpty())
					.map(l -> new Utilisateur(count.incrementAndGet(), l.split(";")))
					.collect(Collectors.toList());

		} catch (IOException | URISyntaxException e) {
			System.err.println("erreur de lecture: " + e.getMessage());
		}
		return new ArrayList<>();
	}

}
