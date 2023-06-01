import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-new-ticket',
  templateUrl: './new-ticket.component.html',
  styleUrls: ['./new-ticket.component.css']
})
export class NewTicketComponent implements OnInit {
  userInputForm: FormGroup
  userName: string;

  constructor(private http: HttpClient, private notification: ToastrService) { }

  ngOnInit(): void {
    this.createForm()
    this.userName = this.getUserName()
  }

  createForm(){
    this.userInputForm = new FormGroup({
      user: new FormControl(window.sessionStorage.getItem("user")),
      komputer: new FormControl(window.sessionStorage.getItem("pc"), Validators.required),
      opis: new FormControl("", Validators.required),
    })
  }

  async createNewTicket(){
    
    //this.userInputForm.controls['user'].setValue(this.userName)

    const headers = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    }

    var body = JSON.stringify(this.userInputForm.value) 


    this.http.post("https://localhost:7129/api/reports", body, headers).subscribe(response => {
      console.log(response)
    })


    this.notification.success("Utworzono nowe zgłoszenie")
    this.cleareForm();

    await this.delay(25) // Tymczasowy fix na błąd renderowania po dodaniu nowego postu.

  }

  cleareForm(){
    this.userInputForm.reset();
  }

  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }

  getUserName(){
    let name = window.sessionStorage.getItem("user");
    if (name == null){
      return ""
    }

    return name
  }

}
