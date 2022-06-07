package model;

import util.UtilServiceLoadBalancer;

public class ServiceLoadBalancer {

	private int port;
	private int nodePort;
	private DeploymentApp depApp;
	
	/*
	 * Constructeur avec tous les param�tres donn�s
	 */
	public ServiceLoadBalancer(DeploymentApp depApp, int port, int nodePort) {
		this.depApp=depApp;
		this.port=port;
		this.nodePort=nodePort;
	}
	
	/*
	 * Constructeur avec un NodePort par d�faut 31285
	 */
	public ServiceLoadBalancer(DeploymentApp depApp, int port) {
		this(depApp, port, 31285);
		
	}
	
	public void createServiceLoadBalancerFile() {

		if(depApp!=null) {
			boolean isCreateFile=UtilServiceLoadBalancer.createServiceLoadBalancerFile(depApp.getNomDeploiement(), port, nodePort);
			
			if(isCreateFile)
				System.out.println("Fichier serviceLoadBalancer.yaml cr�er avec succ�s");
			
			else System.out.println("Erreur de cr�ation du Fichier serviceLoadBalancer.yaml, v�rifiez les droits d'�criture");
		}
		
		else System.out.println("L'objet DeploymentApp est null");
		
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
	
}
