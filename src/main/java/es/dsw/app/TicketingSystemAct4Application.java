package es.dsw.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

//Poner esto en el main de todos las aplicacciones
@ComponentScan(basePackages="es.dsw")
public class TicketingSystemAct4Application {

	public static void main(String[] args) {
		SpringApplication.run(TicketingSystemAct4Application.class, args);
	}

}
