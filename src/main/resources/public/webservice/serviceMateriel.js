function postMateriel(data){
    return $.ajax({
        url: url + 'materiel' ,
        data : JSON.stringify({materielType:data}),
        headers : { 'token':localStorage.getItem('token')},
        method: 'POST',
        contentType: 'application/json',
    })
}
