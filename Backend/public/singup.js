
$(document).ready(function(){

    $('#regbutton').click(function(){

        $.ajax({
            type: 'POST',
            data: JSON.stringify({
                username2: $("#user2").val(),
                password2: $("#pass2").val(),
          }),
            dataType: 'json',
            url: "http://localhost:8080/dsaApp/usuarios/login/NewUser",
            contentType: 'json',
            success: function (us) {
                console.log(us.username2);
                console.log(us.password2);
                alert("Bienvenido, tambien la gano en 2074");
                console.log("Bienvenido");
            },

            /*error: function(error){
                alert("Error");
            }*/
            }


            })

    });

