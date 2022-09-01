$("#btnLogOut").on("click", function(){
    localStorage.removeItem('usuario_data');
    window.location.href = "index.html";
});