window.onload = function (){

    //add event listener
    let buttonz = document.getElementById('logout-btn');

    buttonz.addEventListener('click', logoutRequest)

    getReimb();


    document.getElementById("new-request").addEventListener('click', submitRequest);
}

//fill table with currently existing reimbursements
function getReimb(){
    //utilizing fetch here

    fetch('http://localhost:11235/employee')
        .then(
            function(empResponse){
                const employeeRequests = empResponse.json();
                return employeeRequests;
            }
        ).then(
            function(secondRes){
                console.log("got promises")
                console.log(secondRes)
                //call function to create table using json
                createTable(secondRes);
            }
        )
        .catch(
            (e) => {console.log("Failed to get reimbursement request JSON")}
        )
}

//
function submitRequest(eve){
    eve.preventDefault();

    let requestJSON = {
        "type": document.getElementById("reimb-type-list").value,
        "amount": document.getElementById("reimbursement-amount").value,
        "description": document.getElementById("reimbursement-description").value
    }

    fetch('http://localhost:11235/employee', {
        'method': "post", 
        'headers': {
            'Content-Type': 'application/json'
        },
        'body': JSON.stringify(requestJSON)
    })
        .then(
            function(reqResponse){
                const convertedRequest = reqResponse.json();
                return convertedRequest;
            }
        ).then(
            function(secondRes){
                console.log("posted new Reimbursement Request")
                console.log(secondRes)
                //call function to create table using json
                addReq(secondRes);
            }
        )
        .catch(
            (e) => {console.log("Failed to get reimbursement request JSON")}
        )
}


//DOM manipulation to create table
function createTable(tableJSON){
    console.log("creating table")
    for( var request of tableJSON){
        addReq(request);
    }
}

//DOM manipulation to add a new row to the table
function addReq(request){
    //create table row
    let newRow = document.createElement("tr");

    console.log("created variables")
    //type
    let newEntry = document.createElement("td");
    let tdText = document.createTextNode(request.type);
    newEntry.appendChild(tdText);
    newRow.appendChild(newEntry);

    console.log("inserted type")
    //status
    let newEntry2 = document.createElement("td");
    let tdText2 = document.createTextNode(request.status);
    newEntry2.appendChild(tdText2);
    newRow.appendChild(newEntry2);

    console.log("inserted status")
    //amount
    let newEntry3 = document.createElement("td");
    let tdText3 = document.createTextNode('$'+request.amount);
    newEntry3.appendChild(tdText3);
    newRow.appendChild(newEntry3);

    //description
    let newEntry4 = document.createElement("td");
    let tdText4 = document.createTextNode(request.description);
    newEntry4.appendChild(tdText4);
    newRow.appendChild(newEntry4);

    //date submitted
    let newEntry5 = document.createElement("td");
    let tdText5 = document.createTextNode(request.timeSubmitted);
    newEntry5.appendChild(tdText5);
    newRow.appendChild(newEntry5);

    //date resolved
    let newEntry6 = document.createElement("td");
    let tdText6 = document.createTextNode(request.timeResolved);
    newEntry6.appendChild(tdText6);
    newRow.appendChild(newEntry6);

    //resolver
    let newEntry7 = document.createElement("td");
    let tdText7 = document.createTextNode(request.resolver);
    newEntry7.appendChild(tdText7);
    newRow.appendChild(newEntry7);


    document.getElementById("reimb").appendChild(newRow);
}

function logoutRequest(){
    console.log('Recieved login request')

    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function(){

        if(xhttp.readyState===4 && xhttp.status===200){
            window.location.href = 'http://localhost:11235/'
        }
    }

    xhttp.open("get", `http://localhost:11235/logout`);


    xhttp.send();
}