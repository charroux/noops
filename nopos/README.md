# noops

Création d'un étudiant dans le main
'''
Person tintin = new Person();
tintin.setName("Tintin");
personGateway.send(tintin);
'''

Envoi du la personne vers le gestionaire de BDD
'''
@Bean
public IntegrationFlow gateway() {
return IntegrationFlows.from(PersonGateway.class)
.channel("outboundDatabaseAdapterFlow.input")
//	.log()
.get();
}
'''


