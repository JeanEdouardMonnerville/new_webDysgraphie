function ajoutMateriel(data){
    console.log(JSON.stringify({'token':localStorage.getItem('token'), materiel : {materielType:data}}));
   postMateriel(data)
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
