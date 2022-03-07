# Programme de génération des fichiers de configuration

Ce programme écrit en Java permet de générer des fichiers de configuration :
- Kubernetes 
- Docker

## Prérequis
Version de Java supérieure ou égale à 8.

## Description des fonctions 

### Création d'un fichier secret
 #### createSecretFile(nom du secret, mot de passe) : 
- Cette fonction permet de générer un fichier de configuration secret.yml
 
 Exemple : 
 
 ```bash
 GenerateConfigurationFiles.createSecretFile("mysql-secret", "test1234");
 ```
    

 
### Création d'un fichier de stockage
 #### createStorageFile(nom du volume, nom du volume claim, capacité, répertoire de stockage) : 
 - Créer un fichier de configuration storage.yml

  Exemple :
 ```bash
 GenerateConfigurationFiles.createStorageFile("mysql-pv-volume", "mysql-pv-claim", "20Gi", "/mnt/data");
 ```


### Création d'un fichier de déploiement pour les bases de données
 #### createDeploymentDBFile(nom du déploiement,nom de image, nom du secret, répertoire de stockage, port, nom du volume claim) : 
 - Créer un fichier de configuration deploymentDB.yaml pour les bases de données

 Exemple :
```bash
 GenerateConfigurationFiles.createDeploymentDBFile("mysql", "mysql:5.7", "mysql-secret", "/var/lib/mysql", 3306, "mysql-pv-claim");
 ```

  
### Création d'un fichier de déploiement pour les applications
 #### a) createDeploymentAppFile(nom du déploiement, nom de image) : 
 - Créer un fichier de configuration deploymentApp.yaml pour les applications avec un replicas par défaut à 1
     
 Exemple :
 
```bash
 GenerateConfigurationFiles.createDeploymentAppFile("my-service", "efrei/my-service:latest");
 ```    
 
#### b) createDeploymentAppFile(nom du déploiement, nom de image, le nombre de replicas) : 
 - Créer un fichier de configuration deploymentApp.yaml pour les applications avec un nombre de replicas donné

 Exemple :
```bash
 GenerateConfigurationFiles.createDeploymentAppFile("my-service", "efrei/my-service:latest", 2);	
```    
### Création d'un fichier de service ClusterIP 
 #### createServiceClusterIpFile(nom du service, port) : 
 - Créer un fichier de configuration serviceClusterIp.yaml 

 Exemple :
```bash
 GenerateConfigurationFiles.createServiceClusterIpFile("mysql", 3306);
```    
      
### Création d'un fichier de service NodePort
 #### a) createServiceNodePortFile(nom du service, port) : 
 - Créer un fichier de configuration serviceClusterNodePort.yaml avec un NodePort par défaut à 31281

 Exemple :
```bash
 GenerateConfigurationFiles.createServiceNodePortFile("mysql", 3306);
```         
 #### b) createServiceNodePortFile(nom du service, port, nodePort) : 
 - Créer un fichier de configuration serviceClusterNodePort.yaml avec un NodePort donné

 Exemple :
```bash
 GenerateConfigurationFiles.createServiceNodePortFile("mysql", 3306, 31283);
```    

### Création d'un fichier de service LoadBalancer
 #### a) createServiceLoadBalancerFile(nom du service, port) : 
 - Créer un fichier de configuration serviceLoadBalancer.yaml avec un NodePort par défaut à 31285

 Exemple :
```bash
 GenerateConfigurationFiles.createServiceLoadBalancerFile("my-service", 8080);
```         

 #### b) createServiceLoadBalancerFile(nom du service, port, nodePort) : 
 - Créer un fichier de configuration serviceClusterNodePort.yaml avec un NodePort donné

 Exemple :
```bash
 GenerateConfigurationFiles.createServiceLoadBalancerFile("my-service", 8080,31289);
```                 

### Création d'un fichier Dockerfile
 #### a) createDockerFile(nom du jar, port) : 
 - Créer un fichier Dockerfile avec une version de java par défaut à 8 

 Exemple :
```bash
 GenerateConfigurationFiles.createDockerFile("MyService-0.0.1-SNAPSHOT.jar", 8080);
```     
      
 #### b) createDockerFile(nom du jar, port, version de java) : 
 - Créer un fichier Dockerfile avec une version de java donnée

 Exemple :
```bash
 GenerateConfigurationFiles.createDockerFile("MyService-0.0.1-SNAPSHOT.jar", 8080, 11);
```     

 #### c) createDockerFile(nom du jar) : 
 - Créer un fichier Dockerfile avec une version de java 8 et un port par défaut 8080

 Exemple :
```bash
 GenerateConfigurationFiles.createDockerFile("MyService-0.0.1-SNAPSHOT.jar");
```     

 #### d) createDockerFile(version de java, nom du jar) : 
 - Créer un fichier Dockerfile avec une version de java donnée et un port par défaut 8080

 Exemple :
```bash
 GenerateConfigurationFiles.createDockerFile(11,"MyService-0.0.1-SNAPSHOT.jar");
```     
            
