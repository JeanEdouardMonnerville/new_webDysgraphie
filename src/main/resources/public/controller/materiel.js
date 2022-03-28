function ajoutMateriel(data){
    $.ajax({
        url: url + 'materiel' ,
        data : {'token':localStorage.getItem('token'), 'materielType':data},
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

function materielchoice(choix){
     ajoutMateriel(choix);
    $( "#container" ).load( "page/autorisation.html" );
}
