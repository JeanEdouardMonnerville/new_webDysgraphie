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
    let nom = $("#addPatientLibelle").val();
    let prenom = $("#addPatientLibelle").val();
    let classe = $("#addPatientclasse").val();
    let sexe = $("#addPatientsexe").val();
    let dateExam = $("#addPatientdateExamen").val();
    let anonymat = $("#addPatientAnonymat").val();
    datajson = {nom:nom,prenom:prenom,sexe:sexe,age:age,classe:classe,anonymat:anonymat,dateExamen:dateExam};
    ajoutPatient(datajson);
}