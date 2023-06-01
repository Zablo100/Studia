<?php
session_start();
if (!isset($_SESSION['loggedin11'])) {
    header('Location: logowanie/index3.php');
    exit();
}?>
<style>
    .content{
        display: flex;
    }
    
    #y1{
        background-color: yellow;
        position: absolute;
        font-size: 2em;
        left: 5em;
        top: 4.5em;
        
        z-index: 1;
    }
    
    #y2{
        background-color: yellow;
        position: absolute;
        font-size: 2em;
        left: 13em;
        top: 6em;
        
        z-index: 1;
    }
    #y3{
        background-color: yellow;
        position: absolute;
        font-size: 2em;
        left: 16em;
        top: 12em;
        
        z-index: 1;
    }
    
    #y4{
        background-color: yellow;
        position: absolute;
        font-size: 2em;
        left: 26em;
        top: 6em;
        
        z-index: 1;
    }
    
    #y5{
        background-color: yellow;
        position: absolute;
        font-size: 2em;
        left: 26em;
        top: 12em;
    }
    
    #temp1{
        position: absolute;
        height: 15em;
        width: 12em;

        
        opacity: 0.6;
        
        left: 4em;
        top: 4em;
    }
    
    #temp2{
        position: absolute;
        height: 15em;
        width: 20em;

        
        opacity: 0.6;
        
        left: 20em;
        top: 4em;
    }
    
    #temp3{
        position: absolute;
        height: 10em;
        width: 15em;

        
        opacity: 0.6;
        
        left: 26em;
        top: 21em;
    }
    
    #wykres{
        height: 35em;
        width: 45em;
    }
    }
</style>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<div class="content">
            <div id="obrazek">
                <img src="mieszkanie.jpg">
                <span id="y1"></span>
                <span id="y2"></span>
                <span id="y3"></span>
                <div id="temp1"></div>
                <div id="temp2"></div>
                <div id="temp3"></div>
                </div>
        <div id="koc">
            <img src="Koc.png">
            <span id="y4"></span>
            <span id="y5"></span>
        </div>
        <div id="wykres">
            <canvas id="myChart"></canvas>
        </div>

</div>
<div class="content">
    <img id="fire" src="icons/falarm.png" width="80px" height="150px">
    <img id="r" src="icons/ralarm.jpg" width="150px" height="150px">
    <img id="co" src="icons/coalarm.png" width="150px" height="150px">
    <img id="pipe">
    <img id="klima">
</div>

<script>
var evtSource = new EventSource('scadasource.php');
const ctx = document.getElementById('myChart');
const data2 = {
  labels: ["1","2","3","4","5"],
  datasets: [
    {
      label: 'X1',
      data: [0,0,0,0,0],
      yAxisID: 'y',
    },
    {
      label: 'X2',
      data: [0,0,0,0,0],
      yAxisID: 'y',
    },
    {
      label: 'X3',
      data: [0,0,0,0,0],
      yAxisID: 'y',
    },
    {
      label: 'X4',
      data: [0,0,0,0,0],
      yAxisID: 'y',
    },
    {
      label: 'X5',
      data: [0,0,0,0,0],
      yAxisID: 'y',
    }
  ]
};


var chart = new Chart(ctx, {
    type: 'line',
    data: data2
    ,
    options: {
      animation: {
        duration: 0
    }
    }
  });


evtSource.onmessage = function(e) 
{ 
var data = e.data; 
data = data.split("\t"); 
        const x1 = parseInt(data[0])
        const x2 = parseInt(data[1])
        const x3 = parseInt(data[2])
        const x4 = parseInt(data[3])
        const x5 = parseInt(data[4])
        const fire = parseInt(data[5])
        const r = parseInt(data[6])
        const pipe = parseInt(data[7])
        const klima = parseInt(data[8])
        const co = parseInt(data[9])
        var t1 = []
        var t2 = []
        var t3 = []
        var t4 = []
        var t5 = []
        
        t1.push(parseInt(data[10]))
        t1.push(parseInt(data[11]))
        t1.push(parseInt(data[12]))
        t1.push(parseInt(data[13]))
        t1.push(parseInt(data[14]))
        
        t2.push(parseInt(data[15]))
        t2.push(parseInt(data[16]))
        t2.push(parseInt(data[17]))
        t2.push(parseInt(data[18]))
        t2.push(parseInt(data[19]))
        
        t3.push(parseInt(data[20]))
        t3.push(parseInt(data[21]))
        t3.push(parseInt(data[22]))
        t3.push(parseInt(data[23]))
        t3.push(parseInt(data[24]))
        
        t4.push(parseInt(data[25]))
        t4.push(parseInt(data[26]))
        t4.push(parseInt(data[27]))
        t4.push(parseInt(data[28]))
        t4.push(parseInt(data[29]))
        
        t5.push(parseInt(data[30]))
        t5.push(parseInt(data[31]))
        t5.push(parseInt(data[32]))
        t5.push(parseInt(data[33]))
        t5.push(parseInt(data[34]))
        
        
        y1.innerHTML = x1
        y2.innerHTML = x2
        y3.innerHTML = x3
        y4.innerHTML = x4
        y5.innerHTML = x5

        
        if(x1 < 10){
            document.getElementById("temp1").style.backgroundColor = "blue";
        }else if(x1 >= 10 & x1 < 20){
            document.getElementById("temp1").style.backgroundColor = "green";
        }else if(x1 >= 20 & x1 < 25){
            document.getElementById("temp1").style.backgroundColor = "transparent";
        }else if(x1 >= 25 & x1 < 30){
            document.getElementById("temp1").style.backgroundColor = "orange";
        }else if( x1 >= 30){
            document.getElementById("temp1").style.backgroundColor = "red";
        }
        
        
        if(x2 < 10){
            document.getElementById("temp2").style.backgroundColor = "blue";
        }else if(x2 >= 10 & x2 < 20){
            document.getElementById("temp2").style.backgroundColor = "green";
        }else if(x2 >= 20 & x2 < 25){
            document.getElementById("temp2").style.backgroundColor = "transparent";
        }else if(x2 >= 25 & x2 < 30){
            document.getElementById("temp2").style.backgroundColor = "orange";
        }else if( x2 >= 30){
            document.getElementById("temp2").style.backgroundColor = "red";
        }
        
        if(x3 < 10){
            document.getElementById("temp3").style.backgroundColor = "blue";
        }else if(x3 >= 10 & x3 < 20){
            document.getElementById("temp3").style.backgroundColor = "green";
        }else if(x3 >= 20 & x3 < 25){
            document.getElementById("temp3").style.backgroundColor = "transparent";
        }else if(x3 >= 25 & x3 < 30){
            document.getElementById("temp3").style.backgroundColor = "orange";
        }else if( x3 >= 30){
            document.getElementById("temp3").style.backgroundColor = "red";
        }
        

        if(fire){
            document.getElementById("fire").src="icons/fire.jpg";
        }
        
        if(r){
            document.getElementById("r").src="icons/r.png";
        }
        
        if(pipe){
            document.getElementById("pipe").src="icons/pipe.png";
            document.getElementById("pipe").style.height = '100px';
            document.getElementById("pipe").style.width = '200px';
        }
        
        if(klima){
            document.getElementById("klima").src="icons/klima.png";
            document.getElementById("klima").style.height = '100px';
            document.getElementById("klima").style.width = '200px';
        }
        
        if(co){
            document.getElementById("co").src="icons/cod.png";
        }
        
        const data3 = {
  labels: [1,2,3,4,5],
  datasets: [
    {
      label: 'X1',
      data: t1,
      yAxisID: 'y',
    },
    {
      label: 'X2',
      data: t2,
      yAxisID: 'y',
    },
    {
      label: 'X3',
      data: t3,
      yAxisID: 'y',
    },
    {
      label: 'X4',
      data: t4,
      yAxisID: 'y',
    },
    {
      label: 'X5',
      data: t5,
      yAxisID: 'y',
    }
  ]
};
        
        chart.config.data = data3;
        chart.update();
};
        
evtSource.onerror = function() { evtSource.close(); console.log('Done!'); };
</script>