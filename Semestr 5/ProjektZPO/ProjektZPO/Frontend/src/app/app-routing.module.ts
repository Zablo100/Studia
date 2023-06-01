import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { adminGuard, userGuard } from './guard';
import { LoginPanelComponent } from './login-panel/login-panel.component';
import { ModPanelComponent } from './mod-panel/mod-panel.component';
import { RaportPanelComponent } from './raport-panel/raport-panel.component';
import { ReportPanelComponent } from './report-panel/report-panel.component';
import { UserPanelComponent } from './user-panel/user-panel.component';

const routes: Routes = [
  {path: "", component: LoginPanelComponent},
  {path: "reports", component: ReportPanelComponent, canActivate: [adminGuard]},
  {path: "user", component: UserPanelComponent, canActivate: [userGuard]},
  {path: "admin", component: AdminPanelComponent, canActivate: [adminGuard]},
  {path: "mod", component: ModPanelComponent, canActivate: [adminGuard]},
  {path: "raport", component: RaportPanelComponent, canActivate: [adminGuard]},
  {path: "**", redirectTo: "", pathMatch: "full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
