import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { BehaviorSubject, distinctUntilChanged, map, tap } from 'rxjs';
import { User, UserRole } from '../models/User';
import { TokenService } from './token.service';
import { SnackBarService } from './snack-bar.service';
import { SnackBarType } from '../models/SnackBarType';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private userSubject = new BehaviorSubject<User | undefined>(undefined);
  public user$ = this.userSubject.asObservable().pipe(distinctUntilChanged());
  public isAuthenticated$ = this.userSubject
    .asObservable()
    .pipe(map((v) => Boolean(v)))
    .pipe(distinctUntilChanged());

  constructor(
    private apiService: ApiService,
    private tokenService: TokenService,
    private snackBarService: SnackBarService,
    private router: Router
  ) {
    if (this.tokenService.isTokenExpired()) {
      this.router.navigate(['/auth/login']);
    } else {
      const username = this.tokenService.getTokenSubject();

      if (username) {
        this.apiService.getAuthenticatedUser().subscribe(
          (data) => {
            this.userSubject.next({
              username: data.username,
              userRole: data.userRole,
            });
          },
          () => this.clearUser()
        );
      }
    }
  }

  register(username: string, password: string) {
    return this.apiService.register(username, password).pipe(
      tap({
        next: (data) =>
          this.updateUser(data.username, data.userRole, data.token),
        error: () => this.clearUser(),
      })
    );
  }

  login(username: string, password: string) {
    return this.apiService.login(username, password).pipe(
      tap({
        next: (data) =>
          this.updateUser(data.username, data.userRole, data.token),
        error: () => this.clearUser(),
      })
    );
  }

  logout() {
    this.clearUser();
  }

  updateUser(username: string, userRole: UserRole, token: string) {
    this.tokenService.setToken(token);
    this.userSubject.next({ username, userRole });
    this.snackBarService.open(SnackBarType.SUCCESS, 'Successfully logged in.');
    this.router.navigate(['/']);
  }

  clearUser() {
    this.tokenService.removeToken();
    this.userSubject.next(undefined);
  }
}
