import { HttpClient } from '@angular/common/http';
import { ThisReceiver } from '@angular/compiler';
import { Component, Inject, OnInit, ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { mod, Role } from '../models/user';

@Component({
  selector: 'app-user-edit-panel',
  templateUrl: './user-edit-panel.component.html',
  styleUrls: ['./user-edit-panel.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class UserEditPanelComponent implements OnInit {
  user: any;
  editForm: FormGroup;
  pcList: any[];


  constructor(private matDialog: MatDialog, private http: HttpClient, @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
    this.user = this.data.user
    this.getAllPC()
    this.createForm()
  }

  createForm() {
    const id = this.user.computer ? this.user.computer.id : 0
    this.editForm = new FormGroup({
      username: new FormControl(this.user.username, Validators.required),
      password: new FormControl(this.user.password, Validators.required),
      role: new FormControl(this.user.role, Validators.required),
      pc: new FormControl(id, Validators.required)
    })
  }

  submit(){
    var body = {
      id: this.user.id,
      username: this.editForm.value.username,
      password: this.editForm.value.password,
      role: this.editForm.value.role,
      pc: this.editForm.value.pc
    }

    this.http.put("https://localhost:7129/api/admin/user", body).subscribe((response) => {
      window.location.reload()
    })
  }

  getAllPC(){
    this.http.get("https://localhost:7129/api/admin/pc").subscribe((response) => {
      this.pcList = response as any
    })
  }

  deleteUser(){
    this.http.delete("https://localhost:7129/api/admin/user/" + this.user.id).subscribe((response) => {
      window.location.reload()
    })
  }

}
