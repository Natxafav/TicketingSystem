package es.dsw.models;

public class SessionData {
	private Integer roomNumber;
    private Integer filmId;
    private Integer sessionId;
    private String filmName;
    private String fButacasSelected;
    private Integer numeroAdultos;
    private Integer numeroMenores;
    private Integer maxNumButacasToSelect;
	public SessionData(Integer roomNumber, Integer filmId, Integer sessionId, String filmName, String fButacasSelected,
			Integer maxNumButacasToSelect, Integer numeroAdultos, Integer numeroMenores) {
		super();
		this.roomNumber = roomNumber;
		this.filmId = filmId;
		this.sessionId = sessionId;
		this.filmName = filmName;
		this.fButacasSelected = fButacasSelected;
		this.numeroAdultos = numeroAdultos;
		this.numeroMenores = numeroMenores;
		this.maxNumButacasToSelect = this.numeroAdultos + this.numeroMenores;
	}
	public Integer getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Integer getFilmId() {
		return filmId;
	}
	public void setFilmId(Integer filmId) {
		this.filmId = filmId;
	}
	public Integer getSessionId() {
		return sessionId;
	}
	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}
	public String getFilmName() {
		return filmName;
	}
	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}
	public String getfButacasSelected() {
		return fButacasSelected;
	}
	public void setfButacasSelected(String fButacasSelected) {
		this.fButacasSelected = fButacasSelected;
	}
	public Integer getNumeroAdultos() {
		return numeroAdultos;
	}
	public void setNumeroAdultos(Integer numeroAdultos) {
		this.numeroAdultos = numeroAdultos;
		this.maxNumButacasToSelect = this.numeroAdultos + this.numeroMenores;
	}
	public Integer getNumeroMenores() {
		return numeroMenores;
	}
	public void setNumeroMenores(Integer numeroMenores) {
		this.numeroMenores = numeroMenores;
		this.maxNumButacasToSelect = this.numeroAdultos + this.numeroMenores;
	}
	
	public Integer getMaxNumButacasToSelect() {
		return maxNumButacasToSelect;
	}
	public void setMaxNumButacasToSelect(Integer maxNumButacasToSelect) {
		this.maxNumButacasToSelect =  this.numeroAdultos + this.numeroMenores;
	}
	
	
	
}
