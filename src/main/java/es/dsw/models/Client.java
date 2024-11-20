package es.dsw.models;

public class Client {

	private String nombre;	
	private String apellidos;
	private String email;
	private String re_email;
	private String ftitulartarjeta;
	private String fnumtarjeta;
	private String fMesCaduca; 
	private String fAnioCaduca; 
	private String fccstarjeta;
	private Double totalPagar;
	public Client(String nombre, String apellidos, String email, String re_email, String ftitulartarjeta,
			String fnumtarjeta, String fMesCaduca, String fAnioCaduca, String fccstarjeta, Double totalPagar) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.re_email = re_email;
		this.ftitulartarjeta = ftitulartarjeta;
		this.fnumtarjeta = fnumtarjeta;
		this.fMesCaduca = fMesCaduca;
		this.fAnioCaduca = fAnioCaduca;
		this.fccstarjeta = fccstarjeta;
		this.totalPagar = totalPagar;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRe_email() {
		return re_email;
	}
	public void setRe_email(String re_email) {
		this.re_email = re_email;
	}
	public String getFtitulartarjeta() {
		return ftitulartarjeta;
	}
	public void setFtitulartarjeta(String ftitulartarjeta) {
		this.ftitulartarjeta = ftitulartarjeta;
	}
	public String getFnumtarjeta() {
		return fnumtarjeta;
	}
	public void setFnumtarjeta(String fnumtarjeta) {
		this.fnumtarjeta = fnumtarjeta;
	}
	public String getfMesCaduca() {
		return fMesCaduca;
	}
	public void setfMesCaduca(String fMesCaduca) {
		this.fMesCaduca = fMesCaduca;
	}
	public String getfAnioCaduca() {
		return fAnioCaduca;
	}
	public void setfAnioCaduca(String fAnioCaduca) {
		this.fAnioCaduca = fAnioCaduca;
	}
	public String getFccstarjeta() {
		return fccstarjeta;
	}
	public void setFccstarjeta(String fccstarjeta) {
		this.fccstarjeta = fccstarjeta;
	}
	public Double getTotalPagar() {
		return totalPagar;
	}
	public void setTotalPagar(Double totalPagar) {
		this.totalPagar = totalPagar;
	}
	
	
	
 
	
	
}
