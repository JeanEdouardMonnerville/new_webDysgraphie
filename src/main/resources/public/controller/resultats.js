function resultatVitesseInscription(){
    getresultatVitesseInscription()
        .done((data)=> {
            console.log("done resultatVitesseInscription:",data);
            lineChart(data,'Vitesse inscription','myChartVitesseI')
        })
        .fail((data) => {
            console.log('erreur resultatVitesseInscription: ' ,data);
        })
}

function resultatVitesse(){
    getresultatVitesse()
        .done((data)=> {
            console.log("done resultatVitesse:",data);
            lineChart(data,'Vitesse','myChartVitesse')

        })
        .fail((data) => {
            console.log('erreur resultatVitesse: ' ,data);
        })

}
function resultatVitesseComparer(){
    getresultatVitesseComparer()
        .done((data)=> {
            console.log("done resultatVitesseComparer:",data);
            // lineChart(data,'Vitesse comparée')
        })
        .fail((data) => {
            console.log('erreur resultatVitesseComparer: ' ,data);
        })
}
function resultatAccelerationComparer(){
    getresultatAccelerationComparer()
        .done((data)=> {
            console.log("done resultatAccelerationComparer:",data);
            // lineChart(data,'acceleration comparée')
        })
        .fail((data) => {
            console.log('erreur resultatAccelerationComparer: ' ,data);
        })
}
function resultatAcceleration(){
    getresultatAcceleration()
        .done((data)=> {
            console.log("done resultatAcceleration:",data);
            lineChart(data,'acceleration','myChartAcceleration')
        })
        .fail((data) => {
            console.log('erreur resultatAcceleration: ' ,data);
        })
}
function resultatJerk(){
    getresultatJerk()
        .done((data)=> {
            console.log("done resultatJerk:",data);
            lineChart(data,'jerk','myChartJerk')
        })
        .fail((data) => {
            console.log('erreur resultatJerk: ' ,data);
        })
}
function resultatJerkComparer(){
    getresultatJerkComparer()
        .done((data)=> {
            console.log("done resultatJerkComparer:",data);
            // lineChart(data,'jerk comparé')
        })
        .fail((data) => {
            console.log('erreur resultatJerkComparer: ' ,data);
        })
}
function resultatTechnique(){
    getresultatTechnique().done((dataJson)=>{});
}
function resultatDownload(){
    getresultatDownload()
        .done((data)=> {
            console.log("done resultatDownload:",data);
        })
        .fail((data) => {
            console.log('erreur resultatDownload: ' ,data);
        })
}

function getUrlResultat(){
    return url + 'resultat/download/'+localStorage.getItem('token');
}

function putUrlResultat(){
    let downloadExcel = document.getElementById("idDownloadUrl");
    downloadExcel.href=getUrlResultat();
}
