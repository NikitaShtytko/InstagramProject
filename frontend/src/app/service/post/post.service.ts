import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Post} from '../../models/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private httpClient: HttpClient) { }

  getPosts(): Observable<Post[]> {
    return this.httpClient.get<Post[]>('/api/posts');
  }

  getPostsByUserId(id): Observable<Post[]> {
    return this.httpClient.get<Post[]>('/api/posts/user/' + id);
  }

  getPostById(id: number) {
    return this.httpClient.get<Post>('/api/posts/' + id);
  }

  savePost(post: Post) {
    return this.httpClient.post<Post>('/api/posts', post);
  }

  createPost(formData: FormData): Observable<any> {
    return this.httpClient.post('/api/posts', formData);
  }

  updatePost(formData: FormData): Observable<any> {
    return this.httpClient.put('/api/posts', formData);
  }

  delete(id: number): Observable<any> {
    return this.httpClient.delete('/api/posts/' + id);
  }
}
