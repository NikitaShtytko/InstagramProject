import {Component, OnInit} from '@angular/core';
import {Comments} from '../../moduls/comments';
import {CommentsService} from '../../service/comments/comments.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  constructor(private commentsService: CommentsService) { }

  public comments: Comments[];

  public subscriptions: Subscription[] = [];



  ngOnInit(): void {
    this.getComments();
  }

  public getComments(): void{
    this.subscriptions.push(this.commentsService.getComments().subscribe(response => {this.comments = response; console.log(response); }));
  }

}
