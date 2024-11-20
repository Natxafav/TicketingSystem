package es.dsw.controllers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


@Controller

@SessionAttributes({"reservation", "sessionData", "client"})
public class indexController {
	
	@GetMapping({"/", "/index"})
    public String showHome(SessionStatus status,Model model) {
		status.setComplete();
		
		
        DayOfWeek dayOfWeek = getCalculatedDay();
        String mensajeDelDia;
        double precioEntrada;

       
        switch (dayOfWeek) {
            case MONDAY:
                mensajeDelDia = "Comienza la semana a lo grande.";
                precioEntrada = 6.0;
                break;
            case TUESDAY:
                mensajeDelDia = "Hoy doble de palomitas.";
                precioEntrada = 6.0;
                break;
            case WEDNESDAY:
                mensajeDelDia = "Día del espectador.";
                precioEntrada = 3.5;
                break;
            case THURSDAY:
                mensajeDelDia = "La noche de las aventuras.";
                precioEntrada = 6.0;
                break;
            case FRIDAY:
                mensajeDelDia = "No te quedes en tu casa.";
                precioEntrada = 6.0;
                break;
            case SATURDAY:
                mensajeDelDia = "¿Ya has hecho planes para esta noche?";
                precioEntrada = 6.0;
                break;
            case SUNDAY:
                mensajeDelDia = "Vente y carga las pilas.";
                precioEntrada = 6.0;
                break;
            default:
                mensajeDelDia = "¡Bienvenido al cine!";
                precioEntrada = 6.0;
                break;
        }

        LocalDateTime ahora = LocalDateTime.now();
      
        String fecha = ahora.format(DateTimeFormatter.ofPattern("EEEE,'día' d 'de' MMMM"));
        String hora = ahora.format(DateTimeFormatter.ofPattern("HH'h' mm'm'"));
        fecha = capitalizeWords(fecha);
        model.addAttribute("mensajeDelDia", mensajeDelDia);
        model.addAttribute("precioEntrada", precioEntrada);
        model.addAttribute("fecha", fecha);
        model.addAttribute("hora", hora);

        return "index"; 
    }

   private DayOfWeek getCalculatedDay() {
    	return LocalDate.now().getDayOfWeek(); 
    }
   public String capitalizeWords(String str) {
	   Set<String> exceptions = new HashSet<>(Arrays.asList("de", "día"));

	    String[] words = str.split(" ");
	    StringBuilder capitalized = new StringBuilder();

	    for (int i = 0; i < words.length; i++) {
	        String word = words[i];

	        
	        if (exceptions.contains(word.toLowerCase()) && i != 0) {
	            capitalized.append(word.toLowerCase());
	        } else {
	            capitalized.append(Character.toUpperCase(word.charAt(0)))
	                       .append(word.substring(1).toLowerCase());
	        }
	       
	        if (i < words.length - 1) {
	            capitalized.append(" ");
	        }
	    }

	    return capitalized.toString();
	}
}
