import {Component, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {Tag} from '../../../models/tag';
import {TagService} from '../../../service/tag/tag.service';

@Component({
  selector: 'app-tag',
  templateUrl: './tag.component.html',
  styleUrls: ['./tag.component.css']
})
export class TagComponent implements OnInit {

  constructor(private tagService: TagService) {}
  public tag: Tag[];

  public subscriptions: Subscription[] = [];

  ngOnInit(): void {
    this.getTags();
  }

  public getTags(): void{
    this.subscriptions.push(this.tagService.getTags().subscribe(response => {this.tag = response; }));
  }

}
