import {Component, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {Post} from '../../models/post';
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
  }

  public getPosts(): void{
    this.subscriptions.push(this.postService.getPosts().subscribe(response => {this.posts = response; }));
  }

  public getPostsByUserId(id: number): void{
    this.subscriptions.push(this.postService.getPostsByUserId(id).subscribe(response =>
    {this.posts = response; }));
  }
}
