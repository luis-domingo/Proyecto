
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
            url: "http://147.83.7.205:8080/dsaApp/usuarios/newuser",
            contentType: 'application/json',
            success: function (us) {
                console.log(us.nombre);
                console.log(us.password);
                alert("Bienvenido, tambien la gano en 2074");
                window.location.replace("index.html");
                console.log("Bienvenido");

            },

            /*error: function(error){
                alert("Error");
            }*/
            })


            })

    });

