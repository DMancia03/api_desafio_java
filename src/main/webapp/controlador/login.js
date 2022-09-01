$(document).ready(function () {
    if(isValidString(localStorage.getItem('usuario_data'))){//window.location.pathname.includes("dependiente.html")){
        console.log(window.location.pathname);
        window.location.href = "dependiente.html";
    }

    $(".btnLogin").on("click", function(){
        let username = $("input[name='username']").val();
        let pass = $("input[name='password']").val();

        if(isValidString(username) && isValidString(pass)){
            login(username, pass);
        }else{
            Warning('Ingrese valos válidos en los campos');
        }
    });
});

//Login
function login(name, pass){
    let data = "username="+name+'&pass='+pass;
    let myHeaders = new Headers({
        'Content-Type': 'application/x-www-form-urlencoded'
    });
    let fetchData = {
        method: 'POST',
        body: data,
        headers: myHeaders
    };
    fetch('http://localhost:8080/dependienterest/rest/dependiente/login', fetchData)
    .then((resp) => resp.json())
    .then(function(data) {
        console.log(data);
        if(data.error == undefined || data.error == null || data.error == ""){
            Success('Bienvenido(a) ' + data.nombre, data);
        }else{
            Warning(data.error);
            localStorage.removeItem('usuario_data');
        }
    })
    .catch(function(error) {
        Warning(error);
    });
}

//Mensaje de error
function Warning(mensaje){
	Swal.fire({
		title: 'Error',
		html: mensaje,
		icon: 'error'
	});
}

//Mensaje de error
function Success(mensaje, data){
	Swal.fire({
		title: 'Login exitoso',
		html: mensaje,
		icon: 'success'
	}).then(resultado => {
        localStorage.setItem('usuario_data', JSON.stringify(data));
        window.location.href = "dependiente.html";
    });
}

//Validar string
function isValidString(texto){
	let valid = false;

	if(texto != "" && texto != " " && texto != undefined && texto != null){
		valid = true;
	}

	return valid;
}