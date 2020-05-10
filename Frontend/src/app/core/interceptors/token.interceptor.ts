import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { AuthenticationService } from '../services/authentication.service';
import { Observable } from 'rxjs';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(public authenticationService: AuthenticationService) {}
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    
    if(this.authenticationService.isAuthenticated()) {
        request = request.clone({
            setHeaders: {
              Authorization: `Bearer ${this.authenticationService.getToken()}`
            }
          });
    }
  
    return next.handle(request);
  }
}