package model;

import util.UtilServiceNodePort;

public class ServiceNodePort {
	
	private int port;
	private int nodePort;
	private DeploymentApp depApp;
	private DeploymentDB depDB;
	
	public ServiceNodePort() {
		depApp=new DeploymentApp();
		depDB= new DeploymentDB();
	}
	
	/*
	 * Constructeur avec une instance de la classe DeploymentApp(applications)
	 * Et tous les paramètres donnés
	 */
	public ServiceNodePort(DeploymentApp depApp, int port, int nodePort) {
		this.depApp=depApp;
		this.port=port;
		this.nodePort=nodePort;
	}
	
	/*
	 * Constructeur avec une instance de la classe DeploymentApp(applications)
	 * Et avec un NodePort par défaut 31281
	 */
	public ServiceNodePort(DeploymentApp depApp, int port) {
		this(depApp, port, 31281);
		
	}
	
	/*
	 * Constructeur avec une instance de la classe DeploymentDB(bases de données)
	 * Et tous les paramètres donnés
	 */
	public ServiceNodePort(DeploymentDB depDB, int port, int nodePort) {
		this.depDB=depDB;
		this.port=port;
		this.nodePort=nodePort;
	}
	
	/*
	 * Constructeur avec une instance de la classe DeploymentDB(bases de données)
	 * Et avec un NodePort par défaut 31290
	 */
	public ServiceNodePort(DeploymentDB depDB, int port) {
		this(depDB, port, 31290);
		
	}
	
	public void createServiceNodePortFileApp() {

		if(depApp!=null) {
			
			boolean isCreateFile=UtilServiceNodePort.createServiceNodePortFile(depApp.getNomDeploiement(), port, nodePort);
			
			if(isCreateFile)
				System.out.println("Fichier serviceNodePort.yaml créer avec succès");
			
			else System.out.println("Erreur de création du Fichier serviceNodePort.yaml, vérifiez les droits d'écriture");
		}
		else System.out.println("L'objet DeploymentApp est null");
		
	}
	
	public void createServiceNodePortFileDB() {

		if(depDB!=null) {
			boolean isCreateFile=UtilServiceNodePort.createServiceNodePortFile(depDB.getNomDeploiement(), port, nodePort);
			
			if(isCreateFile)
				System.out.println("Fichier serviceNodePort.yaml créer avec succès");
			
			else System.out.println("Erreur de création du Fichier serviceNodePort.yaml, vérifiez les droits d'écriture");
			
		}
		else System.out.println("L'objet DeploymentDB est null");
		
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getNodePort() {
		return nodePort;
	}

	public void setNodePort(int nodePort) {
		this.nodePort = nodePort;
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
