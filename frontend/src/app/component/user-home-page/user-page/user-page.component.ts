import {Component, OnInit} from '@angular/core';
import {UserService} from '../../../service/user/user.service';
import {PostService} from '../../../service/post/post.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenService} from '../../../service/token/token.service';
import {User} from '../../../models/user';
import {Post} from '../../../models/post';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  private login: string;
  public userImg: string;
  public img: string;

  constructor(private userService: UserService,
              private postService: PostService,
              private activateRoute: ActivatedRoute,
              private tokenService: TokenService,
              private router: Router) {
    this.login = activateRoute.snapshot.params.login;
  }

  public userRole;
  public prototype;
  public user: User;
  public posts: Post[];
  public vision = false;

  public subscriptions: Subscription[] = [];

  ngOnInit(): void {
    this.getUserByLogin(this.login);
    this.userRole = this.tokenService.userDetails?.authorities[0]?.authority;
  }

  _UserPosts(): void {
    this.subscriptions.push(this.postService.getPostsByUserId(this.user.id).subscribe(response => {
      this.posts = response;
      this.posts.forEach(value => {
        if (value !== null){
          value.photo = 'data:image/png;base64,' + value.photo;
        }
      });
    }));
    this.vision = !this.vision;
  }

  public getUserByLogin(login: string): void {
    this.subscriptions.push(this.userService.getUserByLogin(login).subscribe(response => {
      this.user = response;
      if (this.user.photo === null){
        this.userImg = 'assets/images/person.png';
      }
      else {
        this.userImg = 'data:image/png;base64,' + this.user.photo;
      }
    }));
  }

  _deleteUser(): void{
    this.userService.deleteUser(this.user.id).subscribe(res => {
      this.router.navigate(['posts']);
    });
  }

}
