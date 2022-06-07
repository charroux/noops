package model;

import util.UtilServiceClusterIp;

public class ServiceClusterIp {

	private int port;
	private DeploymentApp depApp;
	private DeploymentDB depDB;
	
	
	public ServiceClusterIp() {
		depApp=new DeploymentApp();
		depDB= new DeploymentDB();
	}
	
	/*
	 * Constructeur avec une instance de la classe DeploymentApp(applications)
	 */
	public ServiceClusterIp(DeploymentApp depApp, int port) {
		this.depApp=depApp;
		this.port=port;
	}
	
	/*
	 * Constructeur avec une instance de la classe DeploymentBD(bases de donn�es)
	 */
	public ServiceClusterIp(DeploymentDB depDB, int port) {
		this.depDB=depDB;
		this.port=port;
	}
	
	public void createServiceClusterIpFileApp() {
		
		if(depApp!=null) {
		
			boolean isCreateFile=UtilServiceClusterIp.createServiceClusterIpFile(depApp.getNomDeploiement(), port);
			
			if(isCreateFile)
				System.out.println("Fichier serviceClusterIp.yaml cr�er avec succ�s");
			
			else System.out.println("Erreur de cr�ation du Fichier serviceClusterIp.yaml, v�rifiez les droits d'�criture");
		}
		
		else System.out.println("L'objet DeploymentApp est null");
		
	}
	
	public void createServiceClusterIpFileDB() {
		
		if(depDB!=null) {
			boolean isCreateFile=UtilServiceClusterIp.createServiceClusterIpFile(depDB.getNomDeploiement(), port);
			
			if(isCreateFile)
				System.out.println("Fichier serviceClusterIp.yaml cr�er avec succ�s");
			
			else System.out.println("Erreur de cr�ation du Fichier serviceClusterIp.yaml, v�rifiez les droits d'�criture");
			
		}
		else System.out.println("L'objet DeploymentDB est null");
		
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public DeploymentApp getDepApp() {
		return depApp;
	}

	public void setDepApp(DeploymentApp depApp) {
		this.depApp = depApp;
	}

	public DeploymentDB getDepDB() {
		return depDB;
	}

	public void setDepDB(DeploymentDB depDB) {
		this.depDB = depDB;
	}
	
}
