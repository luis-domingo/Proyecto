
$(document).ready(function(){
    let spn =document.querySelector("#Username");
    let var1 =localStorage.getItem('User-name');
    spn.innerHTML=var1;
   $('#logout').click(function(){
    alert("Bye Bye");
      });


});