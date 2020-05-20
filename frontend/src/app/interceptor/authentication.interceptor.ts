import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SecurityConstants} from '../models/AuthToken';

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {

  constructor() {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token = localStorage.getItem(SecurityConstants.AUTHORIZATION);
    if (token) {
      const authReq = request.clone({
        headers: request.headers.set(SecurityConstants.AUTHORIZATION, 'Bearer' + token)
      });
      return next.handle(authReq);
    }
    return next.handle(request);
  }
}
