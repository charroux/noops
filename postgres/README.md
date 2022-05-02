#  Déploiment  d'un conteneur  Postgres dans un cluster Kubernetes

## 1) Déploi du serveur Postgres 
###Prérequis


### Descri des  des fichiers  :

#### postges-secret.yaml 
Ce fichier définit le mot de passe qui sera utilisé lors de la connexion au serveur  Postgres.
La commande 

####  postgres-deployment.yaml
Ce fichier définit  le conteneur postgres à lancer sur  le cluster kubernetes
la commande 

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
   kubectl get PersistentVolumeClaims
   kubectl get deployments
    kubectl get services
    kubectl get pod
  ```
## 3) Connexion au serveur Postgres :hsba
```bash
kubectl exec --stdin --tty my-service-6df765865-b7pp6 -- postgres -ptest1234
```
