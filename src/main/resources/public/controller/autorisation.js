function ajoutAutorisation(dataJson){
    $.ajax({
        url: url + 'autorisation' ,
        data : dataJson,
        method: 'POST'
    })
        .done((data)=> {})
        .fail((data) => {})
}

function autorisation(){
    // ajoutAnalyse({});
    $( "#container" ).load( "page/infoPatient.html" );
}
