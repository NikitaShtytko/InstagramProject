import {Component, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {Post} from '../../moduls/post';
import {PostService} from '../../service/post/post.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  constructor(private postService: PostService) {}
  public posts: Post[];

  public subscriptions: Subscription[] = [];

  ngOnInit(): void {
    this.getPosts();
    // this.getPostsByUserId(2);
  }

  public getPosts(): void{
    this.subscriptions.push(this.postService.getPosts().subscribe(response => {this.posts = response; console.log(response); }));
  }

  public getPostsByUserId(id: number): void{
    this.subscriptions.push(this.postService.getPostsByUserId(id).subscribe(response => {this.posts = response; console.log(response); }));
  }
}
