import {Component, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {PostService} from '../../../service/post/post.service';
import {Post} from '../../../models/post';
import {ActivatedRoute, Router} from '@angular/router';
import {FormControl, FormGroup} from '@angular/forms';
import {CommentsService} from '../../../service/comments/comments.service';
import {Comments} from '../../../models/comments';
import {TokenService} from '../../../service/token/token.service';
import {User} from '../../../models/user';
import {UserService} from '../../../service/user/user.service';

@Component({
  selector: 'app-single-post',
  templateUrl: './single-post.component.html',
  styleUrls: ['./single-post.component.css']
})
export class SinglePostComponent implements OnInit {

  public post: Post;
  public user: User;
  public comment: Comments;
  public subscriptions: Subscription[] = [];
  public vision = false;
  public globalVision = false;
  public deleteButton = false;

  id: number;

  constructor(private postService: PostService,
              private commentService: CommentsService,
              private activateRoute: ActivatedRoute,
              private router: Router,
              private tokenService: TokenService,
              private userService: UserService) {
    this.id = activateRoute.snapshot.params.id;
  }

  form: FormGroup = new FormGroup({
    field: new FormControl('', []),
  });

  ngOnInit(): void {
    this.getPostById(this.id);
    this.tokenService.getUserDetails().subscribe((res: any) => {
      this.subscriptions.push(this.userService.getUserByLogin(res.username).subscribe(response => {
        this.user = response;
        console.log(this.user);
        if (this.user.login === this.post.user.login){
          this.deleteButton = !this.deleteButton;
        }
        console.log(this.user);
        this.globalVision = !this.globalVision;
      }));
    });
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

    comment.txt = this.form.controls.field.value;
    comment.user = this.user;
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
