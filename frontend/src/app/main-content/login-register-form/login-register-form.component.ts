import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "../../../shared/service/auth.service";
import {Router} from "@angular/router";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-login-register-form',
  templateUrl: './login-register-form.component.html',
  styleUrls: ['./login-register-form.component.scss']
})
export class LoginRegisterFormComponent implements OnInit, OnDestroy {

  username = ''
  password = ''
  loginValid = true
  isOnRegister = false
  registerButtonText = 'Don\'t have account yet? Register now.'

  private isAuthenticatedSub: Subscription | undefined;

  constructor(private authService: AuthService,
              private router: Router) {
  }

  ngOnDestroy(): void {
    this.isAuthenticatedSub?.unsubscribe();
  }

  ngOnInit(): void {
    this.isAuthenticatedSub = this.authService.isAuthenticated$.subscribe(value => {
      if (value) {
        this.router.navigate(['/homepage']).finally();
      }
    })
  }

  onSubmit(): void {
    if (this.isOnRegister) {
      this.authService.register(this.username, this.password).catch(() => {
        this.loginValid = false;
      });
    } else {
      this.authService.login(this.username, this.password).catch(() => {
        this.loginValid = false;
      });
    }
  }

  getHeader(): string {
    return this.isOnRegister ? 'Registration' : 'Log in';
  }

  getSubmitButtonText(): string {
    return this.isOnRegister ? 'Register' : 'Login';
  }

  changeFormMode(): void {
    if (this.isOnRegister) {
      this.registerButtonText = 'Don\'t have account yet? Register now.';
    } else {
      this.registerButtonText = 'Have an account already? Log in.';
    }

    this.isOnRegister = !this.isOnRegister;
  }

}
