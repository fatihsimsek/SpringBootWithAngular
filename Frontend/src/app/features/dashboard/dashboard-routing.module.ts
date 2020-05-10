import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MainLayoutComponent } from './main-layout.component';
import { VehicleListComponent } from './vehicle/vehicle-list.component';
import { PhoneListComponent } from './phone/phone-list.component';

const routes: Routes = [
    {
      path: '',
      component: MainLayoutComponent,
      children: [
        { path: '', redirectTo: "vehicle", pathMatch: 'full' },
        {
            path: 'vehicle', component: VehicleListComponent
        },
        {
          path: 'phone', component: PhoneListComponent
        }
      ]
    },
  ];
  
  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class DashboardRoutingModule { }