package sv.cuponera.dependientes.dependienterest;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sv.cuponera.dependientes.model.*;
import java.util.ArrayList;
import java.util.List;

@Path("dependiente")
public class DependienteRest {
	DependienteDAO dependienteDAO = new DependienteDAO();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarConceptos() throws SQLException{

	 List<Usuario> dependientes = dependienteDAO.listarDependiente();
	 return Response.status(200).entity(dependientes).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("login")
	public Response login( @FormParam("username") String username,@FormParam("pass") String pass) throws SQLException{
		 
		 Usuario dependiente = dependienteDAO.login(username, pass);
		 
		 if(dependiente==null){
			 return Response.status(400).header("Access-Control-Allow-Origin", "*").entity("{\"error\":\"Usuario y contraseña incorrecto\"}").build();
		 }
		 dependiente.setEmpresa(dependienteDAO.obtenerEmpresa(dependiente.getIdEmpresa()));
		 
		 if(!dependiente.getRol().equals("Dependiente") && !dependiente.getRol().equals("AdministradorE")) {
			 return Response.status(400).header("Access-Control-Allow-Origin", "*").entity("{\"error\":\"Usuario no pertenece a un dependiente de empresa\"}").build();
		 }
		 
		 return Response.status(201)
				 .header("Access-Control-Allow-Origin", "*")
				 .entity(dependiente)
				 .build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("cupon")
	public Response obtenerCupon( @FormParam("codigo") String codigoCupon, @FormParam("tipoFiltro") String tipo, @FormParam("filtro") String filtro) throws SQLException{
		List<Cupon> cupones = new ArrayList<Cupon>();
		if(tipo.equals("cod")) {
			cupones = dependienteDAO.obtenerCupones(codigoCupon, filtro);
		}else if(tipo.equals("empresa")) {
			cupones = dependienteDAO.obtenerCuponesPorEmpresa(filtro);
		}
		else if(tipo.equals("usuario")) {
			cupones = dependienteDAO.obtenerCuponesPorUsuario(codigoCupon, filtro);
		}
		else if(tipo.equals("oferta")) {
			cupones = dependienteDAO.obtenerCuponesPorEmpresa(filtro);
		}
		else {
			cupones = dependienteDAO.obtenerCupones(codigoCupon, filtro);
		}
		 
		if(cupones.size() == 0){
			return Response.status(400).header("Access-Control-Allow-Origin", "*").entity("{\"error\":\"Cupón no existe\"}").build();
		}
		for(Cupon cupon : cupones){
			cupon.setUsuario(dependienteDAO.obtenerCliente(cupon.getIdUsuario()));
			cupon.setOferta(dependienteDAO.obtenerOferta(cupon.getIdOferta()));
			cupon.getOferta().setEmpresa(dependienteDAO.obtenerEmpresa(cupon.getOferta().getIdEmpresa()));
		}
		
		//cupon.setEmpresa(dependienteDAO.obtenerEmpresa(dependiente.getIdEmpresa()));
		 
		return Response.status(201)
				 .header("Access-Control-Allow-Origin", "*")
				 .entity(cupones)
				 .build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("empresa")
	public Response obtenerEmpresa( @FormParam("codigo") String codigoEmpresa) throws SQLException{
		 
		Empresa empresa = dependienteDAO.obtenerEmpresa(codigoEmpresa);
		 
		if(empresa==null){
			return Response.status(400).header("Access-Control-Allow-Origin", "*").entity("Código de cupón incorrecto").build();
		}
		 
		return Response.status(201)
				 .header("Access-Control-Allow-Origin", "*")
				 .entity(empresa)
				 .build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("canjeo")
	public Response canjeo( @FormParam("codigo") String codigo,@FormParam("dui") String dui, @FormParam("empresa") String empresa) throws SQLException{
		String estadoCupon = dependienteDAO.obtenerEstadoCupon(codigo);
		
		if(estadoCupon.equals("Vencido")) {
			return Response.status(400)
					.header("Access-Control-Allow-Origin", "*")
					.entity("{\"error\":\"El cupón " + codigo + " esta vencido\"}")
					.build();
		}else if(estadoCupon.equals("Canjeado")) {
			return Response.status(400)
					.header("Access-Control-Allow-Origin", "*")
					.entity("{\"error\":\"El cupón " + codigo + " ya esta canjeado\"}")
					.build();
		}else if(estadoCupon.equals("Disponible")) {
			dependienteDAO.canjearCupon(codigo, dui);
			
			List<Cupon> cupones = dependienteDAO.obtenerCupones(codigo, empresa);
			 
			if(cupones.size() == 0){
				return Response.status(400).header("Access-Control-Allow-Origin", "*").entity("{\"error\":\"Cupón no se pudo recuperar\"}").build();
			}
			 
			for(Cupon cupon : cupones) {
				cupon.setUsuario(dependienteDAO.obtenerCliente(cupon.getIdUsuario()));
				cupon.setOferta(dependienteDAO.obtenerOferta(cupon.getIdOferta()));
				cupon.getOferta().setEmpresa(dependienteDAO.obtenerEmpresa(cupon.getOferta().getIdEmpresa()));
			}
				 
			return Response.status(201)
						 .header("Access-Control-Allow-Origin", "*")
						 .entity(cupones)
						 .build();
		}else {
			return Response.status(400)
					.header("Access-Control-Allow-Origin", "*")
					.entity("{\"error\":\"El cupón " + codigo + " no tiene un estado disponible\"}")
					.build();
		}
	}
}
