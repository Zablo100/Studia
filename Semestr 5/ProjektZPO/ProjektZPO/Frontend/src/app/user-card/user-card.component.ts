import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Role, user } from '../models/user';
import { UserEditPanelComponent } from '../user-edit-panel/user-edit-panel.component';

@Component({
  selector: 'app-user-card',
  templateUrl: './user-card.component.html',
  styleUrls: ['./user-card.component.css']
})
export class UserCardComponent implements OnInit {
  @Input() user: any;

  constructor(private matDialog: MatDialog) { }

  ngOnInit(): void {
  }

  getRole(){
    return Role[this.user.role]
  }

  getPC(){
    if (this.user.computer != null){
      return this.user.computer.name
    }

    return "Brak"
  }

  openEditWidnow(){
    this.matDialog.open(UserEditPanelComponent, {
      "autoFocus": false,
      height: '550px',
      width: '400px',
      data: {
        user: this.user
      }
    });
  }

}
