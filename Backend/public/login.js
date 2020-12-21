
$(document).ready(function(){

   $('#loginbutton').click(function(){
   let usr=document.getElementById("user1").value;
    $.ajax({
       type: 'POST',
       data: JSON.stringify({
         nombre: $("#user1").val(),
         password: $("#pass1").val()
       }),
       dataType: 'json',
       url: "http://147.83.7.205:8080/dsaApp/usuarios/login",
       contentType: 'application/json',
       success: function(us) {
            console.log(us.nombre);
            console.log(us.password);
            alert("Bienvenido de nuevo");
            localStorage.SetItem('User-name', usr);
            console.log("Bienvenido");
            window.location.replace("profile.html");

       },
       error: function(error){
            alert("Error al iniciar sesion");
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