import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {TokenService} from '../token/token.service';

@Injectable({providedIn: 'root'})
export class CanActivateService implements CanActivate {

  constructor(public router: Router,
              public auth: TokenService) {
  }

  canActivate(): boolean {
    if (!this.auth.isAuthenticated()) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}
