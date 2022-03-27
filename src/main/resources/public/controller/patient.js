function ajoutPatient(dataJson){
    $.ajax({
        url: url + 'patient' ,
        data : dataJson,
        method: 'POST'
    })
        .done((data)=> {})
        .fail((data) => {})
}

function information(){
    let age = $("#addPatientage").val();
    let nom = $("#addPatientLibelle").val();
    let classe = $("#addPatientclasse").val();
    let sexe = $("#addPatientsexe").val();
    let dateExam = $("#addPatientdateExamen").val();
    let anonymat = $("#addPatientAnonymat").val();
    let evaluation = $("#addPatientEvaluation").val();
    let evaluationComparer = $("#addPatientEvaluationAComparer").val();
    console.log(age,nom,classe,sexe,dateExam,anonymat,evaluation,evaluationComparer)

    if (age==''){
    }if (nom==''){
    }if (classe==''){
    }if (sexe==''){
    }if (dateExam==''){
    }if (anonymat==''){
    }if (evaluation==''){
    }if (evaluationComparer==''){
    }

    if(localStorage.getItem("analyse")==="BHK") {
        $( "#container" ).load( "page/consigneBHK.html" );
    } else $( "#container" ).load( "page/consignePangramme.html" );
}