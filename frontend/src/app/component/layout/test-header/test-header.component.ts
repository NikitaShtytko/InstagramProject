import {Component, OnInit} from '@angular/core';
import {Post} from '../../../moduls/post';
import {Subscription} from 'rxjs';
import {FormControl, FormGroup} from '@angular/forms';
import {PostService} from '../../../service/post/post.service';

@Component({
  selector: 'app-test-header',
  templateUrl: './test-header.component.html',
  styleUrls: ['./test-header.component.css']
})
export class TestHeaderComponent implements OnInit {

  public post: Post;
  public subscriptions: Subscription[] = [];
  selectedPhoto: File;

  form: FormGroup = new FormGroup({

    description: new FormControl('', []),
    place: new FormControl('', [])

  });

  constructor(private postService: PostService) {
  }

  ngOnInit(): void {
  }

  _onSave(){
    const post = new Post();
    post.txt = this.form.controls.description.value;
    post.place = this.form.controls.place.value;

    // post.user = this.user;

    const providerData = new FormData();

    providerData.append('photo', this.selectedPhoto);

    this.savePost(post);
    this.form.reset();
  }



  private savePost(post: Post) {
    this.subscriptions.push(this.postService.savePost(post).subscribe(response => {this.post = response; console.log(response); }));
  }

  _modalReset(){
    this.form.reset();
  }

}
