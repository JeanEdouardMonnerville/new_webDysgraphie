function ajoutMateriel(dataJson){
    $.ajax({
        url: url + 'materiel' ,
        data : dataJson,
        method: 'POST'
    })
        .done((data)=> {})
        .fail((data) => {})
}

function materielchoice(choix){
    // ajoutMateriel({});
    $( "#container" ).load( "page/autorisation.html" );
}
