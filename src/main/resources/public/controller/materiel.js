function ajoutMateriel(data){
    $.ajax({
        url: url + 'materiel' ,
        data : JSON.stringify({'token':localStorage.getItem('token'), materiel : {materielType:data}}),
        method: 'POST',
        contentType: 'application/json',
    })
        .done((data)=> {
            console.log("done ajoutMateriel:",data)
            $( "#container" ).load( "page/autorisation.html" );

        })
        .fail((data) => {
            console.log("fail ajoutMateriel:",data)
        })
}

function materielchoice(choix){
     ajoutMateriel(choix);
}
