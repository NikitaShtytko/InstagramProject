import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Likee} from '../../moduls/likee';

@Injectable({
  providedIn: 'root'
})
export class LikeeService {

  constructor(private httpClient: HttpClient) {}

  getLikes(): Observable<Likee[]> {
    return this.httpClient.get<Likee[]>('/api/likes');
  }
}
