import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { user } from '../models/user';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: user[]

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getUsers()
  }

  async getUsers(){
    this.http.get("https://localhost:7129/api/admin/users").subscribe((response) => {
      this.users = response as user[]
      console.log(this.users)
    })
  }

}
