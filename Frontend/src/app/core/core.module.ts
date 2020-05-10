import { NgModule, ErrorHandler, ModuleWithProviders  } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AuthenticationGuard } from './guards/authentication.guard';
import { AuthenticationService } from './services/authentication.service';
import { ErrorInterceptor } from './interceptors/error.interceptor';
import { GlobalErrorHandler } from './global-error-handler';
import { TokenInterceptor } from './interceptors/token.interceptor';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule
  ],
  exports: [],
  providers: [
    AuthenticationGuard,
    AuthenticationService,
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
    { provide: ErrorHandler, useClass: GlobalErrorHandler }
  ]
})
export class CoreModule {
}