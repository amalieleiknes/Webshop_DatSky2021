/*https://www.w3schools.com/js/js_cookies.asp - A function to set a Cookie*/
function setCookie(name, value, validDays){
    let date = new Date();
    if(validDays === 0){
        date.setTime(1); //Date is now Thu Jan 01 1970 01:00:00
    }
    else{
        date.setTime(date.getTime() + (validDays*24*60*60*1000));
    }
    let expires = "Expires= " + date.toUTCString();
    document.cookie = name + "= " + value + "; " + expires + ";path=/";
}
/*https://www.w3schools.com/js/js_cookies.asp - A function to get a Cookie*/
function getCookie(cookieName){
    let name = cookieName + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let splitArray = decodedCookie.split(';');
    for(let counter = 0; counter < splitArray.length; counter++){
        let cookie = splitArray[counter];
        while(cookie.charAt(0) === ' '){
            cookie = cookie.substring(1);
        }
        if(cookie.indexOf(name) === 0){
            return cookie.substring(name.length, cookie.length);
        }
    }
    return "";
}


function getUser(){
    let userID = getCookie("userID");
    let username = getCookie("username");
    return {
        userID: userID,
        username: username
    };
}
