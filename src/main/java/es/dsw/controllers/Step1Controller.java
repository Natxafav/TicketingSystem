package es.dsw.controllers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.dsw.connection.MySqlConnection;
import es.dsw.models.Reservation;
import es.dsw.models.SessionData;

@Controller
@SessionAttributes({"reservation", "sessionData", "client"})
public class Step1Controller {
	 
    
	@GetMapping(value = { "step1", "/step1" })
	public String step1(Model model) throws SQLException {
		
		 DayOfWeek calculatedDay = LocalDate.now().getDayOfWeek();        
	        int roomCinemaNumber = roomCinemaNumber(calculatedDay);
	       
		
		List<SessionData> activeSessions = new ArrayList<>();
		MySqlConnection connection = new MySqlConnection();
		 connection.open(); 
		 if(!connection.isError()) {
		        try {
		
				ResultSet resultado = connection.executeSelect(" SELECT NUMBERROOM_RCF AS NUMSALA,\r\n"
						+ "               IDFILM_SSF AS IDPELICULA,\r\n"
						+ "               IDSESSION_SSF AS IDSESION\r\n"
						+ " FROM DB_FILMCINEMA.SESSION_FILM,\r\n"
						+ "            DB_FILMCINEMA.ROOMCINEMA_FILM\r\n"
						+ " WHERE S_ACTIVEROW_SSF = 1 AND\r\n"
						+ "              IDROOMCINEMA_RCF = IDROOMCINEMA_SSF AND\r\n"
						+ "              S_ACTIVEROW_RCF = 1\r\n"
						+ " ORDER BY NUMBERROOM_RCF ASC");
				
				while(resultado.next()) {
					
					int roomNumber = resultado.getInt("NUMSALA");
					int filmId = resultado.getInt("IDPELICULA");
					int sessionId = resultado.getInt("IDSESION");
					activeSessions.add(new SessionData (roomNumber, filmId, sessionId,"", "", 0,0,0));
				}
				
			
			} finally {
	            connection.close(); 
	        }
		 }
		if (!model.containsAttribute("reservation") || 
			    ((Reservation) model.getAttribute("reservation")).getPrice() == null) {
			double price = calculatedPrice(calculatedDay);
			String[] butacas = new String[0];
            Reservation reservation = new Reservation("", "", "", butacas,false,price);
            model.addAttribute("reservation", reservation);
        }
		
		Collections.shuffle(activeSessions);
		activeSessions = activeSessions.subList(0, roomCinemaNumber);
		model.addAttribute("activeSessions", activeSessions);
        model.addAttribute("numSalas", roomCinemaNumber);
      
		return "views/step1";		
	}		
		
		private  int roomCinemaNumber (DayOfWeek calculatedDay) {
			 switch(calculatedDay) {
			  	case MONDAY, WEDNESDAY,SUNDAY: return  4;
			    default:  	return 7;			   
			 }
			
	}
	
			
	private double calculatedPrice(DayOfWeek calculatedDay) {
		return (calculatedDay == DayOfWeek.WEDNESDAY)? 3.5: 6.0;
		
	}
	

}
