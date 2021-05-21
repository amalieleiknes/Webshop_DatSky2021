//Functions for validation of input
function isValidNumber(inputElement, elementName, errorMessageId){
    if(/\S/.test(inputElement.value) && !isNaN(inputElement.value)){
        // string is not empty, is a number and not just whitespace
        $("#"+errorMessageId).fadeOut();
        inputElement.classList.remove("input-alert");
        //valid
        return true;
    }else{
        $("#"+errorMessageId).fadeIn();
        inputElement.classList.add("input-alert");
        $("#"+errorMessageId).html('Field "' + elementName + '" must consist of numbers.');
        return false;
    }
}

function isValid(inputElement,elementName, errorMessageId){
    console.log("InputElement: ", inputElement);
    if(inputElement === null){
        return  false;
    }

    if(inputElement.value.length > 0 && /\S/.test(inputElement.value)){
        $("#"+errorMessageId).fadeOut();
        inputElement.classList.remove("input-alert");
        //valid
        return true;
    }else{
        $("#"+errorMessageId).fadeIn();
        inputElement.classList.add("input-alert");
        $("#"+errorMessageId).html('Field "' + elementName + '" is not filled in.');
        return false;
    }
}