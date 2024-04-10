import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { ResponsiveService } from '../../services/responsive.service';
import { UserRole } from '../../models/User';
import { DialogService } from '../../services/dialog.service';
import { TourSearchDialogComponent } from '../../../features/tour/components/tour-search-dialog/tour-search-dialog.component';

type NavItem = {
  label: string;
  action: () => void;
  icon: string;
  hidden?: Observable<boolean>;
};

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent {
  public navItems: NavItem[] = [
    {
      label: 'Home',
      action: () => this.router.navigate(['']),
      icon: 'home',
    },
    {
      label: 'Tours',
      action: () => this.router.navigate(['/tours/']),
      icon: 'local_airport',
      hidden: this.authService.isAuthenticated$.pipe(map((v) => !v)),
    },
    {
      label: 'Destinations',
      action: () => this.router.navigate(['/destinations/']),
      icon: 'public',
      hidden: this.authService.isAuthenticated$.pipe(map((v) => !v)),
    },
    {
      label: 'Blogs',
      action: () => this.router.navigate(['/blogs/']),
      icon: 'article',
      hidden: this.authService.isAuthenticated$.pipe(map((v) => !v)),
    },
    {
      label: 'Your bookings',
      action: () => this.router.navigate(['/bookings/user-bookings']),
      icon: 'card_travel',
      hidden: this.authService.isAuthenticated$.pipe(map((v) => !v)),
    },
    {
      label: 'All bookings',
      action: () => this.router.navigate(['/bookings/all']),
      icon: 'card_travel',
      hidden: this.authService.user$.pipe(
        map((v) => v?.userRole !== UserRole.ADMIN)
      ),
    },
    {
      label: 'Login',
      action: () => this.router.navigate(['/auth/login']),
      icon: 'login',
      hidden: this.authService.isAuthenticated$,
    },
    {
      label: 'Logout',
      action: () => this.authService.logout(),
      icon: 'logout',
      hidden: this.authService.isAuthenticated$.pipe(map((v) => !v)),
    },
    {
      label: 'Register',
      action: () => this.router.navigate(['/auth/register']),
      icon: 'exit_to_app',
      hidden: this.authService.isAuthenticated$,
    },
  ];

  isSmall$ = this.responsiveService.isSmall$;

  constructor(
    private router: Router,
    private authService: AuthService,
    private responsiveService: ResponsiveService,
    private dialogService: DialogService
  ) {}

  handleSearchClick() {
    this.dialogService.open(TourSearchDialogComponent);
  }
}
