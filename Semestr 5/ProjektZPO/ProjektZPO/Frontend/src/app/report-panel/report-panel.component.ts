import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ViewEncapsulation } from '@angular/core';
import { MatDialogRef, MatDialog } from '@angular/material/dialog';
import { ReportDetailsComponent } from '../report-details/report-details.component';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ticket } from '../models/ticket';

@Component({
  selector: 'app-report-panel',
  templateUrl: './report-panel.component.html',
  styleUrls: ['./report-panel.component.css'],

})
export class ReportPanelComponent implements OnInit {
  rawTickets: ticket[];
  tickets: ticket[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get("https://localhost:7129/api/reports").subscribe((response) => {
      this.rawTickets = response as ticket[]
      this.filterTickets()
    })
  }

  filterTickets(){
    for (const ticket of this.rawTickets){
      if(ticket.status == "oczekujące"){
        this.tickets.push(ticket)
      }
    }
  }


}
