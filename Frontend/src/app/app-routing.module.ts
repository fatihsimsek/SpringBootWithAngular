import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: "", pathMatch: 'full', redirectTo: '/authentication/login' 
  },
  /*{
    path: "dashboard", loadChildren: "./features/dashboard/dashboard.module#DashboardModule",
  },*/
  {
    path: "authentication", loadChildren: "./features/authentication/authentication.module#AuthenticationModule",
  },
  /*{
    path: "miscellaneous", loadChildren: "./features/miscellaneous/miscellaneous.module#MiscellaneousModule",
  },
  { 
    path: "**", redirectTo: "miscellaneous/error404" 
  }*/
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
