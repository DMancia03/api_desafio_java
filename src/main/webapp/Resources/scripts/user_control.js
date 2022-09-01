document.getElementById("btn_passch").addEventListener("click", (e)=>{
	Swal.fire({
		title: 'Cambio de contraseña', 
		icon: 'info',
		input: 'password',
		inputLabel: 'Ingrese su contraseña',
		showDenyButton: true,
		denyButtonText: 'Cancelar'
	}).then((result)=>{
		if(result.isConfirmed){
			$.post('http://localhost:8080/LaCuponera/usuario', {
				'op': 'passVer', 
				'pass': document.getElementById("swal2-input").value,
				'codigo': document.getElementById("userID").value
			}, function(result){
				if(result == "pass correcta"){
					Swal.fire({
						icon: 'question', 
						title: 'Cambiar contraseña', 
						html: '<input class="swal2-input" type="password" id="newpass" placeholder="Nueva contraseña"/><br>'+
							'<input placeholder="Confirmar contraseña" class="swal2-input" type="password" id="passconfirm">',
						showDenyButton: true,
						denyButtonText: 'Cancelar', 
						confirmButtonText: 'Cambiar contraseña', 
						
					}).then((result)=>{
						if(result.isConfirmed){
							console.log($('#newpass').val())
							if($("#newpass").val() == $("#passconfirm").val()){
								$.post('http://localhost:8080/LaCuponera/usuario', {
									'op': 'passCH', 
									'codigo': $("#userID").val(), 
									'pass': $('#newpass').val()
								}, function(result){
									if(result == "actualizado"){
										Swal.fire({
											title: '¡Exito!',
											text:'Se ha actualizado la contraseña de manera correcta', 
											icon: 'success'
										})
									}else{
										Swal.fire({
											title: '¡Error!',
											text:'No se ha podido actualizar la contraseña, vuelve a intentarlo más tarde', 
											icon: 'error'
										})	
									}
								})
							}else{
								Swal.fire({
									title: '¡Error!',
									text:'Las contraseñas no coinciden, vuelve a intentarlo más tarde', 
									icon: 'error'
								})	
							}
						}
					})
				}else{
					Swal.fire({
						icon: 'error',
						text: 'Contraseña icorrecta, vuelve a intentarlo de nuevo', 
						title: '¡Error!'
					})
				}
			})
		}
	}); 
})