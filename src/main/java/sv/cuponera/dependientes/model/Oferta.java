package sv.cuponera.dependientes.model;

import java.util.Date;

public class Oferta {
	private int IdOferta;
	private String Nombre;
	private String Descripcion;
	private Double PrecioRegular;
	private Double PrecioOfertado;
	private Date Inicio;
	private Date Fin;
	private Date FechaLimite;
	private int Limite;
	private String OfertaCol;
	private String Estado;
	private String IdEmpresa;
	private Empresa Empresa;
	public int getIdOferta() {
		return IdOferta;
	}
	public void setIdOferta(int idOferta) {
		IdOferta = idOferta;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public Double getPrecioRegular() {
		return PrecioRegular;
	}
	public void setPrecioRegular(Double precioRegular) {
		PrecioRegular = precioRegular;
	}
	public Double getPrecioOfertado() {
		return PrecioOfertado;
	}
	public void setPrecioOfertado(Double precioOfertado) {
		PrecioOfertado = precioOfertado;
	}
	public Date getInicio() {
		return Inicio;
	}
	public void setInicio(Date inicio) {
		Inicio = inicio;
	}
	public Date getFin() {
		return Fin;
	}
	public void setFin(Date fin) {
		Fin = fin;
	}
	public Date getFechaLimite() {
		return FechaLimite;
	}
	public void setFechaLimite(Date fechaLimite) {
		FechaLimite = fechaLimite;
	}
	public int getLimite() {
		return Limite;
	}
	public void setLimite(int limite) {
		Limite = limite;
	}
	public String getOfertaCol() {
		return OfertaCol;
	}
	public void setOfertaCol(String ofertaCol) {
		OfertaCol = ofertaCol;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public String getIdEmpresa() {
		return IdEmpresa;
	}
	public void setIdEmpresa(String idEmpresa) {
		IdEmpresa = idEmpresa;
	}
	public Empresa getEmpresa() {
		return Empresa;
	}
	public void setEmpresa(Empresa empresa) {
		Empresa = empresa;
	}
	
	
}
