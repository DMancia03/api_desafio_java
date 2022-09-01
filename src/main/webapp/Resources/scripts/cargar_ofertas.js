//opciones para cargar ofertas 
//1-leer el local Storage 
//2-hacer funciones para actualizar carrito
//3-compras de carrito




$(document).ready(function(){
	let cartDiv = document.getElementById("cart-div")
	let btnBuy = $("#buy"); 
	let divOfertasN = document.getElementById("cantidad_total");  
	let ofertasSelect = JSON.parse(localStorage.getItem("carritoCupones")); 
	let sizeOfertas =sizeOfObj(ofertasSelect); 
	//console.log(divOfertasN)
	divOfertasN.innerText = sizeOfertas
	document.getElementById("of-c").innerText = sizeOfertas; 
	if(sizeOfertas<=0){
		cartDiv.innerHTML = "<h1>No se han encontrado Ofertas, añade más ofertas para pode continuar</h1>"
		btnBuy.disabled = true;
		
	}else{
		
		//lectura de todas las cosas
		/*for(let key in ofertasSelect ){
			$.post("http://localhost:8080/LaCuponera/ofertas", {
				"op": "obtenerCarro", 
				"codigo": ofertasSelect[key]
			}, function(res){
				cartDiv.innerHTML += res; 
			})
		}*/
		
	}
	llenarcammpos();

	totalOriginal();

})

document.addEventListener("click", (e)=>{
	let target = e.target;
	if(target.tagName == "IMG"){
		let idTag = target.id.split("_"); 
		let idName = "cantidad_"+idTag[1]; 
		let cantidad = document.getElementById(idName).value; 
		cantidad = Number.parseInt(cantidad)

		if(Number.isInteger(cantidad)){
			if(target.id.includes("minus")){
				cantidad--; 
				document.getElementById(idName).value = cantidad

				if(cantidad == 0){
					//eliminar
					eliminarOferta(idTag[1])
					llenarcammpos();
					irCarrito()
				}
			}else if(target.id.includes("plus")){
				cantidad++; 
				document.getElementById(idName).value = cantidad
			}
			let precio = $("#precio_"+idTag[1]).val()
			
			let total = cantidad * Number.parseFloat(precio); 
			total = parseFloat(total); 
				$("#total_"+idTag[1]).val(total)
				document.getElementById("totalSpan_"+idTag[1]).innerText = "$" +  total
				totalOriginal();

		}
		
	}
	if(target.tagName == "A"){
		let idTag = target.id.split("_"); 
		if(idTag.id.includes("eliminar")){
			eliminarOferta(idTag[1])
			llenarcammpos();
			irCarrito()
		}
	}
})





//hacer funcion de contar todas los totales 

function totalOriginal (){
	let inputs = document.getElementsByClassName("totales")
	let total = 0; 
	Array.from(inputs).forEach((input)=>{
		total+= Number.parseFloat(input.value)
	})
	document.getElementById("stotal").innerText = "$"+total; 
	document.getElementById("stotal-2").innerText = "$"+total; 

}



function eliminarOferta(codigo){
	let ofertasSelect = JSON.parse(localStorage.getItem("carritoCupones")); 

	for(const key in ofertasSelect ){
		if(ofertasSelect[key] == codigo){
			delete ofertasSelect[key]
		}
	}
	
	localStorage.setItem("carritoCupones", JSON.stringify(ofertasSelect))
}





function sizeOfObj (obj) { 
	  let c=0; 
	  for(let key in obj){
		  c++; 
		  
	  }
	  return c; 
	}








function ofertaMolde (nombre, codigo, empresaN, precio ){
	return ` <div class="flex items-center hover:bg-gray-100 -mx-8 px-6 py-5">
          <div class="flex w-2/5"> <!-- product -->
            <div class="w-20">
              <img class="h-24" src="https://static.vecteezy.com/system/resources/previews/002/620/855/non_2x/big-sale-offer-discount-coupon-market-over-white-background-free-vector.jpg" alt="oferta">
            </div>
            <div class="flex flex-col justify-between ml-4 flex-grow">
              <span class="font-bold text-sm">${nombre}</span>
              <span class="text-red-500 text-xs">${empresaN}</span>
              <a href="#" class="font-semibold hover:text-red-500 text-gray-500 text-xs">Eliminar</a>
            </div>
          </div>
          <div class="flex justify-center w-1/5">
            <svg class="fill-current text-gray-600 w-3" viewBox="0 0 448 512"><path d="M416 208H32c-17.67 0-32 14.33-32 32v32c0 17.67 14.33 32 32 32h384c17.67 0 32-14.33 32-32v-32c0-17.67-14.33-32-32-32z"/>
            </svg>
			<input type='hidden' id='id_${codigo}' value='${codigo}'/>
			<input type='hidden'  id='precio_${codigo}' value'${precio}'/> 
            <input class="mx-2 border text-center w-8" type="text" value="1">

            <svg class="fill-current text-gray-600 w-3" viewBox="0 0 448 512">
              <path d="M416 208H272V64c0-17.67-14.33-32-32-32h-32c-17.67 0-32 14.33-32 32v144H32c-17.67 0-32 14.33-32 32v32c0 17.67 14.33 32 32 32h144v144c0 17.67 14.33 32 32 32h32c17.67 0 32-14.33 32-32V304h144c17.67 0 32-14.33 32-32v-32c0-17.67-14.33-32-32-32z"/>
            </svg>
          </div>
          <span class="text-center w-1/5 font-semibold text-sm">$ ${precio}</span>
          <span class="text-center w-1/5 font-semibold text-sm">$ ${precio}</span>
        </div>`; 
}