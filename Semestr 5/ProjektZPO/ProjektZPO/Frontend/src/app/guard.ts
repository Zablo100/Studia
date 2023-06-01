import { Injectable } from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { Observable } from "rxjs";


@Injectable()
export class adminGuard implements CanActivate {
    constructor(private router: Router, private notification: ToastrService) {}
    
    canActivate() {
      if (this.isAdmin()){
        return true
      }
      return false;
    }

    isAdmin(){
        const role = window.sessionStorage.getItem("role");
        if (role == "mod" || role == "admin"){
            return true
        }

        this.notification.error("Nie masz uprawnień do tej witryny")
        this.router.navigateByUrl("/")
        return false
    }
  }

@Injectable()
export class userGuard implements CanActivate{
    constructor(private router: Router, private notification: ToastrService) {}

    canActivate() {
        if (this.isLogIn()){
            return true
          }
          return false;
    }

    isLogIn(){
        const role = window.sessionStorage.getItem("role");
        if (role == "mod" || role == "admin" || role == "user"){
            return true
        }

        this.notification.error("Nie masz uprawnień do tej witryny")
        this.router.navigateByUrl("/")
        return false
    }

}