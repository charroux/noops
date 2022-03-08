package model;

import util.UtilSecret;

public class Secret {

	private String nomSecret;
	private String password;
	
	public Secret(String nomSecret, String password) {
		this.nomSecret=nomSecret;
		this.password=password;
	}

	public void createSecretFile() {
		
		boolean isCreateFile=UtilSecret.createSecretFile(nomSecret, password);
		
		if(isCreateFile)
			System.out.println("Fichier secret.yaml créer avec succès");
		
		else System.out.println("Erreur de création du Fichier secret.yaml, vérifiez les droits d'écriture");
		
	}
	
	public String getNomSecret() {
		return nomSecret;
	}

	public void setNom(String nomSecret) {
		this.nomSecret = nomSecret;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
