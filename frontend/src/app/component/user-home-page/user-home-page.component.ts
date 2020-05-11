import {Component, OnInit} from '@angular/core';
import {UserService} from '../../service/user/user.service';
import {User} from '../../moduls/user';
import {Subscription} from 'rxjs';
import {Post} from '../../moduls/post';
import {PostService} from '../../service/post/post.service';
import {FormControl, FormGroup} from '@angular/forms';

const currentTimeZoneOffsetInHours = new Date().getTimezoneOffset() / 60;

@Component({
  selector: 'app-user-home-page',
  templateUrl: './user-home-page.component.html',
  styleUrls: ['./user-home-page.component.css']
})

export class UserHomePageComponent implements OnInit {

  constructor(private userService: UserService, private postService: PostService) {
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

  public subscriptions: Subscription[] = [];
  private myImage: any;

  ngOnInit(): void {
    // this.getUserByLogin('VreDina');
    this.subscriptions.push(this.userService.getUserByLogin('VreDina').subscribe(response => {
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

  _onSave(){
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

  // converter(photo: string){
  //   console.log(photo);
  //   if (photo !== ''){
  //   this.myImage.src = URL.createObjectURL(photo);
  //   photo = this.myImage;
  //   console.log(photo);
  //   }
  //   }
}
