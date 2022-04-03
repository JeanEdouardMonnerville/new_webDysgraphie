function ajoutAnalyse(dataJson){
    postAnalyse(dataJson)
        .done((data)=> {
            console.log("done :");
            console.log(data,data.token);
            localStorage.setItem("token",data.token);
            $( "#container" ).load( "page/materiel.html" );
        })
        .fail((data) => {
            console.log('erreur : ' ,data);
        })
}

function analysechoice(choix){
    let oldtoken = localStorage.getItem('token');
    if ( oldtoken!=null){
        console.log('token old',oldtoken);
        deleteEnregistrement();
    }
    ajoutAnalyse(JSON.stringify({typeAnalyse:choix}));
    localStorage.setItem("analyse",choix);

}
