package sv.cuponera.dependientes.model;

import java.util.Date;

public class Cupon {
	private String CodigoCupon;
	private int IdUsuario;
	private int IdOferta;
	private Date FechaCompra;
	private String Estado;
	private Date FechaUso;
	private Usuario Usuario;
	private Oferta Oferta;
	
	public String getCodigoCupon() {
		return CodigoCupon;
	}
	public void setCodigoCupon(String codigoCupon) {
		CodigoCupon = codigoCupon;
	}
	public int getIdUsuario() {
		return IdUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		IdUsuario = idUsuario;
	}
	public int getIdOferta() {
		return IdOferta;
	}
	public void setIdOferta(int idOferta) {
		IdOferta = idOferta;
	}
	public Date getFechaCompra() {
		return FechaCompra;
	}
	public void setFechaCompra(Date fechaCompra) {
		FechaCompra = fechaCompra;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public Date getFechaUso() {
		return FechaUso;
	}
	public void setFechaUso(Date fechaUso) {
		FechaUso = fechaUso;
	}
	public Usuario getUsuario() {
		return Usuario;
	}
	public void setUsuario(Usuario usuario) {
		Usuario = usuario;
	}
	public Oferta getOferta() {
		return Oferta;
	}
	public void setOferta(Oferta oferta) {
		Oferta = oferta;
	}
	
	
}
