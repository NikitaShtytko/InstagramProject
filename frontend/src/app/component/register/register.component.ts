import {Component, OnInit} from '@angular/core';
import {User} from '../../moduls/user';
import {UserService} from '../../service/user/user.service';
import {Subscription} from 'rxjs';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private userService: UserService,
              private router: Router) {}
  public subscriptions: Subscription[] = [];

  public user: User = new User();

  ngOnInit(): void {
    this.saveUser(this.user);
  }

  public saveUser(user: User): void{
    this.subscriptions.push(this.userService.saveUser(user).subscribe(response => {this.user = response; console.log(response); }));
    console.log(user);
  }

  public gender(name: string): void{
    this.user.gender = name;
  }
}
