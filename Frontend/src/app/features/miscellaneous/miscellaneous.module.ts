import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {routing} from "./miscellaneous-routing";
import { Error404Component } from './error404/error404.component';

@NgModule({
  imports: [
    CommonModule,
    routing
  ],
  declarations: [
    Error404Component
  ]
})
export class MiscellaneousModule { }
