package es.dsw.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.dsw.connection.MySqlConnection;
import es.dsw.models.Client;
import es.dsw.models.Reservation;
import es.dsw.models.SessionData;

@SessionAttributes({"reservation", "sessionData", "client"})
@Controller
public class Step3Controller {
	

	@GetMapping("/step3")
	public String step3( Model model) {
		 Reservation reservation = (Reservation) model.getAttribute("reservation");
		 SessionData sessionData = (SessionData) model.getAttribute("sessionData");
	        if (reservation == null || sessionData == null ) {      
	            return "redirect:/step1";
	        }
	        sessionData.setfButacasSelected("");
	 
	        model.addAttribute("reservation", reservation);
	        model.addAttribute("sessionData", sessionData);
	       
	        return "views/step3";
	    }
	
	 @PostMapping("/step4")
	 public String step3Post(@ModelAttribute Reservation reservation,
			 @ModelAttribute SessionData sessionData, Model model, 
			 RedirectAttributes redirectAttributes,
			 @ModelAttribute Client client) throws SQLException {
	
			 if(sessionData.getfButacasSelected() == null || sessionData.getfButacasSelected().isBlank() ) {
				 redirectAttributes.addFlashAttribute("error", "Debes seleccionar el mismo numero de butacas que de asistentes.");
				 return "redirect:/step3"; 
			 }
			 
			
		     String movieName = getMovieName(sessionData.getFilmId());
			 sessionData.setFilmName(movieName);
			 
			 double total = calcularTotal(reservation, sessionData);
			 client.setTotalPagar(total);
			 sessionData.setfButacasSelected(butacas(sessionData, reservation));
			 
			 model.addAttribute("formatDate",formatDate( reservation) )	;		 
			 model.addAttribute("reservation", reservation);
			 model.addAttribute("sessionData", sessionData);
		        
		     return "views/step4";
		}
	
	 private double calcularTotal(Reservation reservation, SessionData sessionData) {
		    double total = 0;
		    total = sessionData.getNumeroAdultos() * reservation.getPrice() + sessionData.getNumeroMenores() * 3.5;  
		    
		    return total;
	}
	 
	 private String butacas(SessionData sessionData, Reservation reservation) {
		    String seats = sessionData.getfButacasSelected();

		    if (seats != null && !seats.isEmpty()) {
		        seats = seats.replace(" ", "").trim();

		        String[] seatList = seats.split(";");

		        reservation.setButaca(seatList);

		        return String.join(", ", seatList);
		    }

		    return ""; 
		}

	 
	 
	 public String getMovieName(int filmId) throws SQLException {
			String filmName ="";			
			MySqlConnection connectionName = new MySqlConnection();
			 connectionName.open(); 			 
			 if(!connectionName.isError()) {
			        try {
		
					ResultSet resultado = connectionName.executeSelect("SELECT TITLE_RF AS PELICULA FROM DB_FILMCINEMA.REPOSITORY_FILM  WHERE IDFILM_RF = "+ filmId);
					
					while(resultado.next()) {
						
						filmName = resultado.getString("PELICULA");						
					}		
				
				} finally {
		            connectionName.close(); 
		        }
			 }
				
			return filmName;
		}

	 public String formatDate(Reservation reservation) {
		 String currentDate = reservation.getFecha();
		 String[] dateParts = currentDate.split("-");	 
		 return dateParts[2]+"/"+dateParts[1]+"/"+dateParts[0];
	 }

}


	
	
	


