package sv.cuponera.dependientes.model;

public class Empresa {
	private String IdEmpresa;
	private String Nombre;
	private String Ubicacion;
	private Double PorcentajeGanancia;
	private String Rubro;
	public String getIdEmpresa() {
		return IdEmpresa;
	}
	public void setIdEmpresa(String idEmpresa) {
		IdEmpresa = idEmpresa;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getUbicacion() {
		return Ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		Ubicacion = ubicacion;
	}
	public Double getPorcentajeGanancia() {
		return PorcentajeGanancia;
	}
	public void setPorcentajeGanancia(Double porcentajeGanancia) {
		PorcentajeGanancia = porcentajeGanancia;
	}
	public String getRubro() {
		return Rubro;
	}
	public void setRubro(String rubro) {
		Rubro = rubro;
	}
	
	
}
