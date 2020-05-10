import { NgModule } from '@angular/core';
import { SharedModule } from 'src/app/shared/shared.module';
import { DashboardRoutingModule } from './dashboard-routing.module';

import { MainLayoutComponent } from './main-layout.component';
import { HeaderComponent } from './header.component';
import { VehicleListComponent } from './vehicle/vehicle-list.component';
import { PhoneListComponent } from './phone/phone-list.component';

@NgModule({
  declarations: [
    MainLayoutComponent,
    HeaderComponent,
    VehicleListComponent,
    PhoneListComponent
  ],
  imports: [
    SharedModule,
    DashboardRoutingModule
  ],
  exports: [
  ]
})
export class DashboardModule { }
