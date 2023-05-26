// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios();
    //este codigo se ejecuta una vez que actualozamos la pagin
    //esta libreria elije la tabla y la muestra como tabla
  $('#usuarios').DataTable();
});

function getHeaders(){
    return {
        'Accept': 'application/json',
         'Content-Type': 'application/json'
         'Authorization': localStorage.token
    }
}

//se le pone el async a la funcion para indicarle que tiene un await
async function cargarUsuarios(){
    //para hacer un llamado al servidor tengo que usar la funcion fetch
        const request = await fetch('api/usuarios', {
          method: 'GET',
          headers: getHeaders()
        });

      //justamente el await espera el resultado del llamado del fetch
      const usuarios = await request.json();
      let listadoHtml = '';
      for (let usuario of usuarios){

       let botonELiminar = '<a href="#" onclick="eliminarUsuario(' + usuario.id + ')" class="btn btn-danger btn-circle">      <i class="fas fa-trash"></i> </a>'
       let usuarioHtml = '<tr> <td>123</td>    <td>'+usuario.nombre+' '+usuario.apellido+'</td>   <td>'+usuario.email+'</td><td>'+usuario.telefono+'</td> <td>'+botonELiminar+'</td>  </tr>'
       listadoHtml += usuarioHtml;
      }

      document.querySelector('#usuarios tbody').outerHTML = listadoHtml;
}

async function eliminarUsuario(id){
    //para hacer un llamado al servidor tengo que usar la funcion fetch
        alert(id);
      const request = await fetch('api/usuarios/' +id, {
        method: 'DELETE',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
          'Authorization': localStorage.token
        }
      });
      location.reload()
}