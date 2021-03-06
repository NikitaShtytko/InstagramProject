import {Component, OnInit} from '@angular/core';
import {UserService} from '../../service/user/user.service';
import {User} from '../../models/user';
import {Subscription} from 'rxjs';
import {Post} from '../../models/post';
import {PostService} from '../../service/post/post.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenService} from '../../service/token/token.service';

@Component({
  selector: 'app-user-home-page',
  templateUrl: './user-home-page.component.html',
  styleUrls: ['./user-home-page.component.css']
})

export class UserHomePageComponent implements OnInit {

  private login: string;
  public userImg: string;
  public noPosts: boolean;

  constructor(private userService: UserService,
              private postService: PostService,
              private activateRoute: ActivatedRoute,
              private tokenService: TokenService,
              private router: Router) {}

  public details;
  public userRole;

  public prototype;
  public user: User;
  public post: Post;
  public posts: Post[];
  public vision = false;
  public selectedFile = true;
  selectedPhoto: File;

  info: FormGroup = new FormGroup(({
    firstName: new FormControl('', [
      Validators.required,
      Validators.pattern('^[a-zA-Zа-яА-Я\']{2,40}$')
    ]),
    lastName: new FormControl('', [
      Validators.required,
      Validators.pattern('^[a-zA-Zа-яА-Я\']{2,40}$')
    ]),
  }));

  public subscriptions: Subscription[] = [];

  ngOnInit(): void {
    this.getUserByLogin(this.tokenService.userDetails?.username);
    this.details = this.tokenService.userDetails;
    this.userRole = this.details?.authorities[0]?.authority;
  }

  onUserFileSelected(event) {
    this.selectedPhoto = event.target.files[0];
    if (this.selectedPhoto !== null){
      if (this.selectedPhoto.type === 'image/jpeg' || this.selectedPhoto.type === 'image/png') {
        this.selectedFile = true;
      }
      else {
        this.selectedFile = false;
      }
    }
  }

  _UserPosts(): void {
    if (!this.vision){
      this.subscriptions.push(this.postService.getPostsByUserId(this.user.id).subscribe(response => {
        this.posts = response;
        if (this.posts.length === 0){
          this.noPosts = true;
        }
        else{
          this.posts.forEach(value => {
            if (value !== null){
              value.photo = 'data:image/png;base64,' + value.photo;
            }
          });
          this.vision = !this.vision;
        }
      }));
    }
    else {
      this.vision = !this.vision;
    }
  }

  _modalReset(){
    this.info.reset();
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

  public getPostsByUserId(id: number): void {
    this.subscriptions.push(this.postService.getPostsByUserId(id).subscribe(response => {
      this.posts = response;
    }));
  }

  _editSave() {
    const providerData = new FormData();
    if (this.selectedPhoto != null) {
          providerData.append('photo', this.selectedPhoto);
        }

    this.user.firstName = this.info.controls.firstName.value;
    this.user.lastName = this.info.controls.lastName.value;

    providerData.append('user', JSON.stringify(this.user));

    this.subscriptions.push(this.userService.updateInfo(providerData).subscribe(response => {
      this.user = response;
      this.userImg = 'data:image/png;base64,' + response.photo;
      this.info.reset();
    }));
  }

  _defaultValue() {
    this.info.controls.firstName.setValue(this.user.firstName);
    this.info.controls.lastName.setValue(this.user.lastName);
  }

  _deleteUser(): void{
    this.userService.deleteUser(this.user.id).subscribe(res => {
      this.tokenService.logOut();
      this.router.navigate(['']);
    });
  }

  _navigate(id: number): void {
    this.router.navigate(['post', id]);
  }
}
