function postResult() {
    return $.ajax({
        url: 'ecriture',
        method:'post',
        headers : { 'token':localStorage.getItem('token')},
        data: JSON.stringify({
            "liste_point":liste_point
        }),
        contentType:'application/json'
    })
}

function getresultatVitesseInscription(){
    return $.ajax({
        url: url + 'resultat/vitesse/inscription' ,
        method: 'GET',
        headers : { 'token':localStorage.getItem('token')},
        contentType:'application/json'
    })
}

function getresultatVitesse(){
    return $.ajax({
        url: url + 'resultat/vitesse' ,
        method: 'GET',
        headers : { 'token':localStorage.getItem('token')},
        contentType:'application/json'
    })
}
function getresultatVitesseComparer(){
    return $.ajax({
        url: url + 'resultat/comparer/vitesse' ,
        method: 'GET',
        headers : { 'token':localStorage.getItem('token')},
        contentType:'application/json'
    })
}
function getresultatAccelerationComparer(){
    return $.ajax({
        url: url + 'resultat/comparer/acceleration' ,
        method: 'GET',
        headers : { 'token':localStorage.getItem('token')},
        contentType:'application/json'
    })
}
function getresultatAcceleration(){
    return $.ajax({
        url: url + 'resultat/acceleration' ,
        method: 'GET',
        headers : { 'token':localStorage.getItem('token')},
        contentType:'application/json'
    })
}
function getresultatJerk(){
    return $.ajax({
        url: url + 'resultat/jerk' ,
        method: 'GET',
        headers : { 'token':localStorage.getItem('token')},
        contentType:'application/json'
    })
}
function getresultatJerkComparer(){
    return $.ajax({
        url: url + 'resultat/comparer/jerk' ,
        method: 'GET',
        headers : { 'token':localStorage.getItem('token')},
        contentType:'application/json'
    })
}
function getresultatTechnique(){
    return $.get(url + 'resultat/technique'
    );
}
function getresultatDownload(){
    return $.ajax({
        url: url + 'resultat/tdownload' ,
        method: 'GET',
        headers : { 'token':localStorage.getItem('token')},
        contentType:'application/json'
    })
}