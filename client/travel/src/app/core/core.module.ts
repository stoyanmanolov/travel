import { NgModule } from '@angular/core';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoaderComponent } from './components/loader/loader.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { HttpLoaderInterceptor } from './interceptors/http-loader.interceptor';
import { SnackBarService } from './services/snack-bar.service';
import { HttpErrorInterceptor } from './interceptors/http-error.interceptor';
import { SnackBarComponent } from './components/snack-bar/snack-bar.component';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../material/material.module';
import { HttpTokenInterceptor } from './interceptors/http-token.interceptor';

@NgModule({
  declarations: [NavbarComponent, LoaderComponent, SnackBarComponent],
  imports: [CommonModule, MaterialModule, HttpClientModule],
  exports: [NavbarComponent, LoaderComponent],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpTokenInterceptor,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpLoaderInterceptor,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpErrorInterceptor,
      multi: true,
      deps: [SnackBarService],
    },
  ],
})
export class CoreModule {}
