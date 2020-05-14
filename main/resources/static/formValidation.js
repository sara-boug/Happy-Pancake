var psw1= document.getElementById("psw1"); 
var psw2 = document.getElementById("psw2");
var pwdWarning = document.getElementById("pwdWarning"); 
var emailWarning = document.getElementById("emailWarning"); 
var phoneNumWarning = document.getElementById("phoneNumWarning"); 
var btn = document.getElementById("button");
var email = document.getElementById("email"); 
var phoneNumber = document.getElementById("phoneNumber");

handlePasswordMatching(psw1,psw2); 
handlePasswordMatching(psw2, psw1);
validateEmail(email);
validatephoneNumber(phoneNumber);

function validatephoneNumber(phoneNumber) { 
    var regExp=/(06|07|05){1}\d{8}$/; // wich matches the standard phone numbers in algeria without special consideration to fix ones
    phoneNumber.addEventListener("input", function(){ 
   if(/(06|07|05){1}\d{8}$/.test( phoneNumber.value.toString().trim()  )){ 
          handleStyle(phoneNumWarning, btn, "green","", false);     
       } else { 
          handleStyle(phoneNumWarning , btn, "red","insert a valid phone number please", true); 
       
        }
   })

}

function validateEmail(email){  // using a regular exp to validate the  email        
  email.addEventListener("input", function(){ 
     if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/ .test(email.value.trim( ))) { 
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