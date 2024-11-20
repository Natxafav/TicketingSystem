package es.dsw.models;

public class Ticket {
	private String serialCode;
    private String fecha;
    private String hora;
    private String butaca;
    private double price;
    private String qrCode;
    private String fila; 
    private String numButaca;
	public Ticket(String serialCode, String fecha, String hora, String butaca, double price, String fila, String numButaca) {
		super();
		this.serialCode = serialCode;
		this.fecha = fecha;
		this.hora = hora;
		this.butaca = butaca;
		this.price = price;		
		this.fila = fila; 
		this.numButaca = numButaca;
	}
	
	public String getFila() {
		return fila;
	}

	public void setFila(String fila) {
		this.fila = fila;
	}

	public String getNumButaca() {
		return numButaca;
	}

	public void setNumButaca(String numButaca) {
		this.numButaca = numButaca;
	}

	public String getSerialCode() {
		return serialCode;
	}
	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
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
	public String getButaca() {
		return butaca;
	}
	public void setButaca(String butaca) {
		this.butaca = butaca;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
    
    
    
    
}
