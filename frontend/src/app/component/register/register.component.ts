import {Component, OnInit} from '@angular/core';
import {User} from '../../moduls/user';
import {UserService} from '../../service/user/user.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private userService: UserService) {}
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

  public default(role: string, status: string){
    this.user.role = role;
    this.user.status = status;
  }


}
