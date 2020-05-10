import { NgModule } from '@angular/core';
import { SharedModule } from 'src/app/shared/shared.module';
import { DashboardRoutingModule } from './dashboard-routing.module';

import { MainLayoutComponent } from './main-layout.component';
import { HeaderComponent } from './header.component';

@NgModule({
  declarations: [
    MainLayoutComponent,
    HeaderComponent
  ],
  imports: [
    SharedModule,
    DashboardRoutingModule
  ],
  exports: [
  ]
})
export class DashboardModule { }
