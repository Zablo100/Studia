import { Component, Input, OnInit, ViewEncapsulation } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ticket } from '../models/ticket';
import { ReportDetailsComponent } from '../report-details/report-details.component';

@Component({
  selector: 'app-ticket-card',
  templateUrl: './ticket-card.component.html',
  styleUrls: ['./ticket-card.component.css']
})
export class TicketCardComponent {
  @Input() ticket: ticket;

  constructor(private matDialog: MatDialog, private router: Router) { }

  openModal(e: any) {
    let reportId = e.target.id;
    this.matDialog.open(ReportDetailsComponent, {
      "autoFocus": false,
      data: {
        reportId: reportId,
        userRole: "mod"
      }
    });
  }


  isAdmin(){
    if (this.router.url == "/admin"){
      return true
    }

    return false
  }

  isMod(){
    if (this.router.url == "/mod"){
      return true
    }

    return false
  }

  isUser(){
    if (this.router.url == "/user"){
      return true
    }

    return false
  }



}
