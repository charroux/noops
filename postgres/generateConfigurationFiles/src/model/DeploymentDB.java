package model;

import util.UtilDeploymentDB;

public class DeploymentDB {
	
	private String nomDeploiement;
	private String nomImage;
	private Secret secret;
	private String mountPath;
	private int port;
	private Storage storage;
	
	public DeploymentDB(){}
	
	public DeploymentDB(String nomDeploiement, String nomImage, Secret secret, String mountPath, int port, Storage storage) {
		this.nomDeploiement=nomDeploiement;
		this.nomImage=nomImage;
		this.secret=secret;
		this.mountPath=mountPath;
		this.port=port;
		this.storage=storage;
	}
	
	public void createDeploymentDBFile() {
		
		boolean isCreateFile=UtilDeploymentDB.createDeploymentDBFile(nomDeploiement, nomImage,secret.getNomSecret(), mountPath, port, storage.getPersistentVolumeClaimName());
		
		if(isCreateFile)
			System.out.println("Fichier deploymentDB.yaml créer avec succès");
		
		else System.out.println("Erreur de création du Fichier deploymentDB.yaml, vérifiez les droits d'écriture");		
		
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

	public Secret getSecret() {
		return secret;
	}

	public void setSecret(Secret secret) {
		this.secret = secret;
	}

	public String getMountPath() {
		return mountPath;
	}

	public void setMountPath(String mountPath) {
		this.mountPath = mountPath;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}
	

}
