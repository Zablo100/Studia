import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild, ViewContainerRef, ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ticket } from '../models/ticket';
import { TicketCardComponent } from '../ticket-card/ticket-card.component';

@Component({
  selector: 'app-mod-panel',
  templateUrl: './mod-panel.component.html',
  styleUrls: ['./mod-panel.component.css']
})
export class ModPanelComponent implements OnInit {
  @ViewChild("searchOutput", { read: ViewContainerRef }) conteiner!: ViewContainerRef;
  searchForm: FormGroup


  tickets: ticket[]
  myTickets: ticket[]
  filterTickets: ticket[]
  ids: any = []

  userRole: string | null;
  userName: string | null;


  constructor(private http: HttpClient) { }


  ngOnInit(): void {
    this.userName = window.sessionStorage.getItem("user")
    this.userRole = window.sessionStorage.getItem("role")
    this.createSearchForm()

    this.http.get("https://localhost:7129/api/reports").subscribe((response) => {
      this.tickets = response as ticket[];
      this.filterByEmp();
    });
  }

  async searchByPhrase(phrase: string) {
    this.http.get("https://localhost:7129/api/search/" + phrase).subscribe(async (response) => {
      this.ids = response
      console.log(`wynik: ${this.ids}`)
    })
  }

  createSearchForm() {
    this.searchForm = new FormGroup({
      search: new FormControl("", Validators.required)
    })
  }

  async filterByEmp() {
    this.myTickets = []
    for (const ticket of this.tickets) {
      if (ticket.pracownik == this.userName) {
        this.myTickets.push(ticket)
      }
    }
  }

  async filterBySerachInput() {
    this.filterTickets = []
    const value = this.searchForm.value.search
    const search = value.toLowerCase();

    if (search) {
      await this.searchByPhrase(search)
    }

    for (const ticket of this.tickets) {
      if (ticket.user.toLowerCase() == search) {
        this.filterTickets.push(ticket)
      } else if (ticket.komputer.toLowerCase() == search) {
        this.filterTickets.push(ticket)
      } else if (ticket.opis.toLowerCase().match(search)) {
        this.filterTickets.push(ticket)
      }
    }

    await this.delay(10) // tymczasowy fix na żeby pierw skończyło umieszczać respon z api w ids

    console.log(`${search} wynik: ${this.ids}`)
    for (const ticket of this.tickets) {
      if (this.ids.includes(ticket.id)) {
        if (!this.filterTickets.includes(ticket)) {
          this.filterTickets.push(ticket)
        }
      }
    }

    this.createSearchOutput()
  }

  async createSearchOutput() {
    this.conteiner.clear()

    for (const ticket of this.filterTickets) {
      const comp = this.conteiner.createComponent(TicketCardComponent)
      comp.instance.ticket = ticket
    }

  }

  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }



}
