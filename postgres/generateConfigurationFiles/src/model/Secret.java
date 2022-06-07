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
			System.out.println("Fichier secret.yaml cr�er avec succ�s");
		
		else System.out.println("Erreur de cr�ation du Fichier secret.yaml, v�rifiez les droits d'�criture");
		
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
