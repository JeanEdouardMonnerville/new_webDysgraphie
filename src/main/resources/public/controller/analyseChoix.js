let url = 'http://localhost:8081/';

function deleteEnregistrement(){
    $.ajax({
        url : url + 'remove',
        data: {'token':localStorage.getItem('token')},
        method: 'DELETE',
        contentType: 'application/json',
    }).done((data)=>{
        console.log("sucess delete : ",data)
    }).fail((data) =>{
        console.log("fail delete : ",data)
    })
}

function ajoutAnalyse(dataJson){
    console.log(dataJson);
    $.ajax({
        url: url + 'analyse' ,
        data : dataJson,
        method: 'POST',
        contentType:'application/json'
    })
        .done((data)=> {
            console.log("done :");
            console.log(data,data.token);
            localStorage.setItem("token",data.token);
            $( "#container" ).load( "page/materiel.html" );
        })
        .fail((data) => {
            console.log('erreur : ' ,data);
        })
}

function analysechoice(choix){
    let oldtoken = localStorage.getItem('token');
    if ( oldtoken!=null){
        console.log('token old',oldtoken);
        deleteEnregistrement();
    }
    ajoutAnalyse(JSON.stringify({typeAnalyse:choix}));
    localStorage.setItem("analyse",choix);

}
