import { Component } from '@angular/core';
import { AuthService } from '../../../../core/services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {
  heading = 'A travel app.';

  isAuthenticated$ = this.authService.isAuthenticated$;

  constructor(private authService: AuthService) {}
}
