import {Component, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {Post} from '../../models/post';
import {PostService} from '../../service/post/post.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  constructor(private postService: PostService,
              private router: Router) {}
  public posts: Post[];

  public subscriptions: Subscription[] = [];

  ngOnInit(): void {
    this.getPosts();
  }

  public getPosts(): void{
    this.subscriptions.push(this.postService.getPosts().subscribe(response => {
      this.posts = response.reverse();
      this.posts.forEach((value, index, array) => {
        value.photo = 'data:image/png;base64,' + value.photo;
      });
    }));
  }

  public getPostsByUserId(id: number): void{
    this.subscriptions.push(this.postService.getPostsByUserId(id).subscribe(response =>
    {this.posts = response.reverse();
    }));
  }

  _navigate(login: string): void {
    this.router.navigate(['home', login]);
  }
}
