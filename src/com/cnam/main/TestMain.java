package com.cnam.main;

import com.cnam.da.ServiceUtilisateur;
import com.cnam.domaine.Utilisateur;
import com.cnam.fichier.Lecture;
import com.cnam.fichier.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TestMain {


	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	public static void main(String[] args) {

		Logger.init();
		String fileName = "Utilisateurs.csv";

		Logger.info("Traitement insertion utilisateur");
		Lecture lecture = new Lecture();
		Logger.info("Source: " + fileName);
		Logger.info("Début traitement " + LocalDateTime.now().format(formatter));
		List<Utilisateur> csvUtilisateurs = lecture.lireFichierCsv(fileName);

		ServiceUtilisateur serviceUtilisateur = new ServiceUtilisateur();
		//serviceUtilisateur.initDB();
		int totalUtilisateur = csvUtilisateurs.size() - 1;
		int numberUtilisateurInsere = 0;
		Logger.info("Ligne 1: ignorée");
		for(int i = 1; i < totalUtilisateur + 1; i++) {
			Utilisateur u = csvUtilisateurs.get(i);
			if (u.isValid()) {
				int rowsUpdated = serviceUtilisateur.insererUtilisateur(u);
				numberUtilisateurInsere += rowsUpdated;
			}
		}
		Logger.info("");
		Logger.info("Nombre de lignes insérées: " + numberUtilisateurInsere);
		Logger.info("Nombre de lignes rejetées: " + (totalUtilisateur - numberUtilisateurInsere));
		Logger.info("Total lignes traitées: " + totalUtilisateur);
		Logger.info("");
		Logger.info("Fin traitement " + LocalDateTime.now().format(formatter));
	}

}
