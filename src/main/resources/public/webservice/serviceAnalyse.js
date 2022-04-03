function postAnalyse(dataJson){
    return $.ajax({
        url: url + 'analyse' ,
        data : dataJson,
        method: 'POST',
        contentType:'application/json'
    })
}
function deleteEnregistrement(){
    $.ajax({
        url : url + 'remove',
        headers : { 'token':localStorage.getItem('token')},
        method: 'DELETE',
        contentType: 'application/json',
    }).done((data)=>{
        console.log("sucess delete : ",data)
    }).fail((data) =>{
        console.log("fail delete : ",data)
    })
}