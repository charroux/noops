package generateConfigurationFiles;

public class GenerateConfigurationFilesApp {

	public static void main(String[] args) {

		//Cr�ation d'un fichier de configuration secret.yml
		//Param�tres: nom du secret et le mot de passe 
		GenerateConfigurationFiles.createSecretFile("mysql-secret", "test1234");
		
		
		//Cr�ation d'un fichier de configuration storage.yaml
		//Param�tres: nom du volume, nom du volume claim, capacit� et r�pertoire de stockage
		GenerateConfigurationFiles.createStorageFile("mysql-pv-volume", "mysql-pv-claim", "20Gi", "/mnt/data");
		
		//Cr�ation d'un fichier de configuration deploymentDB.yaml => Pour les bases de donn�es
		//Param�tres: nom du d�ploiement,nom de image, nom du secret, r�pertoire de stockage, port, nom du volume claim
		GenerateConfigurationFiles.createDeploymentDBFile("mysql", "mysql:5.7", "mysql-secret", "/var/lib/mysql", 3306, "mysql-pv-claim");
		
		//Cr�ation d'un fichier de configuration deploymentApp.yaml => Pour les applications avec un replicas par d�faut: 1
		//Param�tres: nom du d�ploiement et nom de image
		GenerateConfigurationFiles.createDeploymentAppFile("my-service", "efrei/my-service:latest");
		
		//Cr�ation d'un fichier de configuration deploymentApp.yaml => Pour les applications avec un nombre de replicas donn�
		//Param�tres: nom du d�ploiement, nom de image et le nombre de replicas
		GenerateConfigurationFiles.createDeploymentAppFile("my-service", "efrei/my-service:latest", 2);	
		
		//Cr�ation d'un fichier de configuration serviceClusterIp.yaml
		//Param�tres: nom du service et port
		GenerateConfigurationFiles.createServiceClusterIpFile("mysql", 3306);
		
		//Cr�ation d'un fichier de configuration serviceClusterNodePort.yaml avec un Nodeport par d�faut: 31281
		//Param�tres: nom du service et port
		GenerateConfigurationFiles.createServiceNodePortFile("mysql", 3306);
		
		//Cr�ation d'un fichier de configuration serviceClusterNodePort.yaml avec un NodePort donn�
		//Param�tres: nom du service, port et nodePort
		GenerateConfigurationFiles.createServiceNodePortFile("mysql", 3306, 31283);
		
		
		//Cr�ation d'un fichier de configuration serviceLoadBalancer.yaml avec un Nodeport par d�faut: 31285
		//Param�tres: nom du service et port
		GenerateConfigurationFiles.createServiceLoadBalancerFile("my-service", 8080);
		
		//Cr�ation d'un fichier de configuration serviceClusterNodePort.yaml avec un NodePort donn�
		//Param�tres: nom du service, port et nodePort
		GenerateConfigurationFiles.createServiceLoadBalancerFile("my-service", 8080,31289);
		
		//Cr�ation d'un fichier  Dockerfile avec une version de java par d�faut: 8
		//Param�tres: nom du jar et port
		GenerateConfigurationFiles.createDockerFile("MyService-0.0.1-SNAPSHOT.jar", 8080);
		
		//Cr�ation d'un fichier de configuration Dockerfile avec une version de java donn�e
		//Param�tres: nom du jar, port et une version de java
		GenerateConfigurationFiles.createDockerFile("MyService-0.0.1-SNAPSHOT.jar", 8080, 11);
		
		//Cr�ation d'un fichier  Dockerfile avec une version de java et un port par d�faut: 8 et 8080
		//Param�tres: nom du jar
		GenerateConfigurationFiles.createDockerFile("MyService-0.0.1-SNAPSHOT.jar");
		
		//Cr�ation d'un fichier de configuration Dockerfile avec une version de java donn�e et un port port par d�faut
		//Param�tres: Une version de java et nom du jar
		GenerateConfigurationFiles.createDockerFile(11,"MyService-0.0.1-SNAPSHOT.jar");
		
	}

}
