import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {

  selectedPhoto: File;

  constructor() { }

  form: FormGroup = new FormGroup({
    description: new FormControl('', []),
    place: new FormControl('', [])
  });

  ngOnInit(): void {
  }

  onFileSelected(event) {
    this.selectedPhoto = event.target.files[0];
  }

  _modalReset(){
    this.form.reset();
  }
}
