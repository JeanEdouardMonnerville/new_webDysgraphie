let te = 0;

function postResults() {
    $.ajax({
        url: 'ecriture',
        method:'post',
        data: {
            "liste_point":te,
            "token" : localStorage.getItem("token")
        },
        datatype:'html',
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
        data : {'token':localStorage.getItem('token')},
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

//---------------------------CANVAS
//change pen color
function changeColor(){
    color = '#'+(Math.random()*0xFFFFFF<<0).toString(16);
}

//récupérer pression
Pressure.set('#canvas_draw', {
    change: function(force, evt0){
        chronoOn();
        $.ajax({
            type:'post',
            url:'/addPression',
            data: {
                "pression":force
            },
            error:function(resultat,statut,error){
                console.log("error pression")
            }
        });
    }
}, {polyfill: true});

// start drawing
function moveDrawligne(oEvent){
    var oCanvas = oEvent.currentTarget,
        oCtx = null, oPos = null;
    if(oCanvas.bDraw ==false){
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

function getPosition(oEvent, oCanvas){
    var oRect = oCanvas.getBoundingClientRect(),
        oEventEle = oEvent.changedTouches? oEvent.changedTouches[0]:oEvent;
    var x = (oEventEle.clientX - oRect.left) / (oRect.right - oRect.left) * oCanvas.width;
    var y =(oEventEle.clientY - oRect.top) / (oRect.bottom - oRect.top) * oCanvas.height;
    $.ajax({
        type:'post',
        url:'/addPoint',
        data: {
            "pointX":parseInt(x),
            "pointY":parseInt(y)
        },
        error:function(resultat,statut,error){
            console.log(error.responseText)
        }
    });
    return {
        posX : x,
        posY : y
    };
}

function downDrawligne(oEvent){
    t1 = Date.now();
    changeColor();
    oEvent.preventDefault();
    var  oCanvas = oEvent.currentTarget,
        oPos = getPosition(oEvent, oCanvas);
    oCanvas.posX = oPos.posX;
    oCanvas.posY = oPos.posY;
    oCanvas.bDraw = true;
    capturer(false);
}

function upDrawligne(oEvent){
    t2 = Date.now()
    te = te + (t2-t1)
    var oCanvas = oEvent.currentTarget;
    oCanvas.bDraw = false;
    capturer(true);
}

function initCanvas(){
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
 * Récupère le canva sous forme d'image
 */
function capturer(bAction){
    var oCapture = document.getElementById("capture_canvas");
    oCapture.innerHTML = '';
    if(bAction == true){
        var oImage = document.createElement('img'),
            oCanvas = document.getElementById("canvas_draw");
        oImage.src = oCanvas.toDataURL("image/png");
        oCapture.appendChild(oImage);
    }
}

/**
 * Vide les dessin du canvas
 */
function nettoyer(oEvent){
    var  oCanvas = document.getElementById("canvas_draw"),
        oCtx = oCanvas.getContext('2d');
    oCtx.clearRect(0,0,oCanvas.width,oCanvas.height);
    capturer(false);

    $.ajax({
        url: '/erase',
        success: function (response) {
            console.log("Canvas effacé");
        }
    });
}

// remove callback function when mouse up
canvas_draw.onmouseup = function(evt) {
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