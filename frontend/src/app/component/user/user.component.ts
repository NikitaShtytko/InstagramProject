import {Component, OnInit} from '@angular/core';
import {UserService} from '../../service/user/user.service';
import {User} from '../../moduls/user';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  constructor(private userService: UserService) {}
  public user: User[];

  public subscriptions: Subscription[] = [];

  ngOnInit(): void {
    this.getUsers();
  }

  public getUsers(): void{
    this.subscriptions.push(this.userService.getUsers().subscribe(response => {this.user = response; console.log(response); }));
  }


}
