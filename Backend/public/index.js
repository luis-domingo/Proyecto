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
    },*/
  });


  $("#registerbutton").click(function(){
    window.location.replace("singup.html")
  })


});