import {Component, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {PostService} from '../../../service/post/post.service';
import {Post} from '../../../models/post';
import {ActivatedRoute, Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
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
  public details;
  public user: User;
  public userRole;
  public comment: Comments;
  public subscriptions: Subscription[] = [];
  public vision = false;
  public globalVision = false;
  public deleteButton = false;
  public nullValue = false;

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
    txt: new FormControl('', [
      Validators.required,
      Validators.pattern('^[a-zA-Zа-яА-Я0-9_ \']{1,50}$')
    ]),
  });

  ngOnInit(): void {
    this.subscriptions.push(this.postService.getPostById(this.id).subscribe(response =>
    {this.post = response;
     this.post.comment.reverse();
     this.tokenService.getUserDetails().subscribe((res: any) => {
        this.subscriptions.push(this.userService.getUserByLogin(res.username).subscribe(result => {
          this.user = result;
          this.details = this.tokenService.userDetails;
          this.userRole = this.details?.authorities[0]?.authority;
          if (this.user.login === this.post.user.login || this.userRole === 'ROLE_ADMIN'){
            this.deleteButton = !this.deleteButton;
          }
          console.log(this.user);
          this.globalVision = !this.globalVision;
        }));
      });
    }));
  }

  public getPostById(id: number): void{
    this.subscriptions.push(this.postService.getPostById(id).subscribe(response =>
    {this.post = response;
     this.post.comment.reverse();
     console.log(this.post.user);
    }));
  }

  _postComments(): void {
    this.vision = !this.vision;
  }

  _modalReset(){
    this.form.reset();
  }

  _commentSave(){
    const comment = new Comments();
    comment.txt = this.form.controls.txt.value;
    if (comment.txt === '' || comment.txt === null){
      this.nullValue = !this.nullValue;
      console.log('null');
    }
    else {
    comment.user = this.user;
    this.post.comment = null;
    comment.post = this.post;

    this.subscriptions.push(this.commentService.saveComment(comment).subscribe(response => {
        this.comment = response;
        this.form.reset();
        this._modalReset();
        this.getPostById(this.id);
      }));
    }
  }

  _deletePost() {
    this.postService.delete(this.post.id).subscribe(response => {
      this.router.navigateByUrl('/posts');
    });
  }

  get txt() {
    return this.form.get('txt');
  }
}
