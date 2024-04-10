import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { SnackBarService } from '../services/snack-bar.service';
import { SnackBarType } from '../models/SnackBarType';

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {
  constructor(private snackBarService: SnackBarService) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      catchError((err: HttpErrorResponse) => {
        if (typeof err.error === 'string') {
          this.snackBarService.open(SnackBarType.ERROR, err.error);
        }

        return throwError(() => err);
      })
    );
  }
}
