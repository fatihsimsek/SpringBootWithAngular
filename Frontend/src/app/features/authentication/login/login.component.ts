import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthenticationService } from 'src/app/core/services/authentication.service';

@Component({
  selector: 'app-authentication-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  model: any = {};

  public userName: string;
  public password: string;
  
  constructor(private authenticationService: AuthenticationService,
    private router: Router) {
  }

  ngOnInit() {
  }

  login() {
    this.authenticationService.login(
      this.model.email,
      this.model.password).subscribe(response => {
        this.router.navigate(['/dashboard']);
      });
  }
}
