package sv.cuponera.dependientes.model;

public class Usuario {
	private int IdUsuario;
	private String Username;
	private String Nombre;
	private String Apellidos;
	private String Pass;
	private String Email;
	private int IdRoles;
	private String IdEmpresa;
	private String Dui;
	private Empresa Empresa;
	private String rol;
	
	public int getIdUsuario() {
		return IdUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		IdUsuario = idUsuario;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellidos() {
		return Apellidos;
	}
	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}
	public String getPass() {
		return Pass;
	}
	public void setPass(String pass) {
		Pass = pass;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getDui() {
		return Dui;
	}
	public void setDui(String dui) {
		Dui = dui;
	}
	public Empresa getEmpresa() {
		return Empresa;
	}
	public void setEmpresa(Empresa empresa) {
		Empresa = empresa;
	}
	public int getIdRoles() {
		return IdRoles;
	}
	public void setIdRoles(int idRoles) {
		IdRoles = idRoles;
	}
	public String getIdEmpresa() {
		return IdEmpresa;
	}
	public void setIdEmpresa(String idEmpresa) {
		IdEmpresa = idEmpresa;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
	
	
}
