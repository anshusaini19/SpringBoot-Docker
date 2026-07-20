// Store JWT after Google Login
const params = new URLSearchParams(window.location.search);
const token = params.get("token");

if (token) {
    localStorage.setItem("token", token);

    // Remove token from URL
    window.history.replaceState({}, document.title, "/index.html");
}
// ==========================================
// Quantity Measurement Frontend
// ==========================================

let selectedMeasurement = "Length";
let currentOperation = "COMPARE";

const API = "http://localhost:8080/api/quantity";

// ==========================================
// Cards
// ==========================================

const cards = document.querySelectorAll(".type-card");

cards.forEach(card => {

    card.addEventListener("click", () => {

        cards.forEach(c => c.classList.remove("active"));

        card.classList.add("active");

        switch(card.id){

            case "lengthCard":
                selectedMeasurement="Length";
                break;

            case "weightCard":
                selectedMeasurement="Weight";
                break;

            case "temperatureCard":
                selectedMeasurement="Temperature";
                break;

            case "volumeCard":
                selectedMeasurement="Volume";
                break;
        }

        loadUnits();

    });

});

// ==========================================
// Load Units
// ==========================================

function loadUnits(){

    let units=[];

    switch(selectedMeasurement){

        case "Length":

            units=[
                "FEET",
                "INCHES",
                "CENTIMETERS",
                "YARDS"
            ];

            break;

        case "Weight":

            units=[
                "GRAM",
                "KILOGRAM",
                "TONNE"
            ];

            break;

        case "Temperature":

            units=[
                "CELSIUS",
                "FAHRENHEIT",
                "KELVIN"
            ];

            break;

        case "Volume":

            units=[
                "LITRE",
                "MILLILITRE",
                "GALLON"
            ];

            break;

    }

    fillDropdown("unit1",units);

    fillDropdown("unit2",units);

    fillDropdown("targetUnit",units);

}

function fillDropdown(id,units){

    const dropdown=document.getElementById(id);

    dropdown.innerHTML="";

    units.forEach(unit=>{

        dropdown.innerHTML+=
        `<option value="${unit}">
            ${unit}
        </option>`;

    });

}

// ==========================================
// Labels
// ==========================================

const label1=document.getElementById("label1");
const label2=document.getElementById("label2");

// ==========================================
// Buttons
// ==========================================

const buttons=document.querySelectorAll(".action-btn");

buttons.forEach(button=>{

    button.addEventListener("click",()=>{

        buttons.forEach(btn=>
            btn.classList.remove("active-action"));

        button.classList.add("active-action");

    });

});

document.getElementById("compareBtn").onclick=()=>{

    currentOperation="COMPARE";

    label1.innerText="FROM";

    label2.innerText="TO";

};

document.getElementById("convertBtn").onclick=()=>{

    currentOperation="CONVERT";

    label1.innerText="VALUE";

    label2.innerText="TARGET UNIT";

};

document.getElementById("addBtn").onclick=()=>{

    currentOperation="ADD";

    label1.innerText="VALUE 1";

    label2.innerText="VALUE 2";

};

document.getElementById("subtractBtn").onclick=()=>{

    currentOperation="SUBTRACT";

    label1.innerText="VALUE 1";

    label2.innerText="VALUE 2";

};

document.getElementById("divideBtn").onclick=()=>{

    currentOperation="DIVIDE";

    label1.innerText="VALUE 1";

    label2.innerText="VALUE 2";

};

// ==========================================
// Execute
// ==========================================

document.getElementById("executeBtn").onclick=()=>{

    if(!validateInputs(currentOperation))
        return;

    switch(currentOperation){

        case "COMPARE":
            compareQuantity();
            break;

        case "CONVERT":
            convertQuantity();
            break;

        case "ADD":
            addQuantity();
            break;

        case "SUBTRACT":
            subtractQuantity();
            break;

        case "DIVIDE":
            divideQuantity();
            break;

    }

};

// ==========================================
// Compare API
// ==========================================

async function compareQuantity(){

    const body={

        value:Number(
            document.getElementById("value1").value),

        unit:
            document.getElementById("unit1").value,

        measurementType:selectedMeasurement

    };

    const secondValue=
        document.getElementById("value2").value;

    const secondUnit=
        document.getElementById("unit2").value;

    const response=await fetch(

        API+"/compare"
        +"?secondValue="+secondValue
        +"&secondUnit="+secondUnit
        +"&secondMeasurementType="+selectedMeasurement,

        {

            method:"POST",

            headers:{

                "Content-Type":"application/json",

                "Authorization":
                "Bearer "+localStorage.getItem("token")

            },

            body:JSON.stringify(body)

        }

    );

    const result=await response.json();

    showResult(result);

}

// ==========================================
// Common Result
// ==========================================

function showResult(result){

    if(result.error){

        document.getElementById("result")
        .innerHTML=result.errorMessage;

        return;

    }

    document.getElementById("result").innerHTML=

        result.resultString ??

        result.resultValue ??

        "Success";

}

loadUnits();
// ==========================================
// Convert API
// ==========================================

async function convertQuantity(){

    const body={

        value:Number(
            document.getElementById("value1").value),

        unit:
            document.getElementById("unit1").value,

        measurementType:selectedMeasurement

    };

    const targetUnit=
        document.getElementById("targetUnit").value;

    const response=await fetch(

        API+"/convert"
        +"?targetUnit="+targetUnit,

        {

            method:"POST",

            headers:{

                "Content-Type":"application/json",

                "Authorization":
                "Bearer "+localStorage.getItem("token")

            },

            body:JSON.stringify(body)

        }

    );

    const result=await response.json();

    showResult(result);

}

// ==========================================
// Add API
// ==========================================

async function addQuantity(){

    const body={

        value:Number(
            document.getElementById("value1").value),

        unit:
            document.getElementById("unit1").value,

        measurementType:selectedMeasurement

    };

    const secondValue=
        document.getElementById("value2").value;

    const secondUnit=
        document.getElementById("unit2").value;

    const targetUnit=
        document.getElementById("targetUnit").value;

    const response=await fetch(

        API+"/add"
        +"?secondValue="+secondValue
        +"&secondUnit="+secondUnit
        +"&secondMeasurementType="+selectedMeasurement
        +"&targetUnit="+targetUnit,

        {

            method:"POST",

            headers:{

                "Content-Type":"application/json",

                "Authorization":
                "Bearer "+localStorage.getItem("token")

            },

            body:JSON.stringify(body)

        }

    );

    const result=await response.json();

    showResult(result);

}

// ==========================================
// Subtract API
// ==========================================

async function subtractQuantity(){

    const body={

        value:Number(
            document.getElementById("value1").value),

        unit:
            document.getElementById("unit1").value,

        measurementType:selectedMeasurement

    };

    const secondValue=
        document.getElementById("value2").value;

    const secondUnit=
        document.getElementById("unit2").value;

    const targetUnit=
        document.getElementById("targetUnit").value;

    const response=await fetch(

        API+"/subtract"
        +"?secondValue="+secondValue
        +"&secondUnit="+secondUnit
        +"&secondMeasurementType="+selectedMeasurement
        +"&targetUnit="+targetUnit,

        {

            method:"POST",

            headers:{

                "Content-Type":"application/json",

                "Authorization":
                "Bearer "+localStorage.getItem("token")

            },

            body:JSON.stringify(body)

        }

    );

    const result=await response.json();

    showResult(result);

}

// ==========================================
// Divide API
// ==========================================

async function divideQuantity(){

    const body={

        value:Number(
            document.getElementById("value1").value),

        unit:
            document.getElementById("unit1").value,

        measurementType:selectedMeasurement

    };

    const secondValue=
        document.getElementById("value2").value;

    const secondUnit=
        document.getElementById("unit2").value;

    const response=await fetch(

        API+"/divide"
        +"?secondValue="+secondValue
        +"&secondUnit="+secondUnit
        +"&secondMeasurementType="+selectedMeasurement,

        {

            method:"POST",

            headers:{

                "Content-Type":"application/json",

                "Authorization":
                "Bearer "+localStorage.getItem("token")

            },

            body:JSON.stringify(body)

        }

    );

    const result=await response.json();

    showResult(result);

}