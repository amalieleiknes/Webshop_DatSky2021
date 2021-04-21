$(function(){

    let user = getUser();
    let userLoggedIn = document.getElementById("userLoggedIn");


    // if logged in
    if(user.userID === null || user.userID === ''){


    }
    userLoggedIn.innerHTML = user.username;


        let logOut = {
            text: "Sign out",
            getHTML: function (){
                return this.text;
            }
        }

        let logIn = {
            text: "Sign in",
            getHTML: function (){
                return this.text;
            }
        }


});