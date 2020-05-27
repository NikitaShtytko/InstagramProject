import {Component, OnInit} from '@angular/core';
import {Post} from '../../../models/post';
import {Subscription} from 'rxjs';
import {FormControl, FormGroup} from '@angular/forms';
import {PostService} from '../../../service/post/post.service';
import {UserService} from '../../../service/user/user.service';
import {TokenService} from '../../../service/token/token.service';
import {User} from '../../../models/user';
import {Router} from '@angular/router';

@Component({
  selector: 'app-test-header',
  templateUrl: './test-header.component.html',
  styleUrls: ['./test-header.component.css']
})
export class TestHeaderComponent implements OnInit {

  public post: Post;
  public user: User;
  public subscriptions: Subscription[] = [];
  selectedPhoto: File;
  selectedFile = true;

  form: FormGroup = new FormGroup({

    description: new FormControl('', []),
    place: new FormControl('', [])

  });

  constructor(private userService: UserService,
              private postService: PostService,
              private tokenService: TokenService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getUserByLogin(this.tokenService?.userDetails?.username);
  }

  get userExist() {
    return this.tokenService?.userDetails;
  }

  logOut() {
    this.tokenService.logOut();
    this.router.navigate(['/login']);
  }

  public getUserByLogin(login: string): void {
    this.subscriptions.push(this.userService.getUserByLogin(login).subscribe(response => {
      this.user = response;
    }));
  }

  onFileSelected(event) {
    this.selectedPhoto = event.target.files[0];
    if (this.selectedPhoto !== null){
      if (this.selectedPhoto.type === 'image/jpeg' || this.selectedPhoto.type === 'image/png') {
        this.selectedFile = true;
        console.log('true');
      }
      else {
        this.selectedFile = false;
        console.log('false');
      }
    }
  }

  _onSave(){
    const postData = new FormData();
    const post = new Post();
    console.log('header ' + this.user);
    const user = this.user;

    post.txt = this.form.controls.description.value;
    post.place = this.form.controls.place.value;

    this.user.photo = null;
    post.user = this.user;
    this.user = user;

    postData.append('photo', this.selectedPhoto);
    postData.append('post', JSON.stringify(post));

    this.postService.createPost(postData).subscribe( res => {
      window.location.reload();
    });
  }

  _modalReset(){
    this.form.reset();
  }
}
