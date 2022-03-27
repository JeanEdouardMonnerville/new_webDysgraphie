function resultatVitesseInscription(){
    $.get(url + 'resultat/vitesse/inscription'
    ).done((dataJson)=>{});
}
function resultatVitesse(){
    $.get(url + 'resultat/vitesse'
    ).done((dataJson)=>{});
}
function resultatVitesseComparer(){
    $.get(url + 'resultat/comparer/vitesse'
    ).done((dataJson)=>{});
}
function resultatAccelerationComparer(){
    $.get(url + 'resultat/comparer/acceleration'
    ).done((dataJson)=>{});
}
function resultatAcceleration(){
    $.get(url + 'resultat/acceleration'
    ).done((dataJson)=>{});
}
function resultatJerk(){
    $.get(url + 'resultat/jerk'
    ).done((dataJson)=>{});
}
function resultatJerkComparer(){
    $.get(url + 'resultat/comparer/jerk'
    ).done((dataJson)=>{});
}
function resultatTechnique(){
    $.get(url + 'resultat/technique'
    ).done((dataJson)=>{});
}
function resultatDownload(){
    $.get(url + 'resultat/tdownload'
    ).done((dataJson)=>{});
}