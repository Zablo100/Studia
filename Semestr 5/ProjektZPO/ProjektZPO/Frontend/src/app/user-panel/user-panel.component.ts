import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild, ViewContainerRef, ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ticket } from '../models/ticket';
import { NewOrderComponent } from '../new-order/new-order.component';
import { NewTicketComponent } from '../new-ticket/new-ticket.component';
import { ReportDetailsComponent } from '../report-details/report-details.component';

@Component({
  selector: 'app-user-panel',
  templateUrl: './user-panel.component.html',
  styleUrls: ['./user-panel.component.css']
})
export class UserPanelComponent implements OnInit {
  @ViewChild("newTicket", {read: ViewContainerRef}) conteiner: ViewContainerRef

  tickets: ticket[] = [];
  userName: string;


  constructor(private http: HttpClient, private matDialog: MatDialog) { }

  ngOnInit() {
    this.userName = this.getUserName()
    this.getReportByPerson(this.userName)
    this.newTicketform()
  }


  getUserName(){
    let name = window.sessionStorage.getItem("user");
    if (name == null){
      return ""
    }

    return name
  }


  async getReportByPerson(preson: string){
    this.http.get("https://localhost:7129/api/user/" + preson).subscribe((response) => {
      this.tickets = response as ticket[];
      this.sortTicketsByStatus();
    })
  }


  sortTicketsByStatus(){
    for (const element of this.tickets){
      if(element.status == "zakończone"){
      this.tickets.push(this.tickets.splice(this.tickets.indexOf(element), 1)[0]);
      }
    }
  }


  newTicketform(){
    this.conteiner.clear()

    this.conteiner.createComponent(NewTicketComponent)
  }

  newOrderform(){
    this.conteiner.clear()

    this.conteiner.createComponent(NewOrderComponent)
  }
  
}
