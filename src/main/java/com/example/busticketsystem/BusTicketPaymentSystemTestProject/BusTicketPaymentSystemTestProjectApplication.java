package com.example.busticketsystem.BusTicketPaymentSystemTestProject;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.configuration.ApplicationSwaggerConfig;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Flight;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Ticket;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.FlightService;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.TicketService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//@ComponentScan(basePackages = "com.example.busticketsystem.BusTicketPaymentSystemTestProject")
@SpringBootApplication(scanBasePackages = {"com.example.busticketsystem.BusTicketPaymentSystemTestProject.*"})
//@EnableConfigurationProperties(ApplicationSwaggerConfig.class)

public class BusTicketPaymentSystemTestProjectApplication {
	@Autowired
	TicketService ticketService;
	@Autowired
	FlightService flightService;

	public static void main(String[] args) {
		SpringApplication.run(BusTicketPaymentSystemTestProjectApplication.class, args);
	}

	/*@Bean
	InitializingBean sendDatabase() {

		Ticket ticket = new Ticket();
		ticket.setOwner("Misha");
		Ticket ticket1 = new Ticket();
		ticket1.setOwner("Misha1");

		Ticket ticket2 = new Ticket();
		ticket2.setOwner("Misha2");
		Ticket ticket3 = new Ticket();
		ticket3.setOwner("Misha3");

		Flight flight = new Flight();
		flight.addTicket(ticket);
		flight.addTicket(ticket1);
		flight.addTicket(ticket2);
		flight.addTicket(ticket3);

		return () -> {
			flightService.saveFlight(flight);
		};
	}*/

}
