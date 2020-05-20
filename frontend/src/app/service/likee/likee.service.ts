import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Like} from '../../models/like';

@Injectable({
  providedIn: 'root'
})
export class LikeeService {

  constructor(private httpClient: HttpClient) {}

  getLikes(): Observable<Like[]> {
    return this.httpClient.get<Like[]>('/api/likes');
  }
}
