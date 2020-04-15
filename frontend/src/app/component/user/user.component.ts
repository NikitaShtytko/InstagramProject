import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user/user.service";
import {User} from "../../models/user";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  ngOnInit(): void {
    this.getUsers();
  }

  constructor(private userService: UserService) {}
  public user: User[];

  public subscriptions: Subscription[] = [];

  public getUsers(): void{
    this.subscriptions.push(this.userService.getUsers().subscribe(response => {this.user = response; console.log(response)}))
  }
}
