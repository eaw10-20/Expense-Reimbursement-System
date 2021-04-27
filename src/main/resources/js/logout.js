window.onload = function () {
    //add event listener
    let buttonz = document.getElementById('logout-btn');

    buttonz.addEventListener('click', logoutRequest)

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