import { HttpClient, HttpHeaders, HttpStatusCode } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { user } from '../models/user';
import {Router} from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login-panel',
  templateUrl: './login-panel.component.html',
  styleUrls: ['./login-panel.component.css']
})
export class LoginPanelComponent implements OnInit {
  loginForm: FormGroup
  
  constructor(private http: HttpClient, private router: Router, private notification: ToastrService) { }

  ngOnInit(): void {
    this.createLoginForm()
  }

  createLoginForm(){
    this.loginForm = new FormGroup({
      username: new FormControl("", Validators.required),
      password: new FormControl("", Validators.required)
    })
  }

  async Login(){
    const headers = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    }

    var body = JSON.stringify(this.loginForm.value) 

    this.http.post("https://localhost:7129/api/user", body, headers).subscribe(response => {
      let user = response as user;
      window.sessionStorage.setItem("user", user.username)
      window.sessionStorage.setItem("pc", user.pc)
      switch (user.role){
        case 2:
          window.sessionStorage.setItem("role", "admin")
          this.router.navigate(["./admin"])
          break;
        case 1:
          window.sessionStorage.setItem("role", "mod")
          this.router.navigate(["./reports"])
          break;
        case 0:
          window.sessionStorage.setItem("role", "user")
          this.router.navigate(["./user"])
          break;
      }
    },
    error => {
      this.notification.error("Podane hasło lub nazwa użytkownika jest nieprawidłowa")
    }
    )

  }


}
