package model;

import util.UtilStorage;

public class Storage {

	private String persistentVolumeName;
	private String persistentVolumeClaimName;
	private String capacite;
	private String hostPath;	
	
	public Storage(String persistentVolumeName, String persistentVolumeClaimName, String capacite, String hostPath) {
		this.persistentVolumeName=persistentVolumeName;
		this.persistentVolumeClaimName=persistentVolumeClaimName;
		this.capacite=capacite;
		this.hostPath=hostPath;
	}
	
	
	public void createStorageFile() {
		
		boolean isCreateFile=UtilStorage.createStorageFile(persistentVolumeName, persistentVolumeClaimName, capacite, hostPath);
		
		if(isCreateFile)
			System.out.println("Fichier storage.yaml cr�er avec succ�s");
		
		else System.out.println("Erreur de cr�ation du Fichier storage.yaml, v�rifiez les droits d'�criture");
		
	}


	public String getPersistentVolumeName() {
		return persistentVolumeName;
	}


	public void setPersistentVolumeName(String persistentVolumeName) {
		this.persistentVolumeName = persistentVolumeName;
	}


	public String getPersistentVolumeClaimName() {
		return persistentVolumeClaimName;
	}


	public void setPersistentVolumeClaimName(String persistentVolumeClaimName) {
		this.persistentVolumeClaimName = persistentVolumeClaimName;
	}


	public String getCapacite() {
		return capacite;
	}


	public void setCapacite(String capacite) {
		this.capacite = capacite;
	}


	public String getHostPath() {
		return hostPath;
	}


	public void setHostPath(String hostPath) {
		this.hostPath = hostPath;
	}
	
	
}
