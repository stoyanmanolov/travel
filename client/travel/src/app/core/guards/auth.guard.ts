import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { map } from 'rxjs';

export const authGuard: CanActivateFn = (route) => {
  const router = inject(Router);
  const authService = inject(AuthService);

  const isNavigatingToAuthPath = ['login', 'register'].includes(
    route.routeConfig?.path || ''
  );

  return authService.user$.pipe(
    map((value) => {
      const isAuthenticated = Boolean(value);

      if (isAuthenticated && isNavigatingToAuthPath) {
        router.navigate(['']);
        return false;
      } else if (!isAuthenticated && !isNavigatingToAuthPath) {
        router.navigate(['/login']);
        return false;
      } else {
        return true;
      }
    })
  );
};
