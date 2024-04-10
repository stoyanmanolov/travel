import { NgModule } from '@angular/core';
import { AuthRoutingModule } from './auth-routing.module';
import { AuthFormComponent } from './components/auth-form/auth-form.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { SharedModule } from '../../shared/shared.module';

@NgModule({
  declarations: [AuthFormComponent, LoginComponent, RegisterComponent],
  imports: [AuthRoutingModule, SharedModule],
  exports: [],
})
export class AuthModule {}
