package model;

import util.UtilDeploymentApp;

public class DeploymentApp {
	
	private String nomDeploiement;
	private String nomImage;
	private int nbReplicas;
	
	
	public DeploymentApp() {}
	
	/*
	 * Constructeur avec un replicas donn� en param�tre
	 */
	public DeploymentApp(String nomDeploiement, String nomImage, int nbReplicas) {
		this.nomDeploiement=nomDeploiement;
		this.nomImage=nomImage;
		this.nbReplicas=nbReplicas;
	}
	
	/*
	 * Constructeur avec un replicas � 1
	 */
	public DeploymentApp(String nomDeploiement, String nomImage) {
		this(nomDeploiement, nomImage, 1);
	}
	
	
	public void createDeploymentAppFile() {
		
		boolean isCreateFile=UtilDeploymentApp.createDeploymentAppFile(nomDeploiement, nomImage, nbReplicas);
		
		if(isCreateFile)
			System.out.println("Fichier deploymentApp.yaml cr�er avec succ�s");
		
		else System.out.println("Erreur de cr�ation du Fichier deploymentApp.yaml, v�rifiez les droits d'�criture");	
	}

	public String getNomDeploiement() {
		return nomDeploiement;
	}

	public void setNomDeploiement(String nomDeploiement) {
		this.nomDeploiement = nomDeploiement;
	}

	public String getNomImage() {
		return nomImage;
	}

	public void setNomImage(String nomImage) {
		this.nomImage = nomImage;
	}

	public int getNbReplicas() {
		return nbReplicas;
	}

	public void setNbReplicas(int nbReplicas) {
		this.nbReplicas = nbReplicas;
	}
	
}
