package com.cnam.da;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cnam.domaine.Utilisateur;
import com.cnam.fichier.Logger;

public class ServiceUtilisateur {

	private Connection connection;
	
	public ServiceUtilisateur(){
		this.connection = Connexion.laConnexion();
		//this.initDB();
	}

	/*private void initDB() {
		try (Statement statement = this.connection.createStatement()) {
			//prep.setString(1, "Utilisateurs");
			statement.execute("DELETE FROM Utilisateurs WHERE ID >= 1");
		}catch (SQLException ex) {
			System.err.println("SQLException: "+ ex.getMessage());
			System.err.println("SQLState: "+ ex.getSQLState());
			System.err.println("VendorError: "+ ex.getErrorCode());
		}
	}*/
	
	public int insererUtilisateur(Utilisateur utilisateur) {
		String userEmail = utilisateur.getEmail();
		if (utilisateurExist(userEmail)) {
			Logger.info("Ligne " + utilisateur.getId() + ": rejetée : cause : l'email " + userEmail.split("@")[0] + " existe déjà");
		} else {
			try(PreparedStatement prep = this.connection.prepareStatement("INSERT into Utilisateur(id,nom,prenom,email,sexe) values(?,?,?,?,?)")) {
				prep.setInt(1, utilisateur.getId());
				prep.setString(2, utilisateur.getNom());
				prep.setString(3, utilisateur.getPrenom());
				prep.setString(4, utilisateur.getEmail());
				prep.setString(5, utilisateur.getSexe());

				int result = prep.executeUpdate();//pour executer les nouveau ajouts
				if (result > 0) {
					Logger.info("Ligne " + utilisateur.getId() + ": insérée");
				}
				return result;
				//prep.close();
			} catch (SQLException ex) {
				System.err.println("SQLException: " + ex.getMessage());
			}
		}
		return 0;
	}

	private boolean utilisateurExist(String email){
		boolean result = false;
		try(PreparedStatement prep = this.connection.prepareStatement("select * from Utilisateur where email=?")) {
			prep.setString(1, email);
			ResultSet resultat = prep.executeQuery();
			result = resultat.next();
		}catch (SQLException ex){
			System.err.println("SQLException: "+ ex.getMessage());
		}
		return result;
	}
}
