package generateConfigurationFiles;

public class GenerateConfigurationFilesApp {

	public static void main(String[] args) {

		//Création d'un fichier de configuration secret.yml
		//Paramètres: nom du secret et le mot de passe 
		GenerateConfigurationFiles.createSecretFile("mysql-secret", "test1234");
		
		
		//Création d'un fichier de configuration storage.yaml
		//Paramètres: nom du volume, nom du volume claim, capacité et répertoire de stockage
		GenerateConfigurationFiles.createStorageFile("mysql-pv-volume", "mysql-pv-claim", "20Gi", "/mnt/data");
		
		//Création d'un fichier de configuration deploymentDB.yaml => Pour les bases de données
		//Paramètres: nom du déploiement,nom de image, nom du secret, répertoire de stockage, port, nom du volume claim
		GenerateConfigurationFiles.createDeploymentDBFile("mysql", "mysql:5.7", "mysql-secret", "/var/lib/mysql", 3306, "mysql-pv-claim");
		
		//Création d'un fichier de configuration deploymentApp.yaml => Pour les applications avec un replicas par défaut: 1
		//Paramètres: nom du déploiement et nom de image
		GenerateConfigurationFiles.createDeploymentAppFile("my-service", "efrei/my-service:latest");
		
		//Création d'un fichier de configuration deploymentApp.yaml => Pour les applications avec un nombre de replicas donné
		//Paramètres: nom du déploiement, nom de image et le nombre de replicas
		GenerateConfigurationFiles.createDeploymentAppFile("my-service", "efrei/my-service:latest", 2);	
		
		//Création d'un fichier de configuration serviceClusterIp.yaml
		//Paramètres: nom du service et port
		GenerateConfigurationFiles.createServiceClusterIpFile("mysql", 3306);
		
		//Création d'un fichier de configuration serviceClusterNodePort.yaml avec un Nodeport par défaut: 31281
		//Paramètres: nom du service et port
		GenerateConfigurationFiles.createServiceNodePortFile("mysql", 3306);
		
		//Création d'un fichier de configuration serviceClusterNodePort.yaml avec un NodePort donné
		//Paramètres: nom du service, port et nodePort
		GenerateConfigurationFiles.createServiceNodePortFile("mysql", 3306, 31283);
		
		
		//Création d'un fichier de configuration serviceLoadBalancer.yaml avec un Nodeport par défaut: 31285
		//Paramètres: nom du service et port
		GenerateConfigurationFiles.createServiceLoadBalancerFile("my-service", 8080);
		
		//Création d'un fichier de configuration serviceClusterNodePort.yaml avec un NodePort donné
		//Paramètres: nom du service, port et nodePort
		GenerateConfigurationFiles.createServiceLoadBalancerFile("my-service", 8080,31289);
		
		//Création d'un fichier  Dockerfile avec une version de java par défaut: 8
		//Paramètres: nom du jar et port
		GenerateConfigurationFiles.createDockerFile("MyService-0.0.1-SNAPSHOT.jar", 8080);
		
		//Création d'un fichier de configuration Dockerfile avec une version de java donnée
		//Paramètres: nom du jar, port et une version de java
		GenerateConfigurationFiles.createDockerFile("MyService-0.0.1-SNAPSHOT.jar", 8080, 11);
		
		//Création d'un fichier  Dockerfile avec une version de java et un port par défaut: 8 et 8080
		//Paramètres: nom du jar
		GenerateConfigurationFiles.createDockerFile("MyService-0.0.1-SNAPSHOT.jar");
		
		//Création d'un fichier de configuration Dockerfile avec une version de java donnée et un port port par défaut
		//Paramètres: Une version de java et nom du jar
		GenerateConfigurationFiles.createDockerFile(11,"MyService-0.0.1-SNAPSHOT.jar");
		
	}

}
