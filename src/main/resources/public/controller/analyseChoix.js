let url = 'http://localhost:8081/';
function analysechoice(choix){
    let oldtoken = localStorage.getItem('token')
    if (oldtoken!=''){
        console.log('token old',oldtoken)
        //TODO : Supprimer donnee du token
    }
    ajoutAnalyse({typeAnalyse:choix})
    localStorage.setItem("analyse",choix);

}
function ajoutAnalyse(dataJson){
    console.log('ajoutAnalyse')
    $.ajax({
        url: url + 'analyse' ,
        data : dataJson,
        method: 'POST'
    })
        .done((data)=> {
            console.log(data,data.token)
            localStorage.setItem("token",data.token);
            $( "#container" ).load( "page/materiel.html" );
        })
        .fail((data) => {
            console.log('erreur : ' ,data)
            //TODO : a supprimer
            // $( "#container" ).load( "page/materiel.html" );

        })

}
