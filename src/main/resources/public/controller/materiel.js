function ajoutMateriel(data){
    console.log(JSON.stringify({'token':localStorage.getItem('token'), materiel : {materielType:data}}));
    $.ajax({
        url: url + 'materiel' ,
        data : JSON.stringify({ materiel : {materielType:data}}),
        headers : { 'token':localStorage.getItem('token')},
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
