package generateConfigurationFiles;

import util.UtilDeploymentApp;
import util.UtilDeploymentDB;
import util.UtilDockerFile;
import util.UtilSecret;
import util.UtilServiceClusterIp;
import util.UtilServiceLoadBalancer;
import util.UtilServiceNodePort;
import util.UtilStorage;

public class GenerateConfigurationFiles {

	public static void createDeploymentDBFile(String nom,String image,String secretName,String mountPath, int port, String claimName) {
		
		boolean isCreateFile=UtilDeploymentDB.createDeploymentDBFile(nom, image,secretName, mountPath, port, claimName);
		
		if(isCreateFile)
			System.out.println("Fichier deploymentDB.yaml créer avec succès");
		
		else System.out.println("Erreur de création du Fichier deploymentDB.yaml, vérifiez les droits d'écriture");		
		
	}
	
	public static void createDeploymentAppFile(String nom, String image, int nbReplicas) {
		
		boolean isCreateFile=UtilDeploymentApp.createDeploymentAppFile(nom, image, nbReplicas);
		
		if(isCreateFile)
			System.out.println("Fichier deploymentApp.yaml créer avec succès");
		
		else System.out.println("Erreur de création du Fichier deploymentApp.yaml, vérifiez les droits d'écriture");	
	}
	
	public static void createDeploymentAppFile(String nom, String image) {
		
		int defaultNbReplicas=1;
		
		createDeploymentAppFile(nom, image, defaultNbReplicas);
	}
	
	public static void createSecretFile(String nom, String password) {
		
		boolean isCreateFile=UtilSecret.createSecretFile(nom, password);
		
		if(isCreateFile)
			System.out.println("Fichier secret.yaml créer avec succès");
		
		else System.out.println("Erreur de création du Fichier secret.yaml, vérifiez les droits d'écriture");
		
	}
	
	public static void createServiceNodePortFile(String nom, int port, int nodePort) {

		boolean isCreateFile=UtilServiceNodePort.createServiceNodePortFile(nom, port, nodePort);
		
		if(isCreateFile)
			System.out.println("Fichier serviceNodePort.yaml créer avec succès");
		
		else System.out.println("Erreur de création du Fichier serviceNodePort.yaml, vérifiez les droits d'écriture");
		
	}
	
	public static void createServiceNodePortFile(String nom, int port) {
		
		int defaultNodePort=31281;
		
		createServiceNodePortFile(nom, port, defaultNodePort);
		
	}
	
	public static void createServiceClusterIpFile(String nom, int port) {
		
		boolean isCreateFile=UtilServiceClusterIp.createServiceClusterIpFile(nom, port);
		
		if(isCreateFile)
			System.out.println("Fichier serviceClusterIp.yaml créer avec succès");
		
		else System.out.println("Erreur de création du Fichier serviceClusterIp.yaml, vérifiez les droits d'écriture");
		
	}
	
	public static void createServiceLoadBalancerFile(String nom, int port, int nodePort) {

		boolean isCreateFile=UtilServiceLoadBalancer.createServiceLoadBalancerFile(nom, port, nodePort);
		
		if(isCreateFile)
			System.out.println("Fichier serviceLoadBalancer.yaml créer avec succès");
		
		else System.out.println("Erreur de création du Fichier serviceLoadBalancer.yaml, vérifiez les droits d'écriture");
		
	}
	
	public static void createServiceLoadBalancerFile(String nom, int port) {
		
		int defaultNodePort=31285;
		
		createServiceLoadBalancerFile(nom, port, defaultNodePort);
		
	}	
	
	public static void createStorageFile(String persistentVolumeName,String persistentVolumeClaimName,String capacite,String hostPath) {
	
		boolean isCreateFile=UtilStorage.createStorageFile(persistentVolumeName, persistentVolumeClaimName, capacite, hostPath);
		
		if(isCreateFile)
			System.out.println("Fichier storage.yaml créer avec succès");
		
		else System.out.println("Erreur de création du Fichier storage.yaml, vérifiez les droits d'écriture");
		
	}
	
	public static void createDockerFile(String nomJar, int port, int javaVersion) {
		
		boolean isCreateFile=UtilDockerFile.createDockerFile(nomJar,port, javaVersion);
		
		if(isCreateFile)
			System.out.println("Fichier Dockerfile créer avec succès");
		
		else System.out.println("Erreur de création du Fichier Dockerfile, vérifiez les droits d'écriture");
		
	}
	
	public static void createDockerFile(String nomJar, int port) {
		
		int defaultJavaVersion=8;
		
		createDockerFile(nomJar,port, defaultJavaVersion);
	}
	
	public static void createDockerFile(String nomJar) {
	
		int defaulPort=8080;
		
		createDockerFile(nomJar, defaulPort);
	}
	
	public static void createDockerFile(int javaVersion,String nomJar) {
		
		int defaulPort=8080;
		
		createDockerFile(nomJar, defaulPort, javaVersion); 

	}
	
}
