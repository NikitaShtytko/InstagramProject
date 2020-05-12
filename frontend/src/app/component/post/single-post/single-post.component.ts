import {Component, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {PostService} from '../../../service/post/post.service';
import {Post} from '../../../moduls/post';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-single-post',
  templateUrl: './single-post.component.html',
  styleUrls: ['./single-post.component.css']
})
export class SinglePostComponent implements OnInit {

  public post: Post;
  public subscriptions: Subscription[] = [];
  public vision = false;

  id: number;

  constructor(private postService: PostService, private activateRoute: ActivatedRoute) {
    this.id = activateRoute.snapshot.params.id;
  }

  ngOnInit(): void {
    console.log(this.id);
    this.getPostById(this.id);
  }

  public getPostById(id: number): void{
    this.subscriptions.push(this.postService.getPostById(id).subscribe(response => {this.post = response; console.log(response); }));
  }

  _postComments(): void {
    this.vision = !this.vision;
    console.log(this.vision);
  }
}
