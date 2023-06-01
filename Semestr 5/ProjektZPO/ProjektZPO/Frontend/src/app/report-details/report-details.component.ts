import { Component, OnInit, Inject, ViewChild, ElementRef } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ViewEncapsulation } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ticket, ticketClass } from '../models/ticket';
import { mod } from '../models/user';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-report-details',
  templateUrl: './report-details.component.html',
  styleUrls: ['./report-details.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ReportDetailsComponent implements OnInit {
  @ViewChild('comm') comment: ElementRef<HTMLTextAreaElement>
  report: ticket =  new ticketClass();
  changeEmpForm: FormGroup;
  mods: mod[];
  userRole: string | null;
  userName: string | null;

  constructor(private matDialog: MatDialog, private http: HttpClient, @Inject(MAT_DIALOG_DATA) public data: any, private notification: ToastrService) { }

  ngOnInit(): void {
    let reportId = this.data.reportId
    this.createForm()
    this.getTicket(reportId)
    this.getEmployees()
    this.userRole = window.sessionStorage.getItem("role")
    this.userName = window.sessionStorage.getItem("user")
  }

  canView(){
    if (this.userRole == "mod" || this.userRole == "admin"){
      return true
    }

    return false
  }

  isAdmin(){
    if (this.userRole == "admin"){
      return true
    }

    return false
  }

  async getTicket(id: number){
    this.http.get(`https://localhost:7129/api/reports/${id}`).subscribe((response) => {
      this.report = response as ticket;
      this.getComments(id)
    })
  }

  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }

  async closeDialog(){
    await this.delay(100)
    this.matDialog.closeAll()
    window.location.reload()
  }

  async getComments(id: number){
    this.http.get("https://localhost:7129/api/comments/" + id).subscribe((response) => {
      this.generateCommentsHTML(response)
    })
  }

  async generateCommentsHTML(comments: any){
    const com = document.getElementById("comments");

    if (com){
      for(const element of comments){
        com.innerHTML += `
        <div class="comment">
          <div class="comment-date">${element.date}</div>
          <div class="comment-line"></div>
          <div class="comment-info">${element.comment}</div>
        </div>
        `
        }
      }
  }

  async clearComments(){
    const com = document.getElementById("comments");

    
    com!.innerHTML = "";
  }

  async addComment(){
    var comment = {
        "comment": this.comment.nativeElement.value,
        "reportId": this.report.id
    }

    const headers = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    }

    var body = JSON.stringify(comment) 

    this.http.post("https://localhost:7129/api/comments", body, headers).subscribe(response => {
      console.log(response)
    })

    this.comment.nativeElement.value = ""

    this.clearComments()

    await this.delay(200)
    
    this.getComments(this.report.id)

  }

  async acceptTicket(){
    var body = {
      "status": "1",
      "emp": this.userName
    }

    this.http.put(`https://localhost:7129/api/reports/${this.report.id}`, body).subscribe((response) => {
      this.clearComments();
      this.ngOnInit();
    })

    this.notification.success("Przyjęto zgłoszenie")

  }

  async closeTicket(){
    var body = {
      "status": "2",
      "emp": this.userName
    }

    this.http.put(`https://localhost:7129/api/reports/${this.report.id}`, body).subscribe((response) => {
      this.closeDialog();
    })

    this.notification.success("Zgłoszenie zostało zakończone")
  }

  async getEmployees(){
    this.http.get("https://localhost:7129/api/user").subscribe((response) => {
      this.mods = response as mod[]
    })
  }

  createForm(){
    this.changeEmpForm = new FormGroup({
      emp: new FormControl("")
    })
  }

  async addEmpToTicket(){
    var emp = this.changeEmpForm.value
    var body = {
      "status": "1",
      "emp": emp.emp
    }

    this.http.put(`https://localhost:7129/api/reports/${this.report.id}`, body).subscribe((response) => {
      this.clearComments();
      this.ngOnInit();
    })
    
    this.notification.success(`Przypisano ${emp.emp} do zgłoszenia`)
  }
}
