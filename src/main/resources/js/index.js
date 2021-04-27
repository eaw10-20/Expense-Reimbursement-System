window.onload = function () {
    //check for login session. 
    //window.location.replace(`http://localhost:11235/login`);

    getLoginStatus();
}

function getLoginStatus(){
    let xhttp = new XMLHttpRequest();
    let usertype;

    xhttp.onreadystatechange = function(){
        if(xhttp.readyState===4 && xhttp.status===200){
            usertype = xhttp.response;
            console.log(usertype)

            if(usertype == 0){
                console.log("Redirecting from inside function")
                window.location = (`http://localhost:11235/login.html`);
            }
            else if(usertype == 1){
                console.log("Usertype = "+usertype)
                window.location = (`http://localhost:11235/employee.html`);
            }
            else if(usertype == 2){
                console.log("Usertype = "+usertype)
                window.location = (`http://localhost:11235/financeManager.html`);
            }
            else{
                console.log("unrecognised usertype")
            }
        }
    }

    xhttp.open("POST", `http://localhost:11235/login`);

    xhttp.send();
}

/* async function gLS(){
    const ret = await fetch("http://localhost:11235/login")

    let utype = await ret.text();

    console.log(utype);
    if(utype == 0){
        console.log("Redirecting from inside function")
        window.location = (`http://localhost:11235/login.html`);
    }
    else if(utype == 1){
        console.log("Usertype = "+utype)
        window.location = (`http://localhost:11235/employee.html`);
    }
    else if(utype == 2){
        console.log("Usertype = "+utype)
        window.location = (`http://localhost:11235/financeManager.html`);
    }
    else{
        console.log("unrecognised usertype")
    }
} */