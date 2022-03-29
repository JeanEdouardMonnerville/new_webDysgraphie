function resultatVitesseInscription(){
    $.ajax({
        url: url + 'resultat/vitesse/inscription' ,
        method: 'GET',
        headers : { 'token':localStorage.getItem('token')},
        contentType:'application/json'
    })
        .done((data)=> {
            console.log("done resultatVitesseInscription:",data);
            lineChart(data,'Vitesse inscription','myChartVitesseI')
        })
        .fail((data) => {
            console.log('erreur resultatVitesseInscription: ' ,data);
        })
}

function resultatVitesse(){
    $.ajax({
        url: url + 'resultat/vitesse' ,
        method: 'GET',
        headers : { 'token':localStorage.getItem('token')},
        contentType:'application/json'
    })
        .done((data)=> {
            console.log("done resultatVitesse:",data);
            lineChart(data,'Vitesse','myChartVitesse')

        })
        .fail((data) => {
            console.log('erreur resultatVitesse: ' ,data);
        })

}
function resultatVitesseComparer(){
    $.ajax({
        url: url + 'resultat/comparer/vitesse' ,
        method: 'GET',
        headers : { 'token':localStorage.getItem('token')},
        contentType:'application/json'
    })
        .done((data)=> {
            console.log("done resultatVitesseComparer:",data);
            // lineChart(data,'Vitesse comparée')
        })
        .fail((data) => {
            console.log('erreur resultatVitesseComparer: ' ,data);
        })
}
function resultatAccelerationComparer(){
    $.ajax({
        url: url + 'resultat/comparer/acceleration' ,
        method: 'GET',
        headers : { 'token':localStorage.getItem('token')},
        contentType:'application/json'
    })
        .done((data)=> {
            console.log("done resultatAccelerationComparer:",data);
            // lineChart(data,'acceleration comparée')
        })
        .fail((data) => {
            console.log('erreur resultatAccelerationComparer: ' ,data);
        })
}
function resultatAcceleration(){
    $.ajax({
        url: url + 'resultat/acceleration' ,
        method: 'GET',
        headers : { 'token':localStorage.getItem('token')},
        contentType:'application/json'
    })
        .done((data)=> {
            console.log("done resultatAcceleration:",data);
            lineChart(data,'acceleration','myChartAcceleration')
        })
        .fail((data) => {
            console.log('erreur resultatAcceleration: ' ,data);
        })
}
function resultatJerk(){
    $.ajax({
        url: url + 'resultat/jerk' ,
        method: 'GET',
        headers : { 'token':localStorage.getItem('token')},
        contentType:'application/json'
    })
        .done((data)=> {
            console.log("done resultatJerk:",data);
            lineChart(data,'jerk','myChartJerk')
        })
        .fail((data) => {
            console.log('erreur resultatJerk: ' ,data);
        })
}
function resultatJerkComparer(){
    $.ajax({
        url: url + 'resultat/comparer/jerk' ,
        method: 'GET',
        headers : { 'token':localStorage.getItem('token')},
        contentType:'application/json'
    })
        .done((data)=> {
            console.log("done resultatJerkComparer:",data);
            // lineChart(data,'jerk comparé')
        })
        .fail((data) => {
            console.log('erreur resultatJerkComparer: ' ,data);
        })
}
function resultatTechnique(){
    $.get(url + 'resultat/technique'
    ).done((dataJson)=>{});
}
function resultatDownload(){
    $.ajax({
        url: url + 'resultat/tdownload' ,
        method: 'GET',
        headers : { 'token':localStorage.getItem('token')},
        contentType:'application/json'
    })
        .done((data)=> {
            console.log("done resultatDownload:",data);
        })
        .fail((data) => {
            console.log('erreur resultatDownload: ' ,data);
        })
}