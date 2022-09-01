//Obtener la empresa del usuario activo
let data = localStorage.getItem('usuario_data');
data = JSON.parse(data);
let empresa = data.empresa.idEmpresa;

//Al cargar el documento
$(document).ready(function () {
    //Boton para obtener los cupones por codigo del cupon
    $("#btnObtenerCuponCodigo").on("click", function(){
        Swal.fire({
            title: 'Buscar cupón por código',
            input: 'text',
            inputLabel: 'Ingrese el código del cupón para cargar sus datos',
            showCancelButton: true,
            cancelButtonText: 'Cancelar'
          }).then(result => {
            if(result.isConfirmed){
                let codigo = result.value;
                if(codigo != "" && codigo != " " && codigo != undefined && codigo != null){
                    obtenerCupon(codigo, "cod", empresa);
                }else{
                    Warning('No ingreso un código válido');
                }
            }
          });
    });

    //Boton para obtener los cupones por la empresa del usuario
    $("#btnObtenerCuponEmpresa").on("click", function(){
        let codigo = "";
        obtenerCupon(codigo, "empresa", empresa);
    });

    //Boton para obtener los cupones por el dui del cliente
    $("#btnObtenerCuponUsuario").on("click", function(){
        Swal.fire({
            title: 'Buscar cupón',
            input: 'text',
            inputLabel: 'Ingrese el DUI del cliente para recuperar sus cupones',
            showCancelButton: true,
            cancelButtonText: 'Cancelar'
          }).then(result => {
            if(result.isConfirmed){
                let dui = result.value;
                if(isValidString(dui) && !isNaN(dui) && dui.length == 9){
                    obtenerCupon(empresa, "usuario", dui);
                }else{
                    Warning('No ingreso un DUI válido');
                }
            }
          });
    });

    //Boton para mostrar las opciones del canje de cupones
    $("#btnCanjearCupones").on("click", function(){
        $("#btnVerMiInformacion").removeClass("hidden");
        $("#btnObtenerCuponCodigo").removeClass("hidden");
        $("#btnObtenerCuponEmpresa").removeClass("hidden");
        $("#btnObtenerCuponUsuario").removeClass("hidden");
        $("#btnCanjearCupon").removeClass("hidden");
        $("#btnCanjearCupones").addClass("hidden");
        $("#cupon").removeClass("hidden");
        $("#usuario-data").addClass("hidden");
    });

    //Boton para ocultar las opciones del canje de cupones
    $("#btnVerMiInformacion").on("click", function(){
        $("#btnVerMiInformacion").addClass("hidden");
        $("#btnObtenerCuponCodigo").addClass("hidden");
        $("#btnObtenerCuponEmpresa").addClass("hidden");
        $("#btnObtenerCuponUsuario").addClass("hidden");
        $("#btnCanjearCupon").addClass("hidden");
        $("#btnCanjearCupones").removeClass("hidden");
        $("#cupon").addClass("hidden");
        $("#usuario-data").removeClass("hidden");
    });
});

//Obtener cupon por diferentes parametros
function obtenerCupon(codigo, tipo, empresa){
    let data = "codigo="+codigo+"&tipoFiltro="+tipo+"&filtro="+empresa;
    let myHeaders = new Headers({
        'Content-Type': 'application/x-www-form-urlencoded'
    });
    let fetchData = {
        method: 'POST',
        body: data,
        headers: myHeaders
    };
    fetch('http://localhost:8080/dependienterest/rest/dependiente/cupon', fetchData)
    .then((resp) => resp.json())
    .then(function(data) {
        console.log(data);
        if(data.error == undefined || data.error == null || data.error == ""){
            Success('Datos del cupón recuperados correctamente', data);
        }else{
            Warning(data.error);
            setCuponNull();
        }
    })
    .catch(function(error) {
        Warning(error);
        setCuponNull();
    });
}

//Canjear codigo
function canjearCupon(codigo, dui, empresa){
    let data = "codigo="+codigo+"&dui="+dui+"&empresa="+empresa;
    let myHeaders = new Headers({
        'Content-Type': 'application/x-www-form-urlencoded'
    });
    let fetchData = {
        method: 'POST',
        body: data,
        headers: myHeaders
    };
    fetch('http://localhost:8080/dependienterest/rest/dependiente/canjeo', fetchData)
    .then((resp) => resp.json())
    .then(function(data) {
        console.log(data);
        if(data.error == undefined || data.error == null || data.error == ""){
            //data[0].estado == "Canjear"
            console.log(data[0].estado);
            if(data[0].estado == "Canjeado"){
                Success('Cupón canjeado correctamente', data);
            }else{
                Warning('El copún no se pudo canjear');
                setCupon(data);
            }
        }else{
            Warning(data.error);
        }
    })
    .catch(function(error) {
        Warning(error);
    });
}

//Establecer datos del cupon
function setCupon(cupones){
    let html = '';
    $.each(cupones, function(index, cupon){
        html += `
    <div class="p-5 bg-white flex items-center mx-auto border-b  mb-10 border-gray-200 rounded-lg sm:flex-row flex-col rounded-md shadow-md">
                        <div class="sm:w-32 sm:h-32 h-20 w-20 sm:mr-10 inline-flex items-center justify-center flex-shrink-0">
                            <img
                              src="https://cdn-icons-png.flaticon.com/512/2851/2851418.png"/>
                        </div>
                        <div class="flex-grow sm:text-left text-center mt-6 sm:mt-0">
                            <h1 class="text-black text-2xl title-font font-bold mb-2">${cupon.oferta.nombre}</h1>
                            <p class="leading-relaxed text-base">${cupon.oferta.descripcion}</p>
                    
                            <div class="md:flex font-bold text-gray-800">
                                <div class="w-full md:w-1/2 flex space-x-3">
                                    <div class="w-1/2">
                                        <h2 class="text-gray-500">Fecha de Compra</h2>
                                        <p>${cupon.fechaCompra}</p>
                                    </div>
                                    <div class="w-1/2">
                                        <h2 class="text-gray-500">Fecha Limite de uso</h2>
                                        <p>${cupon.oferta.fechaLimite}</p>
                                    </div>
                                </div>
                                <div class="w-full md:w-1/2 flex space-x-3">
                                    <div class="w-1/2">
                                        <h2 class="text-gray-500">Estado</h2>
                                        <p>${cupon.estado}</p>
                                    </div>
                                    <div class="w-1/2">
                                        <h2 class="text-gray-500">Código</h2>
                                        <p>${cupon.codigoCupon}</p>
                                    </div>
                                </div>
                                <div class="w-full md:w-1/2 flex space-x-3">
                                    <div class="w-1/2">
                                        <h2 class="text-gray-500">Pertenece a:</h2>
                                        <p>${cupon.usuario.nombre + " " + cupon.usuario.apellidos}</p>
                                    </div>
                                    <div class="w-1/2">
                                        <h2 class="text-gray-500">Se puede usar en:</h2>
                                        <p>${cupon.oferta.empresa.nombre}</p>
                                    </div>
                                </div>
                            </div>
                            <a class="mt-3 text-indigo-500 inline-flex items-center canjear-cupon" codigo="${cupon.codigoCupon}" style="cursor:pointer;">Canjear cupón</a>
                        </div>
                    </div>
    `;
    });

    $("#cupones").html(html);
    /*$("#codigo-cupon").html(cupon.codigoCupon);
    $("#nombre-oferta").html(cupon.oferta.nombre);
    $("#descripcion-oferta").html(cupon.oferta.descripcion);
    $("#precioregular-oferta").html("$"+cupon.oferta.precioRegular);
    $("#preciofertado-oferta").html("$"+cupon.oferta.precioOfertado);
    $("#estado-cupon").html(cupon.estado);
    $("#duenio-cupon").html(cupon.usuario.nombre + " " + cupon.usuario.apellidos);
    $("#dui-usuario").html(cupon.usuario.dui);
    $("#fechalimite-oferta").html(cupon.oferta.fechaLimite);
    $("#empresa-oferta").html(cupon.oferta.empresa.nombre);
    $("#fechacompra-oferta").html(cupon.fechaCompra);*/
}

function setCuponNull(){
    let html = `
    <div class="p-5 bg-white flex items-center mx-auto border-b  mb-10 border-gray-200 rounded-lg sm:flex-row flex-col rounded-md shadow-md">
                        <p>No hay cupones buscados</p>
                    </div>
                </div>`;
    $("#cupones").html(html);
    /*$("#codigo-cupon").html("");
    $("#nombre-oferta").html("Busque un cupón ingresando su código");
    $("#descripcion-oferta").html("");
    $("#precioregular-oferta").html("$0");
    $("#preciofertado-oferta").html("$0");
    $("#estado-cupon").html("");
    $("#duenio-cupon").html("");
    $("#dui-usuario").html("");
    $("#fechalimite-oferta").html("");
    $("#empresa-oferta").html("");
    $("#fechacompra-oferta").html("");*/
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
function Success(mensaje, data = ""){
    let data2 = localStorage.getItem('usuario_data');
    data2 = JSON.parse(data2);
    let empresa = data2.empresa.idEmpresa;

	Swal.fire({
		title: 'Acción exitosa',
		html: mensaje,
		icon: 'success'
	}).then(resultado => {
        setCupon(data);
        $(".canjear-cupon").on("click", function(){
            Swal.fire({
                title: 'Canjeo de cupones',
                input: 'text',
                inputLabel: 'Por seguridad, ingresa el DUI del cliente para poder canjear el cupón',
                showCancelButton: true,
                cancelButtonText: 'Cancelar'
              }).then(result => {
                if(result.isConfirmed){
                    let dui = result.value;
                    console.log(dui);
                    console.log(dui.length);
                    if(isValidString(dui) && !isNaN(dui) && dui.length == 9){
                        let codigo = $(this).attr("codigo");
                        console.log(codigo);
                        if(isValidString(codigo)){
                            canjearCupon(codigo, dui, empresa);
                        }else{
                            Warning('Primero debe buscar un cupón para canjearlo')
                        }
                    }else{
                        Warning('No ingreso un DUI válido');
                    }
                }
              });
        });
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