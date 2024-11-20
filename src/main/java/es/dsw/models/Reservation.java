package es.dsw.models;

public class Reservation {

	private String fecha; 
	private String hora;
	private String serialCode; 
	private  String[] butaca;	
	private Boolean menor; 
	private Double price;
	public Reservation(String fecha, String hora, String serialCode,  String[] butaca, Boolean menor, Double price) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.serialCode = serialCode;
		this.butaca = butaca;
		this.menor = menor;
		this.price = price;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getSerialCode() {
		return serialCode;
	}
	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
	}
	public  String[] getButaca() {
		return butaca;
	}
	public void setButaca(String[] butaca) {
		this.butaca = butaca;
	}
	public Boolean getMenor() {
		return menor;
	}
	public void setMenor(Boolean menor) {
		this.menor = menor;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
	
	
	
	
	
	
}
