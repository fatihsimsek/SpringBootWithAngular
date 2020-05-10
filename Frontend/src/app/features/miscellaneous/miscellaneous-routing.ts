import {ModuleWithProviders} from "@angular/core"
import {Routes, RouterModule} from "@angular/router";
import { Error404Component } from './error404/error404.component';

export const routes: Routes = [
  {
    path: '', redirectTo: 'error404', pathMatch: 'full'
  },
  {
    path: 'error404', component: Error404Component
  }
];

export const routing = RouterModule.forChild(routes);




