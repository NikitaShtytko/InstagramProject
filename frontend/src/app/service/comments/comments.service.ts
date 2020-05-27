import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Comments} from '../../models/comments';

@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  constructor(private httpClient: HttpClient) { }

  getComments(): Observable<Comments[]> {
    return this.httpClient.get<Comments[]>('/api/comments');
  }

  saveComment(comment: Comments) {
    return this.httpClient.post<Comments>('/api/comments/', comment);
  }

  getCommentsByPostId(id: number): Observable<Comments[]> {
  return this.httpClient.get<Comments[]>('/api/comments/post/' + id);
}

  delete(id: number) {
    return this.httpClient.delete('/api/comments/' + id);
  }
}
