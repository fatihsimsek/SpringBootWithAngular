import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';

@Injectable()
export class AuthenticationService {
  private userModel : any;
  private isValidAuth = false;

  constructor(private http: HttpClient, 
              private router: Router) { }

  login(userId: string, password: string) {
    return this.http.post<any>("",
      {
        UserName: userId, 
        Password: password
      }).pipe(map((response => {
        this.isValidAuth = response.IsSuccess;
        if(this.isValidAuth){
          this.setModel(response);
        }
        return response;
      })));
  }

  isAuthenticated(): boolean {
    return this.isValidAuth;
  }

  setModel(userModel: any) {
    this.userModel = userModel;
  }

  logout(){
    this.isValidAuth = false;
    this.router.navigateByUrl('/');
  }
}