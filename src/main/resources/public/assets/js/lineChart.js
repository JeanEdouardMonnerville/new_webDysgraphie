function lineChart(data,label,mychart){
    const ctx = document.getElementById(mychart).getContext('2d');
    const myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: data.liste_x,
            datasets: [{
                label: label,
                data:  data.liste_y,
                fill: false,
                borderColor: 'rgb(75, 192, 192)',
                tension: 0.1
            }]
        },
    });
}