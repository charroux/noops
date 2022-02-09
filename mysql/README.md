# ⚙ Deploiement d'un conteneur Mysql dans un cluster Kubernetes 

## 1) Deploiement du serveur MYSQL 


### 💡 Descriptif des fichiers : 

**mysql-secret.yaml** : Ce fichier décrit les variables d'environnement utilisé lors du déploiement du serveur MYSQL 

**mysql-storage.yaml** : Ce fichier décrit les volumes qui vont être utilisés par le serveur MYSQL pour stocker les données 

**mysql-deployment.yaml**: Ce fichier décrit l'ensemble des conteneurs à déployer, en l'espèce : MYSQL Serveur

mysql-

### Voici les commandes utilisées pour déployer notre application à partir de nos fichiers de configuration
```bash
kubectl apply -f mysql-secret.yaml
kubectl apply -f mysql-storage.yaml
kubectl apply -f mysql-deployment.yaml
kubectl get deployment
kubectl get pods
```

