function ajoutAutorisation(){
    postAutorisation()
        .done((data)=> {
            console.log("done ajoutAutorisation",data);
            $( "#container" ).load( "page/infoPatient.html" );
        })
        .fail((data) => {
            console.log("fail ajoutAutorisation",data)
        })
}

function autorisation(){
    ajoutAutorisation();
}
