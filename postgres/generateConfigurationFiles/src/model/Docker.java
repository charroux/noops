package model;

import util.UtilDockerFile;

public class Docker {

	private String nomJar;
	private int port;
	private int javaVersion;
	
	/*
	 * Constructeur avec tous les param�tres donn�s 
	 */
	public Docker(String nomJar, int port, int javaVersion) {
		this.nomJar=nomJar;
		this.port=port;
		this.javaVersion=javaVersion;
	}
	
	/*
	 * Constructeur avec une version de java par d�faut 8 
	 */
	public Docker(String nomJar, int port) {
		this(nomJar, port, 8);
	}
	
	/*
	 * Constructeur avec une version de java par d�faut 8 et un port port par d�faut 8080
	 */
	public Docker(String nomJar) {
	  this(nomJar,8080);	
	}
	
	/*
	 * Constructeur avec un port par d�faut 8080 
	 */
	public Docker(int javaVersion,String nomJar) {
		this(nomJar,8080,javaVersion);
		
	}
	
	public void createDockerFile() {
		
		boolean isCreateFile=UtilDockerFile.createDockerFile(nomJar,port, javaVersion);
		
		if(isCreateFile)
			System.out.println("Fichier Dockerfile cr�er avec succ�s");
		
		else System.out.println("Erreur de cr�ation du Fichier Dockerfile, v�rifiez les droits d'�criture");
		
	}
}
