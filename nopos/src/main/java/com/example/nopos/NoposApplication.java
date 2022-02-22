package com.example.nopos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.http.dsl.Http;
import org.springframework.integration.jpa.dsl.Jpa;
import org.springframework.integration.jpa.support.PersistMode;
import org.springframework.messaging.MessageChannel;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication
public class NoposApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(NoposApplication.class, args);
		UserGateway userGateway = (UserGateway) context.getBean(UserGateway.class);
		User tintin = new User();
		tintin.setName("Tintin");
		tintin.setEmail("tintin@moulinsard.fr");
		userGateway.send(tintin);

	}

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Bean
	public IntegrationFlow gateway() {
		return IntegrationFlows.from(UserGateway.class)
			//	.channel("personDatabaseChannel")
				.channel("outboundDatabaseAdapterFlow.input")
			//	.log()
				.get();
	}

	/**
	 * Insert into database
	 * @return
	 */
	@Bean
	public IntegrationFlow outboundDatabaseAdapterFlow() {
		return f -> f
				.handle(Jpa.outboundAdapter(entityManagerFactory)
								.entityClass(User.class)
								.persistMode(PersistMode.PERSIST),
						e -> e.transactional(true))
				.log();
	}

	/**
	 * Select continuously in the database (each 5 seconds)
	 * @return
	 */
	@Bean
	public IntegrationFlow inboundDatabaseAdapterFlow() {
		return IntegrationFlows
				.from(Jpa.inboundAdapter(this.entityManagerFactory)
								.entityClass(User.class)
								.maxResults(1)
								.expectSingleResult(true),
						e -> e.poller(p -> p.fixedDelay(5000)))
				.log()
				.channel("personDatabaseChannel")
				.get();
	}

	@Bean
	public MessageChannel personDatabaseChannel() {
		return MessageChannels.queue().get();
	}

	/**
	 * Rest Web Service call
	 * @return
	 */
	@Bean
	public IntegrationFlow outboundHttpPost() {
		return IntegrationFlows.from("personDatabaseChannel")
				.handle(Http.outboundChannelAdapter("http://localhost:8080/person")
						.httpMethod(HttpMethod.POST))
				.get();
	}

	/*@Bean
	public IntegrationFlow outboundHttpGet() {
		return IntegrationFlows.from("outboundHttpPost")
				.handle(Http.outboundChannelAdapter("http://localhost:8080/person")
						.httpMethod(HttpMethod.GET)
						.expectedResponseType(Person.class))
				.channel("personWebServiceChannel")
				.get();
	}

	@Bean
	public MessageChannel personWebServiceChannel() {
		return MessageChannels.queue().get();
	}

	@Bean
	public IntegrationFlow outboundKafkaFlow() {
		return IntegrationFlows.from("personWebServiceChannel")
				.log()
				.get();
	}*/

}
