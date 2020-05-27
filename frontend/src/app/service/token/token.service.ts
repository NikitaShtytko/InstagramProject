import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {throwError} from 'rxjs';
import {SecurityConstants} from '../../models/AuthToken';
import {User} from '../../models/user';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  private tokenUrl = '/api/token';
  public userDetails;

  constructor(private http: HttpClient) {
  }

  generateToken(userLogin: { login: string, password: string }) {
    return this.http.post(this.tokenUrl + '/generate-token', userLogin);
  }

  getToken(): string {
    return localStorage.getItem(SecurityConstants.AUTHORIZATION);
  }

  public isAuthenticated(): boolean {
    const token = localStorage.getItem(SecurityConstants.AUTHORIZATION);
    return token != null;
  }

  getUserDetails(){
    return this.http.get(this.tokenUrl + '/details').pipe(
      tap((resUser: User) => {
        if (resUser.id) {
          this.userDetails = resUser;
        }
      }),
      catchError(err => {
        return throwError('User Not Found Error'); })
    );
  }

  // getUserDetails() {
  //   return this.http.get(this.tokenUrl + '/details').subscribe(res => {
  //     this.userDetails = res;
  //   }, error => error);
  // }

  logOut() {
    localStorage.removeItem(SecurityConstants.AUTHORIZATION);
    this.userDetails = null;
  }

  loadConfig() {
    return this.http.get(this.tokenUrl + '/details').toPromise().then(res => {
      this.userDetails = res;
    }).catch(error => {
    });
  }
}
