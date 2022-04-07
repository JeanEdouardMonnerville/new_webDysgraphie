function ajoutPatient(dataJson){
    postPatient(dataJson)
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
    let nom = $("#addPatientNom").val();
    let prenom = $("#addPatientPrenom").val();
    let classe = $("#addPatientclasse").val();
    let sexe = $("#addPatientsexe").val();
    let dateExam = $("#addPatientdateExamen").val();
    let anonymat = $("#addPatientAnonymat").val();
    addPatientdateExamen
    if ($('#addPatientdateExamen').val() ===''){
        $('.alert-danger').removeClass('d-none')
        $('.alert-danger').html("La date d'examen doit être renseigner.")
        console.log("date required")
        return null
    }else  $('.alert-danger').addClass('d-none');
    datajson = {nom:nom,prenom:prenom,sexe:sexe,age:age,classe:classe,anonymat:anonymat,dateExamen:dateExam};
    ajoutPatient(datajson);
}