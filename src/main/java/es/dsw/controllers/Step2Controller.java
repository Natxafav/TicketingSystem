package es.dsw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.dsw.models.Client;
import es.dsw.models.Reservation;
import es.dsw.models.SessionData;

@SessionAttributes({"reservation", "sessionData", "client"})
@Controller
public class Step2Controller {
      
	@GetMapping("/step2")
    public String step2(@RequestParam(defaultValue="") Integer filmId,
                        @RequestParam(defaultValue="") Integer salaId,
                        @RequestParam(defaultValue="") Integer sessionId,
                        Model model) {
		 SessionData sessionData = (SessionData) model.getAttribute("sessionData");
		    
		    if (sessionData == null) {
		        sessionData = new SessionData(0,0,0,"","",0,0,0);
		        model.addAttribute("sessionData", sessionData);
		    }

		    Reservation reservation = (Reservation) model.getAttribute("reservation");
        
	    if (filmId == null && sessionData.getFilmId() != null) {
	        filmId = sessionData.getFilmId();
	    }

	    if (salaId == null && sessionData.getRoomNumber() != null) {
	        salaId = sessionData.getRoomNumber();
	    }
	    if (sessionId == null && sessionData.getSessionId() != null) {
	    	sessionId = sessionData.getRoomNumber();
	    }
	    if (filmId == null || salaId == null) {
	        return "redirect:/step1";
	    }
	    
	    Client client = (Client) model.getAttribute("client");
        if (client == null) {
            client = new Client("","","","" ,"","","" ,"","",0.0);
            model.addAttribute("client", client);
        }
        
	    sessionData.setFilmId(filmId);
	    sessionData.setRoomNumber(salaId);
	    sessionData.setSessionId(sessionId);
	    
        if (reservation == null) {
        	String[] butacas = new String[0];
        	reservation = new Reservation("", "", "", butacas,false,0.0);
        	 model.addAttribute("reservation", reservation);
        } 
       
        
        model.addAttribute("reservation", reservation);
        model.addAttribute("sessionData", sessionData);
        model.addAttribute("client", client);
       
        if (model.containsAttribute("errorMessage")) {
            model.addAttribute("errorMessage", model.asMap().get("errorMessage"));
        }
        return "views/step2";
    }

    @PostMapping("/step3")
    public String step2Post(@ModelAttribute Client client,
    		@ModelAttribute SessionData sessionData,    		
    		@ModelAttribute Reservation reservation,
    		Model model,
    		RedirectAttributes redirectAttributes) {
        boolean hasErrors = false;
        StringBuilder errorMessage = new StringBuilder();
        
        sessionData = (SessionData) model.getAttribute("sessionData");
        
        Integer filmId = sessionData.getFilmId();
        Integer salaId = sessionData.getRoomNumber();

       
        if (client.getNombre() == null || client.getNombre().isEmpty()) {
            errorMessage.append("El campo 'Nombre' es obligatorio. ");
            redirectAttributes.addFlashAttribute("nombreError", true);
            hasErrors = true;
        }

        if (client.getEmail() == null || client.getEmail().isEmpty()) {
            errorMessage.append("El campo 'E-mail' es obligatorio. ");
            redirectAttributes.addFlashAttribute("emailError", true);
            hasErrors = true;
        }

        if (client.getRe_email() == null || client.getRe_email().isEmpty()) {
            errorMessage.append("El campo 'Repetir E-mail' es obligatorio. ");
            redirectAttributes.addFlashAttribute("reEmailError", true);
            hasErrors = true;
        }

        if (!client.getEmail().equalsIgnoreCase(client.getRe_email())) {
            errorMessage.append("Los emails deben coincidir.");
            redirectAttributes.addFlashAttribute("notSameEmailError", true);
            hasErrors = true;
        }

        if (reservation.getFecha() == null || reservation.getFecha().isEmpty()) {
            errorMessage.append("El campo 'Fecha' es obligatorio. ");
            redirectAttributes.addFlashAttribute("fechaError", true);
            hasErrors = true;
        }

        if (reservation.getHora() == null || reservation.getHora().isEmpty()) {
            errorMessage.append("El campo 'Hora' es obligatorio. ");
            redirectAttributes.addFlashAttribute("horaError", true);
            hasErrors = true;
        }

        Integer numAdultos = sessionData.getNumeroAdultos();
        if (numAdultos == null || numAdultos <= 0) {
            errorMessage.append("Debe haber al menos un adulto.");
            redirectAttributes.addFlashAttribute("adultosError", true);
            hasErrors = true;
        }

        if (hasErrors) {
            
            redirectAttributes.addFlashAttribute("reservation", reservation);
            redirectAttributes.addFlashAttribute("sessionData", sessionData);
            redirectAttributes.addFlashAttribute("client", client);
            return "redirect:/step2"; 
        }
     
        model.addAttribute("reservation", reservation);
        model.addAttribute("sessionData", sessionData);
        model.addAttribute("client", client);
        
        return "views/step3";
    }
}

