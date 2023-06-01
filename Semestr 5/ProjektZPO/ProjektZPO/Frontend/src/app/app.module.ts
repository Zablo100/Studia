import { HttpClient, HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatDialogRef, MatDialog, MatDialogModule } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReportPanelComponent } from './report-panel/report-panel.component';
import { LoginPanelComponent } from './login-panel/login-panel.component';
import { ReportDetailsComponent } from './report-details/report-details.component';
import { UserPanelComponent } from './user-panel/user-panel.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { ReactiveFormsModule } from '@angular/forms';
import { TicketCardComponent } from './ticket-card/ticket-card.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { ModPanelComponent } from './mod-panel/mod-panel.component';
import { RaportPanelComponent } from './raport-panel/raport-panel.component';
import { adminGuard, userGuard } from './guard';
import { NewTicketComponent } from './new-ticket/new-ticket.component';
import { NewOrderComponent } from './new-order/new-order.component';
import { ChartComponent } from './chart/chart.component';
import { UserEditPanelComponent } from './user-edit-panel/user-edit-panel.component';
import { UserCardComponent } from './user-card/user-card.component';
import { UserListComponent } from './user-list/user-list.component';
import { NewUserPanelComponent } from './new-user-panel/new-user-panel.component';


@NgModule({
  declarations: [
    AppComponent,
    ReportPanelComponent,
    LoginPanelComponent,
    ReportDetailsComponent,
    UserPanelComponent,
    AdminPanelComponent,
    TicketCardComponent,
    NavBarComponent,
    ModPanelComponent,
    RaportPanelComponent,
    NewTicketComponent,
    NewOrderComponent,
    ChartComponent,
    UserEditPanelComponent,
    UserCardComponent,
    UserListComponent,
    NewUserPanelComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatDialogModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    ToastrModule.forRoot()
  ],
  providers: [ adminGuard, userGuard ],
  bootstrap: [AppComponent]
})
export class AppModule { }
