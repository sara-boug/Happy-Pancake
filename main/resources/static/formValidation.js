var psw1= document.getElementById("psw1"); 
var psw2 = document.getElementById("psw2");
var pwdWarning = document.getElementById("pwdWarning"); 
var emailWarning = document.getElementById("emailWarning"); 
var btn = document.getElementById("button");
var email = document.getElementById("email"); 

handlePasswordMatching(psw1,psw2); 
handlePasswordMatching(psw2, psw1);
validateEmail(email);
 

function validateEmail(email){  // using a regular exp to 
              
  email.addEventListener("input", function(){ 
     if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/ .test(email.value.trim())) { 
        handleStyle(emailWarning , btn, "green","", false);     
    } else { 
       handleStyle(emailWarning , btn, "red","Insert a valid email please", true); 
    
     }

})
}

function handlePasswordMatching(psw1 , psw2) { // compring the input passwords

    psw2.addEventListener("input", function(){ 
     
        if(this.value.trim() != psw1.value.trim()) { 
            handleStyle(pwdWarning , btn, "red","Passwords not matching", true); 
        } else { 
            handleStyle(pwdWarning , btn, "green","Passwords  matching", false);     
         }
     
     }); 
}

function handleStyle( warning, button,  color , message, disabled){  // in order de handle the style of the warnings
    warning.style.color = color; 
    warning.innerHTML=message; 
    button.disabled=disabled; 

}