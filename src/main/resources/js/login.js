window.onload = function () {
    //add event listener
    let button1 = document.getElementById('login-request');

    button1.addEventListener('click', loginRequest)

    console.log("login page")
}

function loginRequest(eve){
    eve.preventDefault();
    console.log('Recieved login request')

    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function(){

        if(xhttp.readyState===4 && xhttp.status===200){
            window.location.href = 'http://localhost:11235/'
        }
    }

    xhttp.open("POST", `http://localhost:11235/home`);

    xhttp.setRequestHeader('content-type', 'application/json');

    let credentials = {
        "username": document.getElementById('usernameInput').value,
        "password": document.getElementById('passwordInput').value
    }

    console.log(credentials);

    xhttp.send(JSON.stringify(credentials))
}