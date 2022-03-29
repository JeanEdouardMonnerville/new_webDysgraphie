let d = false;
let td = 0;
let tf = 0;
let te = 0;
let num_point=1;
let liste_point = [];

function postResults() {
    console.log('lis')
    $.ajax({
        url: 'ecriture',
        method:'post',
        data: JSON.stringify({
            "liste_point":liste_point
        }),
        headers : { 'token':localStorage.getItem('token')},
        contentType:"application/json",
        success: function (response) {
            console.log("Envoi des résultats");
            console.log(response);
            // $("#details").html(response);
            // $("ul").unwrap();
        },
        error:function(e){
            console.log(e);
        }
    });

}

function download(){
    console.log("Lancement du téléchargement ...");
    $.ajax({
        url: 'resultat/download',
        headers : { 'token':localStorage.getItem('token')},
        success: function (response) {
            console.log(response)
            alert("Fichier Excel téléchargé !")
        },
        error:function(e){
            console.log(e);
        }
    });
}

function startTest(){
    if(localStorage.getItem("analyse")==="BHK") {
        $( "#container" ).load( "page/bhkTest.html" , function (){
            startTimer();
            initCanvas();
        });
    } else $( "#container" ).load( "page/consignePangramme.html" );
}
function bhkTstFini(){
    console.log("bhkTstFini")
    postResults();
}
//---------------------------CANVAS
color = "#000";
canvas_draw.width = canvas_draw.parentNode.offsetWidth - 10;
canvas_draw.height = canvas_draw.parentNode.offsetHeight - 10;


//change pen color
function changeColor() {
    color = '#' + (Math.random() * 0xFFFFFF << 0).toString(16);
}

// start drawing
function moveDrawligne(oEvent) {
    var oCanvas = oEvent.currentTarget,
        oCtx = null, oPos = null;
    if (oCanvas.bDraw == false) {
        return false;
    }//if
    oPos = getPosition(oEvent, oCanvas);
    oCtx = oCanvas.getContext('2d');

    //dessine
    oCtx.strokeStyle = color;
    oCtx.lineWidth = 1;
    oCtx.beginPath();
    oCtx.moveTo((oCanvas.posX), oCanvas.posY);
    oCtx.lineTo(oPos.posX, oPos.posY);
    oCtx.stroke();

    oCanvas.posX = oPos.posX;
    oCanvas.posY = oPos.posY;
}

function getPosition(oEvent, oCanvas) {
    var oRect = oCanvas.getBoundingClientRect(),
        oEventEle = oEvent.changedTouches ? oEvent.changedTouches[0] : oEvent;
    var x = (oEventEle.clientX - oRect.left) / (oRect.right - oRect.left) * oCanvas.width;
    var y = (oEventEle.clientY - oRect.top) / (oRect.bottom - oRect.top) * oCanvas.height;
    let inter=0;
    if(liste_point.length !=0){
        inter=oEvent.timeStamp-liste_point[liste_point.length-1].tps
    }
    liste_point.push({
            x: parseInt(x),
            y: parseInt(y),
            num:num_point,
            inter:inter,
            tps: oEvent.timeStamp,
        }
    );
    num_point++;
    return {
        posX: x,
        posY: y,
    };
}

function downDrawligne(oEvent) {
    t1 = Date.now();
    changeColor();
    oEvent.preventDefault();
    var oCanvas = oEvent.currentTarget,
        oPos = getPosition(oEvent, oCanvas);
    oCanvas.posX = oPos.posX;
    oCanvas.posY = oPos.posY;
    oCanvas.bDraw = true;
}

function upDrawligne(oEvent) {
    t2 = Date.now()
    te = te + (t2 - t1)
    var oCanvas = oEvent.currentTarget;
    oCanvas.bDraw = false;
}

function initCanvas() {
    var oCanvas = document.getElementById("canvas_draw");
    oCanvas.bDraw = false;
    oCtx = oCanvas.getContext('2d');
    oCanvas.addEventListener("mousedown", downDrawligne);
    oCanvas.addEventListener("mouseup", upDrawligne);
    oCanvas.addEventListener("mousemove", moveDrawligne);
    oCanvas.addEventListener("touchstart", downDrawligne);
    oCanvas.addEventListener("touchend", upDrawligne);
    oCanvas.addEventListener("touchmove", moveDrawligne);
}

/**
 * Vide les dessin du canvas
 */
function nettoyer(oEvent) {
    var oCanvas = document.getElementById("canvas_draw"),
        oCtx = oCanvas.getContext('2d');
    oCtx.clearRect(0, 0, oCanvas.width, oCanvas.height);
    capturer(false);
    liste_point=[];
}

document.addEventListener('DOMContentLoaded', function () {
    initCanvas();
    document.getElementById("button_clear").addEventListener("click", nettoyer);
});


// remove callback function when mouse up
canvas_draw.onmouseup = function (evt) {
    canvas_draw.onmousemove = {};
};

//---------------------------TIMER

function startTimer(){
    const textCorrection = (element, value) => {
        element.innerHTML = value < 10 ? "0" + value : value;
    }
    let disMinutes = document.querySelector(".minute");
    let disSeconds = document.querySelector(".seconds");
    let circleSvg = document.querySelector("circle");
    let interval;
// Temps du timer
    let totalTime = 5 * 60 ;
    disMinutes.innerHTML = "00";
    disSeconds.innerHTML = "00";

    circleSvg.style.animation = `Loop ${totalTime}s linear 1s`;
    circleSvg.style.animationPlayState = "running";

    interval = setInterval(() => {
        const minutes = Math.floor(totalTime / 60);
        const seconds = totalTime % 60;

        if (totalTime <= 10) {
            circleSvg.style.stroke = "var(--clr-primary)";
            disMinutes.style.animation = "popup 800ms infinite ease-in-out";
            disMinutes.style.animationPlayState = "running";

            disSeconds.style.animation = "popup 800ms infinite ease-in-out";
            disSeconds.style.animationPlayState = "running";
        } else {
            circleSvg.style.stroke = "var(--clr-remaining)";
            disMinutes.style.animation = "none";
            disSeconds.style.animation = "none";
        }

        textCorrection(disMinutes, minutes);
        textCorrection(disSeconds, seconds);

        if (totalTime > 0) {
            totalTime--;
        } else {
            circleSvg.style.animation = "none";
            alert("Temps écoulé.")
            postResults();
            clearInterval(interval);
        }
    }, 1000);
    return totalTime;
}