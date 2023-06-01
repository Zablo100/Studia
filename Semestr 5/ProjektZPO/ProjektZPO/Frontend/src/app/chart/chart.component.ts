import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Data } from '@angular/router';
import { Chart } from 'chart.js/auto';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {
  lables: any[]
  data: number[]
  max: number;
  type: any;

  constructor() { }


  ngOnInit(): void {
    new Chart("myChart", {
      type: this.type,
      data: {
        labels: this.lables,
        datasets: [{
          label: '# of Votes',
          data: this.data,
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true,
            max: this.max,
            ticks: {
              stepSize: 1
            }
          }
        }
      }
    });
  }

}
