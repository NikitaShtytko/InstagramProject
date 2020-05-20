import {Component, OnInit} from '@angular/core';
import {UserService} from '../../service/user/user.service';
import {User} from '../../models/user';
import {Subscription} from 'rxjs';
import {Post} from '../../models/post';
import {PostService} from '../../service/post/post.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {TokenService} from '../../service/token/token.service';

@Component({
  selector: 'app-user-home-page',
  templateUrl: './user-home-page.component.html',
  styleUrls: ['./user-home-page.component.css']
})

export class UserHomePageComponent implements OnInit {

  login: string;

  constructor(private userService: UserService, private postService: PostService,
              private activateRoute: ActivatedRoute, private tokenService: TokenService) {
    this.login = activateRoute.snapshot.params.login;
  }

  public prototype;
  public user: User;
  public post: Post;
  public posts: Post[];
  public vision = false;
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
  private myImage: any;

  ngOnInit(): void {
    console.log('user-home-start');
    this.getUserByLogin(this.tokenService.userDetails?.username);
    console.log('user-home-end');
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

  _modalReset(){
    this.info.reset();
  }

  public getUserByLogin(login: string): void {
    this.subscriptions.push(this.userService.getUserByLogin(login).subscribe(response => {
      this.user = response;
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
    // this.user.role.toString().toUpperCase();
    this.user.firstName = this.info.controls.firstName.value;
    this.user.lastName = this.info.controls.lastName.value;

    providerData.append('user', JSON.stringify(this.user));

    this.subscriptions.push(this.userService.updateInfo(providerData).subscribe(response => {
      this.user = response;
      this.info.reset();
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
