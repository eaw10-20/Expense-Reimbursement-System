let requestNum = 0;

window.onload = function (){

    //add event listener
    let buttonz = document.getElementById('logout-btn');

    buttonz.addEventListener('click', logoutRequest)


    getReimbReq();

    //add event listeners
    let btnpend = document.getElementById('pending-btn');
    let btnappr = document.getElementById('approve-btn');
    let btndeny = document.getElementById('denied-btn');

    btnpend.addEventListener('click', filterPend);
    btnappr.addEventListener('click', filterAppr);
    btndeny.addEventListener('click', filterDeny);
}

 async function getReimbReq(){
    const ret = await fetch("http://localhost:11235/financemanager");

    let tableInfo = await ret.json();

   console.log("Got json");

   createReimbTable(tableInfo);
} 

//DOM manipulation to create table
function createReimbTable(tableJSON){
    console.log("creating table")
    
    for( var request of tableJSON){
        requestNum++;
        addReimbReq(request);
    }

    //add button here
    tableButton();
}

//DOM manipulation to add a new row to the table
function addReimbReq(request){
    //create table row
    let newRow = document.createElement("tr");
    newRow.setAttribute("id", "row"+request.id);

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
    newEntry2.setAttribute("id", "status"+request.id);        //reference id "status"+userid to change request
    newEntry2.setAttribute("class", "request-status");
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

    //requester
    let newEntry5 = document.createElement("td");
    let tdText5 = document.createTextNode(request.author);
    newEntry5.appendChild(tdText5);
    newRow.appendChild(newEntry5);

    //date submitted
    let newEntry8 = document.createElement("td");
    let tdText8 = document.createTextNode(request.timeSubmitted);
    newEntry8.appendChild(tdText8);
    newRow.appendChild(newEntry8);

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


    //check box (Should be last)
    if(request.status == "Pending"){
        let newEntry9 = document.createElement("td");
        let requestChoice = document.createElement("input");
        requestChoice.setAttribute("type", "checkbox");
        requestChoice.setAttribute("class", "form-check-input");
        requestChoice.setAttribute("id", "checkbox"+requestNum);
        requestChoice.setAttribute("name", request.id); //key is name, value is value = on if check (I think undefined if unchecked)
        newEntry9.appendChild(requestChoice);
        newRow.appendChild(newEntry9);
    }
    else{
        let newEntry9 = document.createElement("td");
        newRow.appendChild(newEntry9);
    }

    document.getElementById("reimb-requests").appendChild(newRow);
}

function tableButton(){
    //create table row
    let newRow = document.createElement("tr");
    let i = 1;
    for(let i = 1; i <= 7; i++){
        newRow.appendChild(document.createElement("td"));
    }
    //approve or deny toggle
    let toggleCell = document.createElement("td");
    toggleCell.appendChild(createApproveDeny());
    newRow.appendChild(toggleCell);
    

    //submit button
    let finalCell = document.createElement("td");
    let submitButton = document.createElement("input");
    submitButton.setAttribute("type", "submit");
    submitButton.setAttribute("value", "Submit");
    //submitButton.setAttribute("class", "btn btn-primary");
    submitButton.addEventListener('click', approveDeny)
    newRow.appendChild(submitButton);

    document.getElementById("table-foot").appendChild(newRow);
}

function createApproveDeny(){
    let approveDeny = document.createElement("select");
    approveDeny.setAttribute("name", "approveDeny");
    approveDeny.setAttribute("id", "approveDeny");

    let selectOne = document.createElement("option");
    selectOne.setAttribute("value", 1);             
    let selText = document.createTextNode("Select an option");
    selectOne.appendChild(selText);

    let approve = document.createElement("option");
    approve.setAttribute("value", 2);               //2 currently stores approve. Could be referenced for scalabliity, but not worth now
    let aprvText = document.createTextNode("Approve");
    approve.appendChild(aprvText);

    let deny = document.createElement("option");
    deny.setAttribute("value", 3);               //3 currently stores deny.
    let denyText = document.createTextNode("Deny");
    deny.appendChild(denyText);

    approveDeny.appendChild(selectOne);
    approveDeny.appendChild(approve);
    approveDeny.appendChild(deny);

    return approveDeny;
}

async function approveDeny(){
    console.log("In approveDeny function")

    let checkArry = [];
    for(let i=1; i <= requestNum; i++){
        if(document.getElementById("checkbox"+i)){
            if(document.getElementById("checkbox"+i).checked)
                checkArry.push(document.getElementById("checkbox"+i).name);
        }
        
    }

    let link = "http://localhost:11235/financemanager";

    let formData = new FormData();
    formData.append("type", document.getElementById("approveDeny").value);
    formData.append("ids", checkArry);

    let req = await fetch(link, {
        "method": "post",
        "body": formData
    });

    let status = await req.status;
    console.log(status);

    //refreshing makes sense here since the server has to be called either way to get the submission time/date
    if(status == 200) location.reload();
}

//to store filtered elements
let domStorage = {};

//filter pending button functions
function filterPend(){
    let pending = {};
    let docs = document.querySelectorAll("td.request-status");
    for(doc of docs){
        if(doc.innerHTML == "Pending"){
            pending[doc.parentNode.id] = doc.parentNode.innerHTML;
            doc.parentNode.innerHTML = "";
        }
    }
    domStorage["pending"] = pending;

    let button = document.getElementById("pending-btn");
    button.innerHTML = "Show Pending";
    button.setAttribute("class", "btn btn-outline-warning");
    button.removeEventListener('click', filterPend);
    button.addEventListener('click', showPend);
}

function showPend(){
    let pending = domStorage["pending"];
    for(id in pending){
        document.getElementById(id).innerHTML = pending[id];
    }

    let button = document.getElementById("pending-btn");
    button.innerHTML = "Hide Pending";
    button.setAttribute("class", "btn btn-warning");
    button.removeEventListener('click', showPend);
    button.addEventListener('click', filterPend);
}

//filter approved button functions
function filterAppr(){
    let approved = {};
    let docs = document.querySelectorAll("td.request-status");
    for(doc of docs){
        if(doc.innerHTML == "Approved"){
            approved[doc.parentNode.id] = doc.parentNode.innerHTML;
            doc.parentNode.innerHTML = "";
        }
    }
    domStorage["Approved"] = approved;

    let button = document.getElementById("approve-btn");
    button.innerHTML = "Show Approved";
    button.setAttribute("class", "btn btn-outline-success");
    button.removeEventListener('click', filterAppr);
    button.addEventListener('click', showAppr);
}

function showAppr(){
    let approved = domStorage["Approved"];
    for(id in approved){
        document.getElementById(id).innerHTML = approved[id];
    }

    let button = document.getElementById("approve-btn");
    button.innerHTML = "Hide Approved";
    button.setAttribute("class", "btn btn-success");
    button.removeEventListener('click', showAppr);
    button.addEventListener('click', filterAppr);
}

//filter denied button functions
function filterDeny(){
    let denied = {};
    let docs = document.querySelectorAll("td.request-status");
    for(doc of docs){
        if(doc.innerHTML == "Denied"){
            denied[doc.parentNode.id] = doc.parentNode.innerHTML;
            doc.parentNode.innerHTML = "";
        }
    }
    domStorage["denied"] = denied;

    let button = document.getElementById("denied-btn");
    button.innerHTML = "Show Denied";
    button.setAttribute("class", "btn btn-outline-danger");
    button.removeEventListener('click', filterDeny);
    button.addEventListener('click', showDeny);
}

function showDeny(){
    let denied = domStorage["denied"];
    for(id in denied){
        document.getElementById(id).innerHTML = denied[id];
    }

    let button = document.getElementById("denied-btn");
    button.innerHTML = "Hide Denied";
    button.setAttribute("class", "btn btn-danger");
    button.removeEventListener('click', showDeny);
    button.addEventListener('click', filterDeny);
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