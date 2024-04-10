import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
import moment from 'moment';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  constructor() {}

  getTokenSubject() {
    const decodedToken = jwtDecode(this.getToken() || '');

    if (decodedToken && decodedToken.sub) {
      return decodedToken.sub;
    }

    return '';
  }

  getToken() {
    return window.localStorage.getItem('token');
  }

  setToken(token: string) {
    window.localStorage.setItem('token', token);
  }

  removeToken() {
    window.localStorage.removeItem('token');
  }

  isTokenExpired() {
    const token = this.getToken();

    if (token) {
      const decodedToken = jwtDecode(token);

      if (decodedToken.exp) {
        const expired = moment(decodedToken.exp * 1000).isBefore(Date.now());

        return expired;
      }
    }

    return true;
  }
}
