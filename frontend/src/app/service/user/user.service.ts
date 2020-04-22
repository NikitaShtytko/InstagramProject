import {User} from '../../moduls/user';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  constructor(private httpClient: HttpClient) {
  }


  getUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>('/api/users');
  }

  getUserByLogin(login: string): Observable<User> {
    return this.httpClient.get<User>('/api/users/login/' + login);
  }

  getUserById(id: number): Observable<User> {
    return this.httpClient.get<User>('/api/users/' + id);
  }

  saveUser(user: User): Observable<User> {
    return this.httpClient.post<User>('/api/users', user);
  }

  updateUser(user: User): Observable<User> {
    return this.httpClient.put<User>('/api/users/', user);
  }

  deleteUser(id: number): Observable<User> {
    return this.httpClient.delete<User>('/api/users/' + id);
  }
}
