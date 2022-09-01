console.log(window.location.pathname);

if(!isValidString(localStorage.getItem('usuario_data'))){
    window.location.href = "index.html";
}else{
    let data = localStorage.getItem('usuario_data');
    console.log(data);
    data = JSON.parse(data);
    $("#nombre-usuario").html(data.nombre + " " + data.apellidos);
    $("#empresa-usuario").html(data.empresa.nombre);
    $("#rol-usuario").html(data.rol == "AdministradorE" ? "Administrador de empresa" : data.rol);
    $("#dui-usuario").html(data.dui);
    $("#username-usuario").html(data.username);
}

//Validar string
function isValidString(texto){
	let valid = false;

	if(texto != "" && texto != " " && texto != undefined && texto != null){
		valid = true;
	}

	return valid;
}