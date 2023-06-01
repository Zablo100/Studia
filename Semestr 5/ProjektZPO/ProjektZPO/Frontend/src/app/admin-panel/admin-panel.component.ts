import { Component, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { Observable } from 'rxjs';
import { ticket } from '../models/ticket';
import { HttpClient } from '@angular/common/http';
import { TicketCardComponent } from '../ticket-card/ticket-card.component';
import { UserListComponent } from '../user-list/user-list.component';
import { NewUserPanelComponent } from '../new-user-panel/new-user-panel.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {
  @ViewChild("panel", {read: ViewContainerRef}) conteiner: ViewContainerRef
  tickets: ticket[]

  constructor(private http: HttpClient, private matDialog: MatDialog) { }

  ngOnInit(): void {
    this.http.get("https://localhost:7129/api/reports").subscribe((response) => {
      this.tickets = response as ticket[];
      this.startUserPanel()
    })
  }

  startUserPanel(){
    this.conteiner.clear()

    this.conteiner.createComponent(UserListComponent)
  }

  createNew(){
    this.matDialog.open(NewUserPanelComponent, {
      "autoFocus": false,
      height: '550px',
      width: '400px',
    });
  }


}
