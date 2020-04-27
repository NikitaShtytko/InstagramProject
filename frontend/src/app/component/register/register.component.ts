import {Component, OnInit} from '@angular/core';
import {User} from '../../moduls/user';
import {UserService} from '../../service/user/user.service';
import {Observable, Subscription} from 'rxjs';
import {Router} from '@angular/router';
import {AbstractControl, FormControl, FormGroup, ValidationErrors, Validators} from '@angular/forms';
import {tap} from 'rxjs/operators';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: FormGroup = new FormGroup({

    firstName: new FormControl('', [
      Validators.required,
      Validators.pattern('^[a-zA-Zа-яА-Я\']{2,40}$')
    ]),

    lastName: new FormControl('', [
      Validators.required,
      Validators.pattern('^[a-zA-Zа-яА-Я\']{2,40}$')
    ]),

    email: new FormControl('', [
      Validators.required,
      Validators.pattern('[a-zA-Z0-9_]+@[a-zA-Z_]+?\.[a-zA-Z]{2,4}')
      ]),

    login: new FormControl('', [
      Validators.required,
      Validators.pattern('^[a-zA-Zа-яА-Я\'_0-9]{4,40}$'
      )], [
      this.loginValidator.bind(this)
    ]),

    password: new FormControl('', [
      Validators.required,
      Validators.pattern('^[a-zA-Zа-яА-Я\'_0-9]{4,40}$')
    ]),

    confirmPassword: new FormControl('', [
      Validators.required,
      Validators.pattern('^[a-zA-Zа-яА-Я\'_0-9]{4,40}$'),
    ])
  });


  constructor(private userService: UserService,
              private router: Router) {}

  public subscriptions: Subscription[] = [];

  public user: User = new User();

  ngOnInit(): void {

    this.confirmPass();
    // this.saveUser(this.user);
  }

  register() {
    const user = new User();
    user.login = this.form.controls.login.value;
    user.firstName = this.form.controls.firstName.value;
    user.lastName = this.form.controls.lastName.value;
    user.password = this.form.controls.password.value;
    user.email = this.form.controls.email.value;

    console.log('register');

    this.saveUser(user);
    // this.router.navigate(['/user-home-page'], {});
  }

  public saveUser(user: User): void{
    this.subscriptions.push(this.userService.saveUser(user).subscribe(response => {this.user = response; console.log(response); }));
    console.log(user);
  }

  private loginValidator(control: AbstractControl): Observable<ValidationErrors> {
    return new Observable<ValidationErrors>(observer => {
      control.markAsPending();
      this.userService.getUserByLogin(control.value).pipe(
        tap(res => {
          console.log(res);
          if (res.login != null) {
            control.markAsPending({onlySelf: false});
            control.setErrors({notUnique: true});
          } else {
            control.markAsPending({onlySelf: false});
            this.login.setErrors(null);
          }
        })
      ).subscribe();
    });
  }

  private confirmPass() {
    this.form.controls.confirmPassword.valueChanges.subscribe(confPass => {
      const pass = this.form.controls.password;
      if (pass.value === confPass) {
        this.form.controls.confirmPassword.setErrors(null);
      } else {
        this.form.controls.confirmPassword.setErrors({isConfirm: false});
      }
    });
    this.form.controls.password.valueChanges.subscribe(confPass => {
      const pass = this.form.controls.confirmPassword;
      if (pass.value === confPass) {
        this.form.controls.confirmPassword.setErrors(null);
      } else {
        this.form.controls.confirmPassword.setErrors({isConfirm: false});
      }
    });
  }

  get login() {
    return this.form.get('login');
  }

  public gender(name: string): void{
    this.user.gender = name;
  }


}
