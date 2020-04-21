import {Component, OnInit} from '@angular/core';
import {UserService} from '../../service/user/user.service';
import {User} from '../../moduls/user';
import {Subscription} from 'rxjs';
import {Post} from "../../moduls/post";
import {PostService} from "../../service/post/post.service";

@Component({
  selector: 'app-user-home-page',
  templateUrl: './user-home-page.component.html',
  styleUrls: ['./user-home-page.component.css']
})
export class UserHomePageComponent implements OnInit {

  constructor(private userService: UserService, private postService: PostService) {}
  public user: User;
  public post: Post[];

  public subscriptions: Subscription[] = [];

  ngOnInit(): void {
    this.getUserByLogin('VreDina');
    this.getPosts();
  }

  public getUserByLogin(login: string): void{
    this.subscriptions.push(this.userService.getUserByLogin(login).subscribe(response => {this.user = response; console.log(response); }));
  }

  public getPosts(): void{
    this.subscriptions.push(this.postService.getPosts().subscribe(response => {this.post = response; console.log(response); }));
  }
}
