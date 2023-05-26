// Call the dataTables jQuery plugin
$(document).ready(function() {
  //por hacer
});

//se le pone el async a la funcion para indicarle que tiene un await
async function registrarUsuario(){
    let datos = {};
    datos.nombre = document.getElementById('txtNombre').value;
    datos.apellido = document.getElementById('txtApellido').value;
    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;
    let repetirPassword = document.getElementById('txtRepetirPassword').value;

   if(repetirPassword != datos.password) {
        alert('contraseñas diferentes')
        return;
    }
    //para hacer un llamado al servidor tengo que usar la funcion fetch
      const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });
      //justamente el await espera el resultado del llamado del fetch
     alert("La cuenta fue creada con exito!");
       window.location.href = 'login.html'

}
