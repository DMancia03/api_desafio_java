package sv.cuponera.dependientes.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sv.edu.udb.model.Concepto;

public class DependienteDAO extends AppConnection {
	//Listar los objetos de dependiente
	public ArrayList<Usuario> listarDependiente() throws SQLException{
		connect();
		stmt = conn.createStatement();
		resultSet = stmt.executeQuery("SELECT idUsuario, Username, Nombre, Apellidos, Pass, Email, Roles_idRoles, Empresa_IdEmpresa FROM usuarios");
		ArrayList<Usuario> dependientes = new ArrayList();

		while(resultSet.next()){
		 Usuario tmp = new Usuario();
		 tmp.setIdUsuario(resultSet.getInt(1));
		 tmp.setUsername(resultSet.getString(2));
		 tmp.setNombre(resultSet.getString(3));
		 tmp.setApellidos(resultSet.getString(4));
		 tmp.setPass(resultSet.getString(5));
		 tmp.setEmail(resultSet.getString(6));
		 tmp.setIdRoles(resultSet.getInt(7));
		 tmp.setIdEmpresa(resultSet.getString(8));
		 dependientes.add(tmp);
		}

		close();

		return dependientes;
	}
	
	//Obtener los datos del usuario al que corresponda el usuario y password
	public Usuario login(String username, String pass) throws SQLException{

		Usuario dependiente = null;

		connect();
		pstmt = conn.prepareStatement("SELECT idUsuario, Username, usuarios.Nombre, Apellidos, Pass, Email, roles.Nombre, Empresa_IdEmpresa, DUI FROM usuarios INNER JOIN roles ON usuarios.Roles_idRoles = roles.idRoles WHERE Username = ? AND Pass = ?");
		pstmt.setString(1, username);
		pstmt.setString(2, pass);

		resultSet = pstmt.executeQuery();

		while(resultSet.next()){
			dependiente = new Usuario();
			dependiente.setIdUsuario(resultSet.getInt(1));
			dependiente.setUsername(resultSet.getString(2));
			dependiente.setNombre(resultSet.getString(3));
			dependiente.setApellidos(resultSet.getString(4));
			dependiente.setPass(resultSet.getString(5));
			dependiente.setEmail(resultSet.getString(6));
			dependiente.setRol(resultSet.getString(7));
			dependiente.setIdEmpresa(resultSet.getString(8));
			dependiente.setDui(resultSet.getString(9));
		}

		close();
		return dependiente;
	}
	
	//Cambiar contraseña
	public void cambiarContrasenia(Usuario dependiente) throws SQLException{
		 connect();
		 pstmt = conn.prepareStatement("update concepto set nombre = ? , valor = ? , categoria_id = ? where id = ?");
		 pstmt.setString(1, dependiente.getPass());
		 pstmt.executeUpdate();
		 close();
	}
	
	//Obtener cupones
	public List<Cupon> obtenerCupones(String codigoCupon, String empresa) throws SQLException{

		List<Cupon> cupones = new ArrayList<Cupon>();

		connect();
		pstmt = conn.prepareStatement("SELECT CodigoCupon, FechaCompra, fechUso, cupon.Estado, Usuarios_idUsuario, Oferta_idOferta FROM cupon INNER JOIN oferta ON oferta.idOferta = Oferta_idOferta WHERE CodigoCupon = ? AND oferta.Empresa_IdEmpresa = ?");
		pstmt.setString(1, codigoCupon);
		pstmt.setString(2, empresa);
		resultSet = pstmt.executeQuery();

		while(resultSet.next()){
			Cupon cupon = new Cupon();
			cupon.setCodigoCupon(resultSet.getString(1));
			cupon.setFechaCompra(resultSet.getDate(2));
			cupon.setFechaUso(resultSet.getDate(3));
			cupon.setEstado(resultSet.getString(4));
			cupon.setIdUsuario(resultSet.getInt(5));
			cupon.setIdOferta(resultSet.getInt(6));
			cupones.add(cupon);
		}

		close();
		return cupones;
	}
	
	public List<Cupon> obtenerCuponesPorEmpresa(String empresa) throws SQLException{

		List<Cupon> cupones = new ArrayList<Cupon>();

		connect();
		pstmt = conn.prepareStatement("SELECT CodigoCupon, FechaCompra, fechUso, cupon.Estado, Usuarios_idUsuario, Oferta_idOferta FROM cupon INNER JOIN oferta ON oferta.idOferta = Oferta_idOferta WHERE oferta.Empresa_IdEmpresa = ?");
		pstmt.setString(1, empresa);
		resultSet = pstmt.executeQuery();

		while(resultSet.next()){
			Cupon cupon = new Cupon();
			cupon.setCodigoCupon(resultSet.getString(1));
			cupon.setFechaCompra(resultSet.getDate(2));
			cupon.setFechaUso(resultSet.getDate(3));
			cupon.setEstado(resultSet.getString(4));
			cupon.setIdUsuario(resultSet.getInt(5));
			cupon.setIdOferta(resultSet.getInt(6));
			cupones.add(cupon);
		}

		close();
		return cupones;
	}
	
	public List<Cupon> obtenerCuponesPorUsuario(String empresa, String dui) throws SQLException{

		List<Cupon> cupones = new ArrayList<Cupon>();

		connect();
		pstmt = conn.prepareStatement("SELECT CodigoCupon, FechaCompra, fechUso, cupon.Estado, Usuarios_idUsuario, Oferta_idOferta FROM cupon INNER JOIN usuarios ON usuarios.idUsuario = cupon.Usuarios_idUsuario INNER JOIN oferta ON oferta.idOferta = cupon.Oferta_idOferta WHERE oferta.Empresa_IdEmpresa = ? AND usuarios.DUI = ?");
		pstmt.setString(1, empresa);
		pstmt.setString(2, dui);
		resultSet = pstmt.executeQuery();

		while(resultSet.next()){
			Cupon cupon = new Cupon();
			cupon.setCodigoCupon(resultSet.getString(1));
			cupon.setFechaCompra(resultSet.getDate(2));
			cupon.setFechaUso(resultSet.getDate(3));
			cupon.setEstado(resultSet.getString(4));
			cupon.setIdUsuario(resultSet.getInt(5));
			cupon.setIdOferta(resultSet.getInt(6));
			cupones.add(cupon);
		}

		close();
		return cupones;
	}
	
	//Obtener estado del cupon
	public String obtenerEstadoCupon(String codigoCupon) throws SQLException{

		String estado = "";

		connect();
		pstmt = conn.prepareStatement("SELECT Estado FROM cupon WHERE CodigoCupon = ?");
		pstmt.setString(1, codigoCupon);

		resultSet = pstmt.executeQuery();

		while(resultSet.next()){
			estado = resultSet.getString(1);
		}

		close();
		return estado;
	}
	
	//Obtener la empresa del usuario
	public Empresa obtenerEmpresa(String codigoEmpresa) throws SQLException{

		Empresa empresa = null;

		connect();
		pstmt = conn.prepareStatement("SELECT IdEmpresa, NombreEmpresa, Ubicacion, PorcentajeGanancias, Nombre FROM empresa INNER JOIN rubros ON empresa.Rubros_IdRubros = rubros.IdRubros WHERE IdEmpresa = ?");
		pstmt.setString(1, codigoEmpresa);

		resultSet = pstmt.executeQuery();

		while(resultSet.next()){
			empresa = new Empresa();
			empresa.setIdEmpresa(resultSet.getString(1));
			empresa.setNombre(resultSet.getString(2));
			empresa.setUbicacion(resultSet.getString(3));
			empresa.setPorcentajeGanancia(resultSet.getDouble(4));
			empresa.setRubro(resultSet.getString(5));
		}

		close();
		return empresa;
	}
	
	//Obtener cliente
	//Obtener los datos del usuario al que corresponda el usuario y password
	public Usuario obtenerCliente(int id) throws SQLException{

			Usuario dependiente = null;

			connect();
			pstmt = conn.prepareStatement("SELECT idUsuario, Username, Nombre, Apellidos, Pass, Email, Roles_idRoles, Empresa_IdEmpresa, DUI FROM usuarios WHERE idUsuario = ?");
			pstmt.setInt(1, id);

			resultSet = pstmt.executeQuery();

			while(resultSet.next()){
				dependiente = new Usuario();
				dependiente.setIdUsuario(resultSet.getInt(1));
				dependiente.setUsername(resultSet.getString(2));
				dependiente.setNombre(resultSet.getString(3));
				dependiente.setApellidos(resultSet.getString(4));
				dependiente.setPass(resultSet.getString(5));
				dependiente.setEmail(resultSet.getString(6));
				dependiente.setIdRoles(resultSet.getInt(7));
				dependiente.setIdEmpresa(resultSet.getString(8));
				dependiente.setDui(resultSet.getString(9));
			}

			close();
			return dependiente;
	}
	
	//ObtenerOferta
	public Oferta obtenerOferta(int id) throws SQLException{

		Oferta oferta = null;

		connect();
		pstmt = conn.prepareStatement("SELECT * FROM oferta WHERE idOferta = ?");
		pstmt.setInt(1, id);

		resultSet = pstmt.executeQuery();

		while(resultSet.next()){
			oferta = new Oferta();
			oferta.setIdOferta(resultSet.getInt(1));
			oferta.setNombre(resultSet.getString(2));
			oferta.setDescripcion(resultSet.getString(3));
			oferta.setPrecioRegular(resultSet.getDouble(4));
			oferta.setPrecioOfertado(resultSet.getDouble(5));
			oferta.setInicio(resultSet.getDate(6));
			oferta.setFin(resultSet.getDate(7));
			oferta.setFechaLimite(resultSet.getDate(8));
			oferta.setLimite(resultSet.getInt(9));
			oferta.setOfertaCol(resultSet.getString(10));
			oferta.setEstado(resultSet.getString(11));
			oferta.setIdEmpresa(resultSet.getString(12));
		}

		close();
		return oferta;
	}
	
	public void canjearCupon(String codigo, String dui) throws SQLException {
		connect();
		//pstmt = conn.prepareStatement("SELECT * FROM cupon WHERE CodigoCupon = ? AND Usuarios_idUsuario = (SELECT usuarios.idUsuario FROM usuarios WHERE DUI = ?)");
		pstmt = conn.prepareStatement("UPDATE cupon SET Estado = 'Canjeado', fechUso = CONCAT(YEAR(NOW()), '-', MONTH(NOW()), '-', DAY(NOW())) WHERE CodigoCupon = ? AND Usuarios_idUsuario = (SELECT usuarios.idUsuario FROM usuarios WHERE DUI = ?)");
		pstmt.setString(1, codigo);
		pstmt.setString(2, dui);
		pstmt.execute();
		close();
	}
}
