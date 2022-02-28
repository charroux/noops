#Communication entre une application et un web service en utilisant la base de données MySQL

##Prérequis
Ce didacticiel suppose que vous avez déjà installé Docker, Kubernetes et déployer une image MySQL dans un cluster Kubernetes.
Sinon vous pouvez suivre ce lien https://github.com/charroux/noops/tree/main/mysql pour déployer l'image MySQL dans un cluster Kubernetes.

##L'application principale : nopos
L'application est codée en Java (Spring boot) et utilise une base de données MySQL.
Elle utilise le webservice pour afficher :
              - Un utilisateur (par défaut Tintin) dans la console.
              - "Welcome to web servie" dans un navigateur. 

Une image Docker est déjà disponible dans le Github Docker à l'adresse : https://hub.docker.com/u/medbozo2020 , et ce tutoriel l'utilise. Mais n'hésitez pas à le reconstruire. Tout le code plus le Dockerfile sont là : https://github.com/charroux/noops/tree/main/noops/nopos

##Télécharger le projet nopos
Après avoir téléchrger le projet nopos, palcez-vous dans le dossier.

Testez ce projet avec Docker
Compilez le projet Java :

./gradlew construit sous Linux

construction gradlew sous Windows

Construisez l'image docker : docker build -t nopos.

Vérifiez l'image: images docker

Vérifiez l'ID du conteneur : docker ps

Publier l'image sur le Docker Hub

Récupérer l'ID de l'image : $docker images


Marquez l'image docker : docker tag imageID yourDockerHubName/imageName:version

Exemple : balise docker 1dsd512s0d myDockerID/my-service:1

Connectez-vous au hub Docker :

connexion docker ou
connexion docker http://hub.docker.com ou
connexion docker -u nom d'utilisateur -p mot de passe
Poussez l'image vers le docker hub : docker push yourDockerHubName/imageName:version

Exemple : docker push myDockerID/my-service:1

Créer un déploiement kubernetes à partir d'une image Docker
kubectl obtenir des nœuds

kubectl créer déploiement myservice --image=efrei/my-service:2

L'image utilisée provient du hub Docker : https://hub.docker.com/r/efrei/my-service/tags

Mais vous pouvez utiliser votre propre image à la place.

Vérifier le pod : kubectl get pods

Vérifiez si l'état est en cours d'exécution.

Obtenir les journaux complets d'un pod : kubectl describe pods

Récupérez l'adresse IP mais notez que cette adresse IP est éphémère puisqu'un pod peut être supprimé et remplacé par un nouveau.

Récupérez ensuite le déploiement dans le tableau de bord minikube. En fait, le conteneur Docker est exécuté dans un pod Kubernetes (regardez le pod dans le tableau de bord).

Vous pouvez également entrer dans le conteneur en mode interactif avec : kubectl exec -it podname-rthr5 -- /bin/bash

où podname est le nom des pods obtenus avec : kubectl get pods

Lister le contenu du conteneur avec : ls

N'oubliez pas de quitter le conteneur avec : exit

Exposer le déploiement via un service
Un service Kubernetes est une abstraction qui définit un ensemble logique de pods exécutés quelque part dans le cluster, qui fournissent tous la même fonctionnalité. Une fois créé, chaque service se voit attribuer une adresse IP unique (également appelée clusterIP). Cette adresse est liée à la durée de vie du service et ne changera pas tant que le service est actif.

Exposez les routes HTTP et HTTPS de l'extérieur du cluster aux services du cluster
Pour certaines parties de votre application (par exemple, les interfaces), vous souhaiterez peut-être exposer un service sur une adresse IP externe, en dehors de votre cluster.

Les types de service Kubernetes vous permettent de spécifier le type de service que vous souhaitez. La valeur par défaut est ClusterIP.

Les valeurs de type et leurs comportements sont :

ClusterIP : expose le service sur une adresse IP interne au cluster. Le choix de cette valeur rend le service uniquement accessible depuis le cluster. Il s'agit du ServiceType par défaut.
NodePort : expose le service sur l'IP de chaque nœud à un port statique (le NodePort). Un service ClusterIP, vers lequel le service NodePort est acheminé, est automatiquement créé. Vous pourrez contacter le service NodePort, depuis l'extérieur du cluster, en demandant NodeIP:NodePort.
LoadBalancer : expose le service en externe à l'aide de l'équilibreur de charge d'un fournisseur de cloud. Les services NodePort et ClusterIP, vers lesquels les routes de l'équilibreur de charge externe sont automatiquement créés.
ExternalName : mappe le service sur le contenu du champ externalName (par exemple, foo.bar.example.com), en renvoyant un enregistrement CNAME
Exposez les routes HTTP et HTTPS à l'aide de NodePort
kubectl expose déploiement myservice --type=NodePort --port=8080

Récupérer l'adresse du service : minikube service myservice --url

Ce format de cette adresse est NodeIP:NodePort.

Testez cette adresse dans votre navigateur. Il devrait afficher à nouveau bonjour.

Regardez depuis NodeIP et NodePort dans le tableau de bord minikube.
