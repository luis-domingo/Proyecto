
$(document).ready(function(){

   $('#loginbutton').click(function(){
    $.ajax({
       type: 'POST',
       data: JSON.stringify({
         nombre: $("#user1").val(),
         password: $("#pass1").val()
       }),
       dataType: 'json',
       url: "http://localhost:8080/dsaApp/usuarios/login",
       contentType: 'application/json',
       success: function(us) {
            console.log(us.nombre);
            console.log(us.password);
            alert("Bienvenido, el Betis ganó la Champions por el 2056");
            console.log("Bienvenido");

       },
       error: function(error){
            alert("Error al iniciar sesion, en el 2035 se intentó...");
            console.log(error);
       }
         })
      });
});




/*
$(document).ready(function(){
  $("#loginbutton").click(function(){

    /*console.log(JSON.stringify({name: $("#user").val(), password: $("#pass").val()}));

    $.ajax(
    {
        type: 'POST',
        url: "http://localhost:8080/dsaApp/NewUser",
        datatype: 'json',
        success: function(data){
                console.log(data.name);
                console.log(data.password);
                alert("Bienvenido "+data.name);
        }
        error: function(){
            alert("Error");
        }
    },
  });


  $("#registerbutton").click(function(){
    window.location.replace("singup.html")
  })


});
*/