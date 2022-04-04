function lineChart(liste_x,liste_y,pointBackgroundColor,label,mychart){
    const ctx = document.getElementById(mychart).getContext('2d');
    const myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: liste_x,
            datasets: [{
                label: label,
                data:  liste_y,
                fill: false,
                borderColor: 'rgb(75, 192, 192)',
                tension: 0.1,
                pointBackgroundColor:pointBackgroundColor
            }]
        },
    });
}