function ajoutPatient(dataJson){
    $.ajax({
        url: url + 'patient' ,
        data : JSON.stringify(dataJson),
        headers : { 'token':localStorage.getItem('token')},
        contentType: 'application/json',
        method: 'POST'
    })
        .done((data)=> {
            console.log("succes ajoutPatient",data);
            if(localStorage.getItem("analyse")==="BHK") {
                $( "#container" ).load( "page/consigneBHK.html" );
            } else $( "#container" ).load( "page/consignePangramme.html" );
        })
        .fail((data) => {
            console.log("fail ajoutPatient",data);

        })
}

function information(){
    let age = $("#addPatientage").val();
    let nom = $("#addPatientLibelle").val();
    let prenom = $("#addPatientLibelle").val();
    let classe = $("#addPatientclasse").val();
    let sexe = $("#addPatientsexe").val();
    let dateExam = $("#addPatientdateExamen").val();
    let anonymat = $("#addPatientAnonymat").val();
    console.log(age,nom,classe,sexe,dateExam,anonymat);

    if (age==''){
        console.log("erreur age");
        return;
    }if (nom==''){
        console.log("erreur nom");
        return;
    }if (prenom==''){
        console.log("erreur nom");
        return;
    }if (classe==''){
        console.log("erreur classe");
        return;
    }if (sexe==''){
        console.log("erreur sexe");
        return;
    }if (dateExam==''){
        console.log("erreur date");
        return;
    }if (anonymat==''){
        console.log("erreur annomnyme");
        return;
    }
    datajson = {nom:nom,prenom:prenom,sexe:sexe,age:age,classe:classe,anonymat:anonymat,dateExamen:dateExam};
    console.log(datajson);
    ajoutPatient(datajson);
}