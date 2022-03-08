# Programme de génération des fichiers de configuration

Ce programme écrit en Java (POO) permet de générer des fichiers de configuration :
- Kubernetes 
- Docker

## Prérequis
Version de Java supérieure ou égale à 8.

## Description des classes

### Classe Secret
 #### Constructeur : Secret(nom du secret, mot de passe)
 #### Méthode : createSecretFile() : création du fichier secret.yaml
 
 Exemple 
  ```bash
Secret secret=new Secret("mysql-secret", "test1234");
secret.createSecretFile();
 ```
 
### Classe Storage
#### Constructeur : Storage(nom du volume, nom du volume claim, capacité, répertoire de stockage)
#### Méthode : createStorageFile() : création du fichier storage.yaml

  Exemple :
  
```bash
Storage storage=new Storage("mysql-pv-volume", "mysql-pv-claim", "20Gi", "/mnt/data");
storage.createStorageFile();
```

### Classe DeploymentDB
#### Constructeur :  DeploymentDB(nom du déploiement, nom de image, instance de la classe Secret, répertoire de stockage, port, instance de la classe Storage)
#### Méthode : createDeploymentDBFile() : création du fichier deploymentDB.yaml pour les bases de données

  Exemple :
  
```bash
DeploymentDB depDB=new DeploymentDB("mysql", "mysql:5.7", secret, "/var/lib/mysql", 3306, storage);
depDB.createDeploymentDBFile();
```
### Classe DeploymentApp
#### 1. Constructeur :  DeploymentApp(nom du déploiement, nom de image)
#### Méthode : createDeploymentAppFile() : création du fichier deploymentApp.yaml pour les applications avec un replicas par défaut à 1

  Exemple :
  
```bash
DeploymentApp depApp=new DeploymentApp("my-service", "efrei/my-service:latest");
depApp.createDeploymentAppFile();
```

#### 2. Constructeur :  DeploymentApp(nom du déploiement, nom de image, le nombre de replicas)
#### Méthode : createDeploymentAppFile() : création du fichier deploymentApp.yaml pour les applications avec un replicas donné

  Exemple :
  
```bash
DeploymentApp depApp2=new DeploymentApp("my-service", "efrei/my-service:latest",2);
depApp2.createDeploymentAppFile();
```
### Classe ServiceClusterIp
#### 1. Constructeur : ServiceClusterIp(instance de la classe DeploymentApp, port)
#### Méthode : createServiceClusterIpFileApp() : création du fichier serviceClusterIp.yaml pour les applications

  Exemple :
  
```bash
ServiceClusterIp serviceClusterIpApp=new ServiceClusterIp(depApp, 3306);
serviceClusterIpApp.createServiceClusterIpFileApp();
```

#### 2. Constructeur : ServiceClusterIp(instance de la classe DeploymentDB, port)
#### Méthode : createServiceClusterIpFileDB() : création du fichier serviceClusterIp.yaml pour les bases de données

  Exemple :
  
```bash
ServiceClusterIp serviceClusterIpDB=new ServiceClusterIp(depDB, 3306);
serviceClusterIpDB.createServiceClusterIpFileDB();
```

### Classe ServiceNodePort
#### 1. Constructeur : ServiceNodePort(instance de la classe DeploymentApp, port)
#### Méthode : createServiceNodePortFileApp() : création du fichier serviceNodePort.yaml avec un Nodeport par défaut à 31281 pour les applications

  Exemple :
  
```bash
ServiceNodePort serviceNodePortApp=new ServiceNodePort(depApp2, 3306);
serviceNodePortApp.createServiceNodePortFileApp();
```

#### 2. Constructeur : ServiceNodePort(instance de la classe DeploymentApp, port, nodePort)
#### Méthode : createServiceNodePortFileApp() : création du fichier serviceNodePort.yaml avec un NodePort donné pour les applications

  Exemple :
  
```bash
ServiceNodePort serviceNodePortApp2=new ServiceNodePort(depApp2, 3306, 31283);
serviceNodePortApp2.createServiceNodePortFileApp();
```

#### 3. Constructeur : ServiceNodePort(instance de la classe DeploymentDB, port)
#### Méthode : createServiceNodePortFileDB() : création du fichier serviceNodePort.yaml avec un Nodeport par défaut à 31290 pour les bases de données

  Exemple :
  
```bash
ServiceNodePort serviceNodePortDB=new ServiceNodePort(depDB, 3306);
serviceNodePortDB.createServiceNodePortFileDB();
```

#### 4. Constructeur : ServiceNodePort(instance de la classe DeploymentDB, port, nodePort)
#### Méthode : createServiceNodePortFileDB() : création du fichier serviceNodePort.yaml avec un NodePort donné pour les bases de données

  Exemple :
  
```bash
ServiceNodePort serviceNodePortDB2=new ServiceNodePort(depDB, 3306, 31283);
serviceNodePortDB2.createServiceNodePortFileDB();
```

### Classe ServiceLoadBalancer
#### 1. Constructeur : ServiceLoadBalancer(instance de la classe DeploymentApp, port)
#### Méthode : createServiceLoadBalancerFile() : création du fichier serviceLoadBalancer.yaml avec un Nodeport par défaut à 31285 pour les applications

  Exemple :
  
```bash
ServiceLoadBalancer serviceLoadBalancerApp=new ServiceLoadBalancer(depApp2, 8080);
serviceLoadBalancerApp.createServiceLoadBalancerFile();
```

#### 2. Constructeur :  ServiceLoadBalancer(instance de la classe DeploymentApp, port, nodePort)
#### Méthode : createServiceLoadBalancerFile() : création du fichier serviceClusterNodePort.yaml avec un NodePort donné pour les applications

  Exemple :
  
```bash
ServiceLoadBalancer serviceLoadBalancerApp2=new ServiceLoadBalancer(depApp2, 8080, 31289);
serviceLoadBalancerApp2.createServiceLoadBalancerFile();
```

### Classe Docker
#### 1. Constructeur : Docker(nom du jar, port)
#### Méthode : createDockerFile() : création d'un fichier Dockerfile avec une version de java par défaut à 8

  Exemple :
  
```bash
Docker dockerfile1=new Docker("MyService-0.0.1-SNAPSHOT.jar", 8080);
dockerfile1.createDockerFile();
```

#### 2. Constructeur : Docker(nom du jar, port, une version de java)
#### Méthode : createDockerFile() : création d'un fichier Dockerfile avec une version de java donnée

  Exemple :
  
```bash
Docker dockerfile2=new Docker("MyService-0.0.1-SNAPSHOT.jar", 8080, 11);
dockerfile2.createDockerFile();
```

#### 3. Constructeur : Docker(nom du jar)
#### Méthode : createDockerFile() : création d'un fichier Dockerfile avec une version de java à 8 et un port par défaut à 8080

  Exemple :
  
```bash
Docker dockerfile3=new Docker("MyService-0.0.1-SNAPSHOT.jar");
dockerfile3.createDockerFile();
```

#### 4. Constructeur : Docker(une version de java, nom du jar)
#### Méthode : createDockerFile() : création d'un fichier Dockerfile avec une version de java donnée et un port par défaut à 8080

  Exemple :
  
```bash
Docker dockerfile4=new Docker(11,"MyService-0.0.1-SNAPSHOT.jar");
dockerfile4.createDockerFile();		
```

