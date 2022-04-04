function resultatVitesseInscription(){
    getresultatVitesseInscription()
        .done((data)=> {
            console.log("done resultatVitesseInscription:",data);
            let colors = [];
            let val = data.liste_x[0].toFixed(2);
            for (let i = 0; i<data.liste_Gauss_x.length; i++){
                if (data.liste_Gauss_x[i]==val){
                    colors.push('rgb(255, 0, 0)')
                } else colors.push('rgb(75, 192, 192)')
            }
            lineChart(data.liste_Gauss_x,data.liste_Gauss_y,colors,'Vitesse inscription','myChartVitesseI')
        })
        .fail((data) => {
            console.log('erreur resultatVitesseInscription: ' ,data);
        })
}

function resultatVitesse(){
    getresultatVitesse()
        .done((data)=> {
            console.log("done resultatVitesse:",data);
            lineChart(data.liste_x,data.liste_y,[],'Vitesse','myChartVitesse')

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
            lineChart(data.liste_x,data.liste_y,[],'acceleration','myChartAcceleration')
        })
        .fail((data) => {
            console.log('erreur resultatAcceleration: ' ,data);
        })
}
function resultatJerk(){
    getresultatJerk()
        .done((data)=> {
            console.log("done resultatJerk:",data);
            lineChart(data.liste_x,data.liste_y,[],'jerk','myChartJerk')
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
