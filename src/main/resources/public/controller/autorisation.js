function ajoutAutorisation(dataJson){
    $.ajax({
        url: url + 'autorisation' ,
        data : {'token':localStorage.getItem('token'), 'autorisation' : true},
        method: 'POST',
        contentType: 'application/json',

    })
        .done((data)=> {
            console.log(data)
        })
        .fail((data) => {
            console.log(data)
        })
}

function autorisation(){
    ajoutAutorisation();
    $( "#container" ).load( "page/infoPatient.html" );
}
