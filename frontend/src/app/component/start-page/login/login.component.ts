import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {TokenService} from '../../../service/token/token.service';
import {AuthToken, SecurityConstants} from '../../../models/AuthToken';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  error: string;

  form: FormGroup = new FormGroup({
    login: new FormControl('', [
      Validators.required,
      Validators.pattern('^[a-zA-Zа-яА-Я\'_0-9]{4,40}$')
    ]),

    password: new FormControl('', [
      Validators.required,
      Validators.pattern('^[a-zA-Zа-яА-Я\'_0-9]{4,40}$')
    ]),
  });

  constructor(private tokenService: TokenService, private router: Router){}

  ngOnInit(): void {
  }

  signIn() {
    const user = {
      login:  this.form.controls.login.value,
      password: this.form.controls.password.value,
    };
    this.tokenService.generateToken(user).subscribe((res: AuthToken) => {
      localStorage.setItem(SecurityConstants.AUTHORIZATION, res.value);
      this.tokenService.getUserDetails().subscribe(() => {
        this.router.navigate(['/home/']);
      });
    }, e => {
      this.error = 'Incorrect Login Or Password';
    });
  }
}
