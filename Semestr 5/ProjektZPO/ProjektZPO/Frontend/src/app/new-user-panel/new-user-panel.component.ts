import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-new-user-panel',
  templateUrl: './new-user-panel.component.html',
  styleUrls: ['./new-user-panel.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class NewUserPanelComponent implements OnInit {
  newUserForm: FormGroup
  pcList: any[];
  constructor(private http: HttpClient, private notification: ToastrService) { }

  ngOnInit(): void {
    this.getAllPC()
    this.createform()
  }

  createform(){
    this.newUserForm = new FormGroup({
      username: new FormControl("", Validators.required),
      password: new FormControl("", Validators.required),
      role: new FormControl(0 , Validators.required),
      pc: new FormControl(0 , Validators.required)
    })
  }

  getAllPC(){
    this.http.get("https://localhost:7129/api/admin/pc").subscribe((response) => {
      this.pcList = response as any
    })
  }

  submit() {
    this.http.post("https://localhost:7129/api/admin/user", this.newUserForm.value).subscribe((response) => {
      window.location.reload()
    })

    this.notification.success("Utworzono nowego użytkownika")
    }

}
