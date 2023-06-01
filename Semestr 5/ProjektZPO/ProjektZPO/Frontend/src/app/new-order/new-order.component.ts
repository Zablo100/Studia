import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-new-order',
  templateUrl: './new-order.component.html',
  styleUrls: ['./new-order.component.css']
})
export class NewOrderComponent implements OnInit {
  orderForm: FormGroup

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.createForm()
  }

  createForm(){
    this.orderForm = new FormGroup({
      user: new FormControl(window.sessionStorage.getItem("user")),
      komputer: new FormControl(window.sessionStorage.getItem("pc"), Validators.required),
      item: new FormControl("Myszka", Validators.required),
      amount: new FormControl("1", Validators.required),
      info: new FormControl("", Validators.required),
    })
  }

  createNewOrder(){
    console.log(this.orderForm.value)
  }

}
