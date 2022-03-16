# Communication entre une application et un web service en utilisant la base de données MySQL

## Prérequis
Ce didacticiel suppose que vous avez déjà installé Docker, Kubernetes et déployer une image MySQL dans un cluster Kubernetes.
Sinon vous pouvez suivre ce lien https://github.com/charroux/noops/tree/main/mysql pour déployer l'image MySQL dans un cluster Kubernetes.

## Le web service : webservice
Le web service est codé en Java (Spring boot).
Il affiche :
* Un utilisateur (par défaut Tintin) dans la console.
* "Welcome to webservice" dans un navigateur. 

## Télécharger le projet webservice
Après avoir télécharger le projet webservice, palcez-vous dans le dossier.

## Tester ce projet avec Docker
Compilez le projet Java :

Construit  gradlew sous Linux
```bash
./gradlew 
```
Construction gradlew sous Windows
```bash
gradlew build
```
Construisez l'image docker 
```bash
docker build -t webservice .
```
Vérifiez l'image
``` bash
docker images 
```
Vérifiez l'ID du conteneur 
``` bash
docker ps
```
## Publier l'image sur le Docker Hub

Récupérer l'ID de l'image : 
```bash 
docker images
```
Marquez l'image docker : 
``` bash
docker tag imageID yourDockerHubName/imageName:version
```

Exemple :
``` bash
docker 1dsd512s0d myDockerID/webservice:1
``` 
Connectez-vous au Docker Hub :
``` bash
docker login
``` 
ou
``` bash
docker login http://hub.docker.com 
``` 
ou
``` bash
docker login -u nom d'utilisateur -p mot de passe
``` 
Poussez l'image vers le docker hub 
``` bash
docker push yourDockerHubName/imageName:version
``` 
Exemple : 
``` bash
docker push myDockerID/webservice:1
```
## Créer un déploiement kubernetes à partir d'une image Docker webservice
``` bash
kubectl get nodes

kubectl create deployment mysqlwebservice --image=medbozo2020/mysqlwebservice:1
``` 
L'image utilisée provient du hub Docker : https://hub.docker.com/r/medbozo2020/mysqlwebservice/tags

Mais vous pouvez utiliser votre propre image à la place.

Vérifier le pod :
``` bash
kubectl get pods
``` 
Vérifiez si l'état est en cours d'exécution.

Obtenir les journaux complets d'un pod 
``` bash
kubectl describe pods
``` 
## Exposer le déploiement via un service ClusterIP

``` bash
kubectl expose deployment mysqlwebservice --name=mysqlwebservice --port=8181
``` 

Une image Docker est déjà disponible dans Docker Hub à l'adresse : https://hub.docker.com/u/medbozo2020 , et ce tutoriel l'utilise. Mais n'hésitez pas à le reconstruire. Tout le code plus le Dockerfile sont là : https://github.com/charroux/noops/tree/main/noops/webservice

NB: Le web service ne peut pas être tester pour le moment, car il n'est pas accessible via le navigateur.

## L'application principale : nopos
L'application est codée en Java (Spring boot) et utilise une base de données MySQL.

Elle utilise le webservice pour afficher :
* Un utilisateur (par défaut Tintin) dans la console.
* "Welcome to webservice" dans un navigateur. 
* Crée une table user dans la base de données db_example.
* Insert 1 ligne contenant "email: tintin@moulinsard.fr, name: Tintin" dans la table user.  

## Télécharger le projet nopos
Après avoir téléchrger le projet nopos, palcez-vous dans le dossier.

## Tester ce projet avec Docker 

Compilez le projet Java :

Construit  gradlew sous Linux
```bash
./gradlew 
```
Construction gradlew sous Windows
```bash
gradlew build
```
Construisez l'image docker 
```bash
docker build -t nopos .
```
Vérifiez l'image
``` bash
docker images 
```
Vérifiez l'ID du conteneur 
``` bash
docker ps
```
## Publier l'image sur le Docker Hub

Récupérer l'ID de l'image : 
```bash 
docker images
```
Marquez l'image docker : 
``` bash
docker tag imageID yourDockerHubName/imageName:version
```

Exemple :
``` bash
docker 1dsd512s0d myDockerID/nopos:1
``` 
Connectez-vous au hub Docker :
``` bash
docker login
``` 
ou
``` bash
docker login http://hub.docker.com 
``` 
ou
``` bash
docker login -u nom d'utilisateur -p mot de passe
``` 
## Poussez l'image vers le docker hub 
``` bash
docker push yourDockerHubName/imageName:version
``` 
Exemple : 
``` bash
docker push myDockerID/nopos:1
```
## Créer un déploiement kubernetes à partir d'une image Docker nopos 
``` bash
kubectl get nodes

kubectl create deployment noopsmysql --image=medbozo2020/noopsmysql3:1
``` 
L'image utilisée provient du hub Docker : https://hub.docker.com/r/medbozo2020/noopsmysql3/tags

Mais vous pouvez utiliser votre propre image à la place.

Vérifier le pod :
``` bash
kubectl get pods
``` 
Vérifiez si l'état est en cours d'exécution.

Obtenir les journaux complets d'un pod 
``` bash
kubectl describe pods
``` 
## Exposer le déploiement via un service
Exposez les routes HTTP et HTTPS à l'aide de NodePort
``` bash
kubectl expose deployment noopsmysql --type=NodePort --port=8080
``` 
Récupérer l'adresse du service :
``` bash
minikube service noopsmysql --url
``` 
Testez cette adresse dans votre navigateur. Il devrait afficher à nouveau "Welcome to webservice".


Une image Docker est déjà disponible dans Docker Hub à l'adresse : https://hub.docker.com/u/medbozo2020 , et ce tutoriel l'utilise. Mais n'hésitez pas à le reconstruire. Tout le code plus le Dockerfile sont là : https://github.com/charroux/noops/tree/main/noops/nopos




