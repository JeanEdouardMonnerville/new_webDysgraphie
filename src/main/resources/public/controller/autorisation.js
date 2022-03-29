function ajoutAutorisation(dataJson){
    $.ajax({
        url: url + 'autorisation' ,
        data : {'token':localStorage.getItem('token'), 'autorisation' : true},
        method: 'POST',
        contentType: 'application/json',

    })
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
