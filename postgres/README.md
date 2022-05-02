#  Déploiment  d'un conteneur  Postgres dans un cluster Kubernetes

## 1) Déploi du serveur Postgres 
###Prérequis
```bash
minikube start
```

### Descri des  des fichiers  :

#### postges-secret.yaml 
Ce fichier définit le mot de passe qui sera utilisé lors de la connexion au serveur  Postgres.
```bash 
kubectl apply -f secret.yaml
```

####  postgres-deployment.yaml
Ce fichier définit  le conteneur postgres à lancer sur  le cluster kubernetes
```bash
kubectl apply -f deploymentApp.yaml
```
### Création de service 
Afin de cré le service, il  y a deux fichiers , il est pas necessaire d'exécuter les deux.

#### Le service de type ClusterIP 
Le fichier serviceClusterIp.yaml crée un service 'postgres' qui est uniquement accessible dans le cluster.
```bash 
kubectl apply -f serviceClusterIP.yaml
```
#### Le service de type NodePort 
Le fichier serviceNodePort.yaml crée un service 'postgres' qui est accessible à la fois à l'intérieur et à l'extérieur du cluster. 
```bash 
Kubertl apply -f serviceNodePort.yaml
```

## 2) Vérification des  configuras
```bash

  kubectl get secrets
  kubectl get PersistentVolumes
  kubectl get deploymen
  kubectl get service
  kubectl get pod
  ```
## 3) Connexion au serveur Postgres :
```bash
ubectl exec --stdin --tty my-service-6df765865-b7pp6 -- postgres -ptest1234
```
Apré l'exécution de cette commande nous avons une erreure : 
décricre le bug !!
