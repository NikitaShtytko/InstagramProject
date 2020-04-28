import {Component, OnInit} from '@angular/core';
import {UserService} from '../../service/user/user.service';
import {User} from '../../moduls/user';
import {Subscription} from 'rxjs';
import {Post} from '../../moduls/post';
import {PostService} from '../../service/post/post.service';
import {FormControl, FormGroup} from '@angular/forms';


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

  ngOnInit(): void {
    // this.getUserByLogin('VreDina');
    this.subscriptions.push(this.userService.getUserByLogin('VreDina').subscribe(response => {
      this.user = response;
      console.log(response);
      console.log(' dasdas ' + this.user?.login);
      this.getPostsByUserId(this.user.id);
    }));
  }

  _UserPosts(): void {
    // this.getPostsByUserId(this.user.id);
    this.vision = !this.vision;
    console.log(this.vision);
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
      console.log(response);
    }));
  }

  onFileSelected(event) {
    this.selectedPhoto = event.target.files[0];
  }

  _onSave(){
    const post = new Post();
    post.txt = this.form.controls.description.value;
    post.place = this.form.controls.place.value;
    post.user = this.user;

    const providerData = new FormData();

    providerData.append('photo', this.selectedPhoto);

    this.savePost(post);
    this.getPostsByUserId(this.user.id);
  }


  private savePost(post: Post) {
    this.subscriptions.push(this.postService.savePost(post).subscribe(response => {this.post = response; console.log(response); }));
  }
}
