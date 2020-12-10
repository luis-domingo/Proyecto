
$(document).ready(function(){

    $('#regbutton').click(function(){
        console.log(document.getElementById("user2").value);
        $.ajax({
            type: "POST",
            data: JSON.stringify({
                nombre: document.getElementById("user2").value,
                password: document.getElementById("pass2").value,
          }),
            dataType: 'json',
            url: "http://localhost:8080/dsaApp/usuarios/newuser",
            contentType: 'application/json',
            success: function (us) {
                console.log(us.nombre);
                console.log(us.password);
                alert("Bienvenido, tambien la gano en 2074");
                console.log("Bienvenido");
            },

            /*error: function(error){
                alert("Error");
            }*/
            })


            })

    });

