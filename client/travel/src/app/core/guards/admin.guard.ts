import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { map } from 'rxjs';
import { UserRole } from '../models/User';

export const adminGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const authService = inject(AuthService);

  return authService.user$.pipe(
    map((user) => {
      const isAdmin = user?.userRole === UserRole.ADMIN;

      if (!isAdmin) {
        router.navigate(['']);
        return false;
      } else {
        return true;
      }
    })
  );
};
