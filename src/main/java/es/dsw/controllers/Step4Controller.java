package es.dsw.controllers;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import es.dsw.models.Ticket;

@Controller
@SessionAttributes({"reservation", "sessionData", "client", "tickets"})
public class Step4Controller {
	

	@GetMapping(value= {"/step4"})
	public String stp4 (Model model) throws SQLException {
		 Reservation reservation = (Reservation) model.getAttribute("reservation");
		 SessionData sessionData = (SessionData) model.getAttribute("sessionData");
		 
	        if (reservation == null || sessionData == null ) {      
	            return "redirect:/step1";
	        }
	       
			
		model.addAttribute(sessionData);
		model.addAttribute(reservation);
		
		return "views/step4";
	}
	
	 @PostMapping("/end")
	public String step4Post (@ModelAttribute Reservation reservation,
			 @ModelAttribute SessionData sessionData,
			 @ModelAttribute Client client, Model model, 
			 RedirectAttributes redirectAttributes) throws SQLException {

		 MySqlConnection connection = new MySqlConnection(false);
		    connection.open();

		    if (connection.isError()) {
		        redirectAttributes.addFlashAttribute("error", connection.msgError());
		        return "redirect:/step4";
		    }

		    try {
		        connection.open();

		        String insertQuery = "INSERT INTO DB_FILMCINEMA.BUYTICKETS_FILM (IDBUYTICKETS_BTF, NAME_BTF, SURNAMES_BTF, EMAIL_BTF, CARDHOLDER_BTF, " +
		                "CARDNUMBER_BTF, MONTHCARD_BTF, YEARCARD_BTF, CCS_CARD_CODE_BTF, TOTALPRICE_BTF, S_ACTIVEROW_BTF, S_INSERTDATE_BTF, " +
		                "S_UPDATEDATE_BTF, S_IDUSER_BTF) VALUES (NULL, '" + client.getNombre() + "', '" + client.getApellidos() + "', '" +
		                client.getEmail() + "', '" + client.getFtitulartarjeta() + "', '" + client.getFnumtarjeta() + "', '" + client.getfMesCaduca() + 
		                "', '" + client.getfAnioCaduca() + "', '" + client.getFccstarjeta() + "', " + client.getTotalPagar() + ", b'1', current_timestamp(), NULL, '1');";

		        ResultSet rs = connection.executeInsert(insertQuery);
		        long generatedId = 0;

		        if (rs != null && rs.next()) {
		            generatedId = rs.getLong(1); 
		            System.out.println("Registro insertado en BUYTICKETS_FILM con ID: " + generatedId);
		        } else {
		            throw new SQLException("No se pudo insertar en BUYTICKETS_FILM.");
		        }

		        int contadorMenores = 0; 
		        int contadorAdultos = 0; 
		        List<Ticket> tickets = new ArrayList<>();
		        String fila="";
		        String numButaca ="";
		        for (String butaca : reservation.getButaca()) {
		        	 double ticketPrice;
		        	 if (contadorMenores < sessionData.getNumeroMenores()) {
		        	        ticketPrice = 3.5;  
		        	        contadorMenores++;  
		        	    } else if (contadorAdultos < sessionData.getNumeroAdultos()) {
		        	        ticketPrice = reservation.getPrice();  
		        	        contadorAdultos++;  
		        	    } else {
		        	        throw new SQLException("Se ha superado el número de butacas asignadas.");
		        	    }

		            String serialCode = generateSerialCode(butaca, reservation); 

		            String insertSecondQuery = "INSERT INTO DB_FILMCINEMA.TICKET_FILM " +
		                    "(IDTICKET_TKF, IDSESSION_TKF, DATESESSION_TKF, TIMESESSION_TKF, SERIALCODE_TKF, YOUNGER_TKF, PRICE_TKF, ROWSEAT_TKF, " +
		                    "IDBUYTICKETS_TKF, S_ACTIVEROW_TKF, S_INSERTDATE_TKF, S_UPDATEDATE_TKF, S_IDUSER_TKF) " +
		                    "VALUES (NULL, '"+ sessionData.getSessionId()  +"', '" + reservation.getFecha() + "', '" + reservation.getHora() + "', '" + serialCode 
		                    + "', b'"+(ticketPrice == 3.5 ? 1 : 0)+"', " + ticketPrice + ", '" + butaca + "', " 
		                    + generatedId + ", b'1', current_timestamp(), NULL, '1');";
		            
		            System.out.println(insertSecondQuery);
		            ResultSet rsSecond = connection.executeInsert(insertSecondQuery);

		            if (rsSecond == null || !rsSecond.next()) {
		                throw new SQLException("No se pudo insertar el ticket para la butaca: " + butaca);
		            }
		            
		            Pattern pattern = Pattern.compile("F(\\d+)B(\\d+)");
		            Matcher matcher = pattern.matcher(butaca);

		            if (matcher.matches()) {
		                 fila = matcher.group(1); 
		                 numButaca = matcher.group(2); 

		                System.out.println("Fila: " + fila);
		                System.out.println("Butaca: " + butaca);
		            }
		            
		            Ticket ticket = new Ticket(serialCode, formatDate(reservation), reservation.getHora(), butaca, ticketPrice, fila, numButaca);
		           
		            tickets.add(ticket);		        
		            }

		        connection.commit();
		        System.out.println("Transacción completada correctamente.");
		        model.addAttribute("tickets", tickets);
		        
		    } catch (SQLException e) {
		        connection.rollback(); 
		        System.err.println("Error en la transacción: " + e.getMessage());
		    } finally {
		        connection.close(); 
		    }
		    return "views/end";
	}
	 
	 private String generateSerialCode(String butaca, Reservation reservation) {
		    String base = butaca + reservation.getFecha() + reservation.getHora();
		    int hash = Math.abs(base.hashCode()); 
		    return String.format("%016d", hash).substring(0, 16); 
		}
	 public String formatDate(Reservation reservation) {
		 String currentDate = reservation.getFecha();
		 String[] dateParts = currentDate.split("-");	 
		 return dateParts[2]+"/"+dateParts[1]+"/"+dateParts[0];
	 }
	 
	  	
}
