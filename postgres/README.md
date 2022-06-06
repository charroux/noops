#  Déploiement  d'un conteneur  Postgres dans un cluster Kubernetes

## 1) Déploiement du serveur Postgresql 
###Prérequis
```bash
minikube start
```

### Description des fichiers  :

#### postges-secret.yaml 
Ce fichier définit le mot de passe qui sera utilisé lors de la connexion au serveur  Postgres.
```bash 
kubectl apply -f postges-secret.yaml
```

#### postges-storage.yaml 
Ce fichier définit le stockage du cluster Postgres.
```bash 
kubectl apply -f postges-storage.yaml
```


####  postgres-deployment.yaml
Ce fichier définit  le conteneur postgres à lancer sur  le cluster kubernetes
```bash
kubectl apply -f postgres-deployment.yaml
```
### Création du service 
Afin de créer le service, il  y a deux fichiers , il n'est pas nécessaire d'exécuter les deux.

#### Le service de type ClusterIP 
Le fichier postgres-service.yaml crée un service 'postgres' qui est uniquement accessible dans le cluster.
```bash 
kubectl apply -f postgres-service.yaml
```
#### Le service de type NodePort 
Le fichier serviceNodePort.yaml crée un service 'postgres' qui est accessible à la fois à l'intérieur et à l'extérieur du cluster. 
```bash 
Kubertl apply -f serviceNodePort.yaml
```

## 2) Vérification des  configurations
```bash

  kubectl get secrets
  kubectl get PersistentVolumes
  kubectl get deploymen
  kubectl get service
  kubectl get pod
  ```
## 3) Connexion au serveur Postgres :
```bash
kubectl exec --stdin --tty <Pod_Name> -- postgres -ptest1234
```
Aprés l'exécution de cette commande nous avons un bug 

### Excecution des commandes et l'erreur obtenu : 
```bash
Miage_02\PPD\noops\generateConfigurationFiles\src> kubectl apply -f secret.yaml
```
secret/postgres-secret created
```bash
Miage_02\PPD\noops\generateConfigurationFiles\src> kubectl apply -f storage.yaml
```
persistentvolume/postgres-pv-volume created
persistentvolumeclaim/postgres-pv-claim created
```bash
Miage_02\PPD\noops\generateConfigurationFiles\src> kubectl apply -f deploymentDB.yaml
```
deployment.apps/postgres created
```bash
Miage_02\PPD\noops\generateConfigurationFiles\src> kubectl apply -f serviceClusterIp.yaml      
```
```bash
Miage_02\PPD\noops\generateConfigurationFiles\src> kubectl apply -f serviceNodePort.yaml      
service/postgres configured
```bash
Miage_02\PPD\noops\generateConfigurationFiles\src> kubectl get secrets

NAME                  TYPE                                  DATA   AGE
default-token-xbrdt   kubernetes.io/service-account-token   3      87m
postgres-secret       kubernetes.io/basic-auth              1      73m
```
```bash
Miage_02\PPD\noops\generateConfigurationFiles\src> kubectl get PersistentVolumes

NAME                 CAPACITY   ACCESS MODES   RECLAIM POLICY   STATUS   CLAIM                       STORAGECLASS   REASON   AGE
postgres-pv-volume   10Gi       RWO            Retain           Bound    default/postgres-pv-claim   manual                  72m
NAME                STATUS   VOLUME               CAPACITY   ACCESS MODES   STORAGECLASS   AGE
postgres-pv-claim   Bound    postgres-pv-volume   10Gi       RWO            manual         73m
```
```bash
Miage_02\PPD\noops\generateConfigurationFiles\src> kubectl get deployments
```
NAME       READY   UP-TO-DATE   AVAILABLE   AGE
postgres   0/1     1            0           110s

```bash
Miage_02\PPD\noops\generateConfigurationFiles\src> kubectl get services

NAME         TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)          AGE
kubernetes   ClusterIP   10.96.0.1        <none>        443/TCP          88m
postgres     NodePort    10.109.117.184   <none>        5432:31283/TCP   90s
```
```bash
Miage_02\PPD\noops\generateConfigurationFiles\src> kubectl get pods
```
NAME                        READY   STATUS             RESTARTS      AGE
postgres-74f5b69cdc-djcdj   0/1     CrashLoopBackOff   3 (28s ago)   2m12s
  
```bash

Miage_02\PPD\noops\generateConfigurationFiles\src> kubectl exec --stdin --tty postgres-74f5b69cdc-djcdj -- postgres -ptest1234
```
error: unable to upgrade connection: container not found ("postgres")

## Identificartion de l'erreur : 
Absence du fichier de configuration configmap.yaml
```bash
apiVersion: v1
kind: ConfigMap
metadata:
  name: postgres-config
  labels:
    app: postgres
data:
  POSTGRES_DB: postgresdb
  POSTGRES_USER: postgresadmin
  POSTGRES_PASSWORD: admin123
```

## commande de connexion au pod postgres :
```bash
  kubectl exec -it postgres-5cb8b67d8f-d97gm -- psql -U postgres
```
