// ======================================
// Validation File
// validation.js
// ======================================

function showError(message){

    document.getElementById("errorMessage").innerText = message;

}

function clearError(){

    document.getElementById("errorMessage").innerText = "";

}

function validateInputs(currentOperation){

    clearError();

    const value1 = document.getElementById("value1").value.trim();
    const value2 = document.getElementById("value2").value.trim();

    const unit1 = document.getElementById("unit1").value;
    const unit2 = document.getElementById("unit2").value;
    const targetUnit = document.getElementById("targetUnit").value;

    // -----------------------------
    // Value 1
    // -----------------------------

    if(value1 === ""){
    document.getElementById("result").innerHTML = "Waiting for valid input...";

        showError("Please enter first value.");

        return false;
    }

    if(Number(value1) <= 0){

        showError("First value must be greater than zero.");

        return false;
    }

    // -----------------------------
    // Operations needing second value
    // -----------------------------

    if(currentOperation !== "CONVERT"){

        if(value2 === ""){
document.getElementById("result").innerHTML = "Waiting for valid input...";
            showError("Please enter second value.");

            return false;
        }

        if(Number(value2) <= 0){

            showError("Second value must be greater than zero.");

            return false;
        }

    }

    // -----------------------------
    // Units
    // -----------------------------

    if(!unit1){

        showError("Please select first unit.");

        return false;
    }

    if(currentOperation !== "CONVERT"){

        if(!unit2){

            showError("Please select second unit.");

            return false;
        }

    }

    // -----------------------------
    // Target Unit
    // -----------------------------

    if(

        currentOperation === "CONVERT" ||

        currentOperation === "ADD" ||

        currentOperation === "SUBTRACT"

    ){

        if(!targetUnit){

            showError("Please select target unit.");

            return false;
        }

    }

    clearError();

    return true;

}