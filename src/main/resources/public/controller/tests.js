function postResults() {
    postResult()
        .done((data)=> {
            console.log("Envoi des résultats");
            console.log(data);
            $( "#container" ).load( "page/resultat.html" );
            // $("ul").unwrap();
        })
        .fail((data)=> {
            console.log(data);
        })
}

function startTest(){
    if(localStorage.getItem("analyse")==="BHK") {
        $( "#container" ).load( "page/bhkTest.html" , function (){
            disMinutes = document.querySelector(".minute");
            disSeconds = document.querySelector(".seconds");
            startTimer();
            initCanvas();
        });
    } else {
        $( "#container" ).load( "page/pangrammeTest.html", function (){
            disMinutes = document.querySelector(".minute");
            disSeconds = document.querySelector(".seconds");
            initCanvas();
            startChrono();
        } );
    }
}
function testFini(){
    console.log("testFini")
    postResults();
    clearInterval(interval);
}
//---------------------------TIMER
let interval;
let totalTime
let disMinutes;
let disSeconds;

function startTimer(){

// Temps du timer
    totalTime = 5 * 60 ;
    disMinutes.innerHTML = "00";
    disSeconds.innerHTML = "00";
    let circleSvg = document.querySelector("circle");

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
        }
        else {
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
function annimationTimer(sup10){
    if (sup10){

    }else {
        disMinutes.style.animation = "popup 800ms infinite ease-in-out";
        disMinutes.style.animationPlayState = "running";

        disSeconds.style.animation = "popup 800ms infinite ease-in-out";
        disSeconds.style.animationPlayState = "running";
    }
}
function textCorrection (element, value) {
    element.innerHTML = value < 10 ? "0" + value : value;
}

function startChrono(){
    clearInterval(interval);
    totalTime=0
    disMinutes.innerHTML = "00";
    disSeconds.innerHTML = "00";
    interval = setInterval(function (){
        const minutes = Math.floor(totalTime / 60);
        const seconds = totalTime % 60;

        textCorrection(disMinutes, minutes);
        textCorrection(disSeconds, seconds);

        totalTime++;

    }, 1000);
}