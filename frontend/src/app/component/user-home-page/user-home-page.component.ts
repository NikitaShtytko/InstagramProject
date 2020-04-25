import {Component, OnInit} from '@angular/core';
import {UserService} from '../../service/user/user.service';
import {User} from '../../moduls/user';
import {Subscription} from 'rxjs';
import {Post} from '../../moduls/post';
import {PostService} from '../../service/post/post.service';

@Component({
  selector: 'app-user-home-page',
  templateUrl: './user-home-page.component.html',
  styleUrls: ['./user-home-page.component.css']
})
export class UserHomePageComponent implements OnInit {

  constructor(private userService: UserService, private postService: PostService) {}
  public user: User;
  public posts: Post[];

  public subscriptions: Subscription[] = [];

  ngOnInit(): void {
    this.getUserByLogin('VreDina');
    console.log(this.user?.login);
  }

  _UerPosts(): void {
    this.getPostsByUserId(this.user.id);
  }

  public getUserByLogin(login: string): void{
    this.subscriptions.push(this.userService.getUserByLogin(login).subscribe(response => {this.user = response; console.log(response); }));
  }

  public getPostsByUserId(id: number): void{
    this.subscriptions.push(this.postService.getPostsByUserId(id).subscribe(response => {this.posts = response; console.log(response); }));
  }
}
