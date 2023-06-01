import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ChartComponent } from '../chart/chart.component';
import { raport } from '../models/raport';
import { ticket } from '../models/ticket';

@Component({
  selector: 'app-raport-panel',
  templateUrl: './raport-panel.component.html',
  styleUrls: ['./raport-panel.component.css']
})
export class RaportPanelComponent implements OnInit {
  @ViewChild("chart", {read: ViewContainerRef}) chart!: ViewContainerRef;
  optionForm: FormGroup
  tickets: ticket[]
  datepickerUI: boolean = true
  userInput: boolean = false

  constructor(private http: HttpClient, private notification: ToastrService) { }

  ngOnInit(): void {
    this.createForm()
  }

  createForm() {
    this.optionForm = new FormGroup({
      type: new FormControl("", Validators.required),
      name: new FormControl("", Validators.required),
      start: new FormControl(""),
      end: new FormControl("")
    })
  }

  startChart(){
    console.log(this.optionForm.value)

    const input = this.optionForm.value

    if(input.type == "user" || input.type == "pc"){
      this.getDataFromAPI(input.name, input.type)
    }else{
      this.getDataByDate()
    }
  }

  changeDatepicketView(event: any){
    if (event.target.value == "time"){
      this.datepickerUI = false
      this.userInput = true
    }else{
      this.datepickerUI = true
      this.userInput = false
    }
  }


  createChart(lables: string[], data: number[]){
    this.cleareChart()

    const component = this.chart.createComponent(ChartComponent)
    component.instance.lables = lables
    component.instance.data = data
    component.instance.max = Math.max(...data) + 1;
    component.instance.type = 'bar'
  }

  cleareChart(){
    const myChart = document.getElementById("myChart")
    myChart?.remove()
  }

  async getDataFromAPI(name: string, type: string){
    const headers = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    }
    var body = JSON.stringify({"typ": type})


    this.http.post("https://localhost:7129/api/raport/" + name, body, headers).subscribe((response) => {
      this.tickets = response as ticket[];
      this.mapData()
    });
  }

  async getDataByDate(){
    var data = this.optionForm.value

    const headers = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    }
    var body = JSON.stringify({"start": data.start, "end": data.end});


    this.http.post("https://localhost:7129/api/raport", body, headers).subscribe((response) => {
      this.convertData(response as raport[])
    });
  }

  mapData(){
    const months = ["Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień"]
    var numberOfTicketByMonth = [0,0,0,0,0,0,0,0,0,0,0,0]


    for (const ticket of this.tickets){
      var  splitDate = ticket.date.split(".")

      var date = new Date(+splitDate[2].substring(0,3), +splitDate[1] -1, +splitDate[0])
      const month = date.getMonth()
      numberOfTicketByMonth[month] += 1

      }

      this.createChart(months, numberOfTicketByMonth)
    }

  convertData(raports: raport[]){
    var lables = []
    var data = []

    for (const raport of raports){
      lables.push(raport.pc)
      data.push(raport.ile)
    }

    this.createChart(lables, data)
  }
}
