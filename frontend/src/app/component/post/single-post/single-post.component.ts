import {Component, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {PostService} from '../../../service/post/post.service';
import {Post} from '../../../moduls/post';
import {ActivatedRoute, Router} from '@angular/router';
import {FormControl, FormGroup} from '@angular/forms';
import {CommentsService} from '../../../service/comments/comments.service';
import {Comments} from '../../../moduls/comments';

@Component({
  selector: 'app-single-post',
  templateUrl: './single-post.component.html',
  styleUrls: ['./single-post.component.css']
})
export class SinglePostComponent implements OnInit {

  public post: Post;
  public comment: Comments;
  public subscriptions: Subscription[] = [];
  public vision = false;

  id: number;

  constructor(private postService: PostService, private commentService: CommentsService,
              private activateRoute: ActivatedRoute, private router: Router) {
    this.id = activateRoute.snapshot.params.id;
  }

  form: FormGroup = new FormGroup({
    field: new FormControl('', []),
  });

  ngOnInit(): void {
    this.getPostById(this.id);
  }

  public getPostById(id: number): void{
    this.subscriptions.push(this.postService.getPostById(id).subscribe(response => {this.post = response; }));
  }

  _postComments(): void {
    this.vision = !this.vision;
  }

  _modalReset(){
    this.form.reset();
  }

  _commentSave(){
    // const postData = new FormData();
    const comment = new Comments();
    //
    // comment.txt = this.form.controls.field.value;
    // console.log(this.post);
    // comment.user = this.post.user;
    // console.log(this.post);
    // this.post.comment.push(comment);
    // TODO через токен
    // postData.append('photo', this.post.photo);
    // postData.append('post', JSON.stringify(this.post));

    comment.txt = this.form.controls.field.value;
    comment.user = this.post.user;
    this.post.comment = null;
    comment.post = this.post;

    this.subscriptions.push(this.commentService.saveComment(comment).subscribe(response =>
    {this.comment = response;
     this.form.reset();
     this.getPostById(this.id);
    }));
  }

  _deletePost() {
    this.postService.delete(this.post.id).subscribe(response => {
      this.router.navigateByUrl('/posts');
    });
  }
}
