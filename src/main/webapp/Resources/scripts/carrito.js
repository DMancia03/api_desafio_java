
document.getElementById("ofertas").addEventListener("click", (e)=>{
    let tag = e.target; 
    if(tag.nameTag = "A" && tag.id.includes("cart")){
        e.preventDefault(); 
        let idCompra = tag.id.split("_")[1]; 
        let added = false; 
       // let localStorageCart = window.localStorage();  
        if(localStorage.getItem("carritoCupones")===null){
        	let aName = "producto"+idCompra; 
            let jsodCompra = {aName: idCompra}
            localStorage.setItem("carritoCupones", JSON.stringify(jsodCompra))
        }else{
            let c = 1; 
            let jsodCompra = JSON.parse(localStorage.getItem("carritoCupones")); 
            for(const item in jsodCompra){
                if(jsodCompra[item] == idCompra){
                    added = true; 
                }
            }
            if(!added){
                jsodCompra["producto"+idCompra] = idCompra; 
            }
            localStorage.setItem("carritoCupones", JSON.stringify(jsodCompra))


        }
        let jsod = JSON.parse(localStorage.getItem("carritoCupones")); 
        let array = new Array(); 
        for(let key in jsod){
        	array.push(jsod[key])
        }
        console.log(array)
        llenarcammpos();

       // let urlForCart = "http://"+location.hostname+":"+location.port+"/LaCuponera/carrito?ofertas="+array
        if(added){
        
            Swal.fire({
                title: 'Error', 
                text: 'Ya has seleccionado esta oferta anteriormente, para modificarla puede ir a ver tu carrito',
                icon: 'error', 
                showDenyButton: 'true', 
                confirmButtonText: 'Ver carrito',
                denyButtonText: 'Volver a ofertas'
               }).then((result)=>{
            	   if(result.isConfirmed){
            		   //pasar al carrito
            		   
            		 //window.location.href = urlForCart
            		   irCarrito()
            	   }
               })
        }else{
            Swal.fire({
                title: 'Agregado', 
                text: 'Se ha agrego la oferta a tu carrito',
                icon: 'success', 
                showDenyButton: 'true', 
                confirmButtonText: 'Ver carrito',
                denyButtonText: 'Volver a ofertas'
               }).then((result)=>{
            	   if(result.isConfirmed){
            		   //pasar al carrito
            		   //window.location.href = urlForCart
            		   irCarrito()

            	   }
               })
        }
    
    }
})