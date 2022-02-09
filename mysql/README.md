# Deploiement d'un conteneur MySQL dans un cluster Kubernetes 

## 1) Deploiement du serveur MySQL
### Prérequis
```bash
minikube start
```
### Descriptif des fichiers : 

#### mysql-secret.yaml
Ce fichier définit le mot de passe qui sera utilisé lors de la connexion au serveur MySQL.
```bash
kubectl apply -f mysql-secret.yaml
```

#### mysql-storage.yaml
Ce fichier définit les volumes qui vont être utilisés par le serveur MySQL pour stocker les données. 
```bash
kubectl apply -f mysql-storage.yaml
```
#### mysql-deployment.yaml
Ce fichier définit le conteneur mysql à déployer sur le cluster kubernetes.
```bash
kubectl apply -f mysql-deployment.yaml
```
### Création de service
Il existe deux fichiers de création de service, il n'est pas nécessaire d'exécuter les deux.

Exécuter uniquement celui qui correspond le mieux à votre besoin.
#### Le service de type ClusterIP 
Le fichier mysql-serviceClusterIp.yaml crée un service 'mysql' qui est uniquement accessible dans le cluster. 
```bash
kubectl apply -f mysql-serviceClusterIp.yaml
```
#### Le service de type NodePort
Le fichier mysql-serviceNodePort.yaml crée un service 'mysql' qui est accessible à la fois à l'intérieur et à l'extérieur du cluster.
```bash
kubectl apply -f mysql-serviceNodePort.yaml
```

## 2) Vérification des configurations
```bash
kubectl get deployments
kubectl get pods
kubectl get services
```
## 3) Connexion au server MySQL
### Le service de type ClusterIP 
```bash
kubectl exec --stdin --tty <Id du pod> -- mysql -ptest1234
```
### Le service de type NodePort
```bash
minikube service mysql 
```
ou 
```bash
minikube service mysql --url
```




