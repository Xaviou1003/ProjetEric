package com.cnam.domaine;

import com.cnam.fichier.Logger;

public class Utilisateur {
	int id;
	String nom = "";
	String prenom = "";
	String email = "";
	String sexe = "";

	public Utilisateur(int id, String[] userInfos) {
		super();
		this.id = id;
		this.email = userInfos[0];
		this.nom = userInfos[1];
		this.prenom = userInfos[2];
		if(userInfos.length > 3)this.sexe = userInfos[3];
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public boolean isValid(){
		if(this.nom.isEmpty() || this.prenom.isEmpty() || this.sexe.isEmpty() || this.email.isEmpty()){
			Logger.info("Ligne " + id + ": rejetée: format incorrecte: manque un élèment dans la ligne");
			return false;
		}
		if(!"M".equals(this.sexe) && !"F".equals(this.sexe)){
			Logger.info("Ligne " + id + " rejetée: la valeur de sexe est incorrecte => valeur autorisée (M ou F)");
			return false;
		}
		return true;
	}

}
