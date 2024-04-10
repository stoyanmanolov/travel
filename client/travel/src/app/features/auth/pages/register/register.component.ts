import { Component } from '@angular/core';
import { AuthService } from '../../../../core/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
})
export class RegisterComponent {
  constructor(private authService: AuthService) {}

  registerUser({ username, password }: { username: string; password: string }) {
    this.authService.register(username, password).subscribe();
  }
}
