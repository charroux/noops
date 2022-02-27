# noops

Le programme principal : https://github.com/charroux/noops/blob/main/nopos/src/main/java/com/example/nopos/NoposApplication.java

Création d'une personne dans le main
```
Person tintin = new Person();
tintin.setName("Tintin");
personGateway.send(tintin);
```

La classe JPA Personne : https://github.com/charroux/noops/blob/main/nopos/src/main/java/com/example/nopos/Person.java

<!---
Envoi de la personne vers la base de données
```
@Bean
public IntegrationFlow gateway() {
return IntegrationFlows.from(PersonGateway.class)
.channel("outboundDatabaseAdapterFlow.input")
//	.log()
.get();
}
```
Insert dans la base de données. C'est H2 qui est utilisée en configuration In Memory.
```
@Bean
public IntegrationFlow outboundDatabaseAdapterFlow() {
	return f -> f
			.handle(Jpa.outboundAdapter(entityManagerFactory)
							.entityClass(Person.class)
							.persistMode(PersistMode.PERSIST),
					e -> e.transactional(true));
		//	.log();
}
```
Lecture de la base. 
```
@Bean
public IntegrationFlow inboundDatabaseAdapterFlow() {
	return IntegrationFlows
			.from(Jpa.inboundAdapter(this.entityManagerFactory)
							.entityClass(Person.class)
							.maxResults(1)
							.expectSingleResult(true),
					e -> e.poller(p -> p.fixedDelay(5000)))
			.log()
			.channel("personDatabaseChannel")
			.get();
}
```
--->
Appel d'un Web Service
```
@Bean
public IntegrationFlow outboundHttpPost() {
	return IntegrationFlows.from("personDatabaseChannel")
			.handle(Http.outboundChannelAdapter("http://localhost:8080/person")
					.httpMethod(HttpMethod.POST))
			.get();
}
```
Le Web Service : https://github.com/charroux/noops/blob/main/nopos/src/main/java/com/example/nopos/WebService.java



