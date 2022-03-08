package generateConfigurationFiles;

import model.DeploymentApp;
import model.DeploymentDB;
import model.Docker;
import model.Secret;
import model.ServiceClusterIp;
import model.ServiceLoadBalancer;
import model.ServiceNodePort;
import model.Storage;

public class GenerateConfigurationFilesApp {

	public static void main(String[] args) {

		//Cr�ation d'un fichier de configuration secret.yml
		//Param�tres: nom du secret et le mot de passe 
		Secret secret=new Secret("mysql-secret", "test1234");
		secret.createSecretFile();
		
		
		//Cr�ation d'un fichier de configuration storage.yaml
		//Param�tres: nom du volume, nom du volume claim, capacit� et r�pertoire de stockage
		Storage storage=new Storage("mysql-pv-volume", "mysql-pv-claim", "20Gi", "/mnt/data");
		storage.createStorageFile();
		
		
		//Cr�ation d'un fichier de configuration deploymentDB.yaml => Pour les bases de donn�es
		//Param�tres: nom du d�ploiement,nom de image,instance de la classe Secret, r�pertoire de stockage, port, instance de la classe Storage
		DeploymentDB depDB=new DeploymentDB("mysql", "mysql:5.7", secret, "/var/lib/mysql", 3306, storage);
		depDB.createDeploymentDBFile();
		
		
		//Cr�ation d'un fichier de configuration deploymentApp.yaml => Pour les applications avec un replicas par d�faut: 1
		//Param�tres: nom du d�ploiement et nom de image
		DeploymentApp depApp=new DeploymentApp("my-service", "efrei/my-service:latest");
		depApp.createDeploymentAppFile();
		
		
		//Cr�ation d'un fichier de configuration deploymentApp.yaml => Pour les applications avec un nombre de replicas donn�
		//Param�tres: nom du d�ploiement, nom de image et le nombre de replicas
		DeploymentApp depApp2=new DeploymentApp("my-service", "efrei/my-service:latest",2);
		depApp2.createDeploymentAppFile();
		
		
		//Cr�ation d'un fichier de configuration serviceClusterIp.yaml pour les applications
		//Param�tres: instance de la classe DeploymentApp et port
		ServiceClusterIp serviceClusterIpApp=new ServiceClusterIp(depApp, 3306);
		serviceClusterIpApp.createServiceClusterIpFileApp();
		
		
		//Cr�ation d'un fichier de configuration serviceClusterIp.yaml pour les bases de donn�es
		//Param�tres: instance de la classe DeploymentDB et port
		ServiceClusterIp serviceClusterIpDB=new ServiceClusterIp(depDB, 3306);
		serviceClusterIpDB.createServiceClusterIpFileApp();
		
		
		//Cr�ation d'un fichier de configuration serviceNodePort.yaml avec un Nodeport par d�faut: 31281 pour les applications
		//Param�tres: instance de la classe DeploymentApp et port
		ServiceNodePort serviceNodePortApp=new ServiceNodePort(depApp2, 3306);
		serviceNodePortApp.createServiceNodePortFileApp();
		
		
		//Cr�ation d'un fichier de configuration serviceNodePort.yaml avec un NodePort donn� pour les applications
		//Param�tres: instance de la classe DeploymentApp, port et nodePort
		ServiceNodePort serviceNodePortApp2=new ServiceNodePort(depApp2, 3306, 31283);
		serviceNodePortApp2.createServiceNodePortFileApp();
	

		//Cr�ation d'un fichier de configuration serviceNodePort.yaml avec un Nodeport par d�faut: 31281 pour les DB
		//Param�tres: instance de la classe DeploymentDB et port
		ServiceNodePort serviceNodePortDB=new ServiceNodePort(depDB, 3306);
		serviceNodePortDB.createServiceNodePortFileDB();
		
		
		//Cr�ation d'un fichier de configuration serviceNodePort.yaml avec un NodePort donn� pour les DB
		//Param�tres: instance de la classe DeploymentDB, port et nodePort
		ServiceNodePort serviceNodePortDB2=new ServiceNodePort(depDB, 3306, 31283);
		serviceNodePortDB2.createServiceNodePortFileDB();
		
		
		//Cr�ation d'un fichier de configuration serviceLoadBalancer.yaml avec un Nodeport par d�faut: 31285 pour les applications
		//Param�tres: instance de la classe DeploymentApp et port
		ServiceLoadBalancer serviceLoadBalancerApp=new ServiceLoadBalancer(depApp2, 8080);
		serviceLoadBalancerApp.createServiceLoadBalancerFile();
		
		
		//Cr�ation d'un fichier de configuration serviceClusterNodePort.yaml avec un NodePort donn� pour les applications
		//Param�tres: instance de la classe DeploymentApp, port et nodePort
		ServiceLoadBalancer serviceLoadBalancerApp2=new ServiceLoadBalancer(depApp2, 8080, 31289);
		serviceLoadBalancerApp2.createServiceLoadBalancerFile();
		
		
		//Cr�ation d'un fichier  Dockerfile avec une version de java par d�faut: 8
		//Param�tres: nom du jar et port
		Docker dockerfile1=new Docker("MyService-0.0.1-SNAPSHOT.jar", 8080);
		dockerfile1.createDockerFile();
		
		
		//Cr�ation d'un fichier de configuration Dockerfile avec une version de java donn�e
		//Param�tres: nom du jar, port et une version de java
		Docker dockerfile2=new Docker("MyService-0.0.1-SNAPSHOT.jar", 8080, 11);
		dockerfile2.createDockerFile();
		
		
		//Cr�ation d'un fichier  Dockerfile avec une version de java et un port par d�faut: 8 et 8080
		//Param�tres: nom du jar
		Docker dockerfile3=new Docker("MyService-0.0.1-SNAPSHOT.jar");
		dockerfile3.createDockerFile();
		
		
		//Cr�ation d'un fichier de configuration Dockerfile avec une version de java donn�e et un port par d�faut 8080
		//Param�tres: Une version de java et nom du jar
		Docker dockerfile4=new Docker(11,"MyService-0.0.1-SNAPSHOT.jar");
		dockerfile4.createDockerFile();
		
	}

}
