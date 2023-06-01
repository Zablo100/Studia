export interface ticket{
    id: number,
    date: string,
    user: string,
    komputer: string,
    opis: string,
    status: string,
    pracownik: string
}

export class ticketClass implements ticket{
// Żeby konsola nie płakała przy renderowaniu report-detail
    id: number;
    date: string;
    user: string;
    komputer: string;
    opis: string;
    status: string;
    pracownik: string;

    constructor(){
        this.id = 0
        this.date = ""
        this.komputer = ""
        this.opis = ""
        this.pracownik = ""
        this.status = ""
        this.user = ""
      }
}