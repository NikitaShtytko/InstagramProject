import {Component, OnInit} from '@angular/core';
import {UserService} from '../../service/user/user.service';
import {User} from '../../moduls/user';
import {Subscription} from 'rxjs';
import {Post} from '../../moduls/post';
import {PostService} from '../../service/post/post.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';

const currentTimeZoneOffsetInHours = new Date().getTimezoneOffset() / 60;

@Component({
  selector: 'app-user-home-page',
  templateUrl: './user-home-page.component.html',
  styleUrls: ['./user-home-page.component.css']
})

export class UserHomePageComponent implements OnInit {

  login: string;

  constructor(private userService: UserService, private postService: PostService,
              private activateRoute: ActivatedRoute) {
    this.login = activateRoute.snapshot.params.login;
  }

  public user: User;
  public post: Post;
  public posts: Post[];
  public vision = false;
  selectedPhoto: File;

  form: FormGroup = new FormGroup({
    description: new FormControl('', []),
    place: new FormControl('', [])
  });

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
  private myImage: any;

  ngOnInit(): void {
    // this.getUserByLogin('VreDina');
    // this.subscriptions.push(this.userService.getUserByLogin('VreDina').subscribe(response => {
    this.subscriptions.push(this.userService.getUserByLogin(this.login).subscribe(response => {
      this.user = response;
      console.log(response);
      this.subscriptions.push(this.postService.getPostsByUserId(this.user.id).subscribe(result => {
        this.posts = result;
      }));
    }));
  }

  onFileSelected(event) {
    this.selectedPhoto = event.target.files[0];
  }

  _UserPosts(): void {
    this.subscriptions.push(this.postService.getPostsByUserId(this.user.id).subscribe(response => {
      this.posts = response;
    }));
    this.vision = !this.vision;
  }

  _postSave(){
    const postData = new FormData();
    const post = new Post();

    post.txt = this.form.controls.description.value;
    post.place = this.form.controls.place.value;
    post.user = this.user;

    postData.append('photo', this.selectedPhoto);
    postData.append('post', JSON.stringify(post));

    console.log(postData);
    console.log(post);
    // this.savePost(post);
    this.postService.createPost(postData).subscribe(res => console.log(res));

    this.form.reset();
    this.getPostsByUserId(this.user.id);
  }

  private savePost(post: Post) {
    this.subscriptions.push(this.postService.savePost(post).subscribe(response => {this.post = response; console.log(response); }));
    this.getPostsByUserId(this.user.id);
  }

  _modalReset(){
    this.form.reset();
    this.info.reset();
  }

  public getUserByLogin(login: string): void {
    this.subscriptions.push(this.userService.getUserByLogin(login).subscribe(response => {
      this.user = response;
      console.log(response);
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
    console.log(this.user);

    providerData.append('user', JSON.stringify(this.user));

    this.subscriptions.push(this.userService.updateInfo(providerData).subscribe(response => {
      this.user = response;
      this.form.reset();
    }));
  }

  // updateProvider() {
  //   const providerData = new FormData();
  //   if (this.selectedPhoto != null) {
  //     providerData.append("photo", this.selectedPhoto);
  //   }
  //
  //   this.provider.name = this.providerForm.get('name').value;
  //   this.provider.price = this.providerForm.get('price').value;
  //   this.provider.description = this.providerForm.get('description').value;
  //
  //   providerData.append("provider", JSON.stringify(this.provider));
  //
  //   this.providerService.updateProvider(providerData).subscribe(res => this.mdbModalRef.hide());
  // }
  _defaultValue() {
    this.info.controls.firstName.setValue(this.user.firstName);
    this.info.controls.lastName.setValue(this.user.lastName);
  }
}
