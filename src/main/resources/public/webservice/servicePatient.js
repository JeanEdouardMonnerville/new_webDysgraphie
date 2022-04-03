function postPatient(dataJson) {
    return $.ajax({
        url: url + 'patient' ,
        data : JSON.stringify(dataJson),
        headers : { 'token':localStorage.getItem('token')},
        contentType: 'application/json',
        method: 'POST'
    })
}