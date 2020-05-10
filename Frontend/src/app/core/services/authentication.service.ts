import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { environment } from '../../../environments/environment';

@Injectable()
export class AuthenticationService {
  private tokenModel = null;

  constructor(private http: HttpClient, 
              private router: Router) { }

  login(email: string, password: string) {
    return this.http.post<any>(environment.apiUrl + "auth/signin",
      {
        email: email, 
        password: password
      }).pipe(map((response => {
        this.setToken(response);
        return response;
      })));
  }

  isAuthenticated(): boolean {
    return this.tokenModel != null;
  }

  setToken(tokenModel: any) {
    this.tokenModel = tokenModel;
  }

  logout(){
    this.tokenModel = null;
    this.router.navigateByUrl('/');
  }
}