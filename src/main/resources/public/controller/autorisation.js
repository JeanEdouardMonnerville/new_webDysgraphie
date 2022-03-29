function ajoutAutorisation(dataJson){
    $.ajax({
        url: url + 'autorisation' ,
        data : {'autorisation' : true},
        headers : { 'token':localStorage.getItem('token')},
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
