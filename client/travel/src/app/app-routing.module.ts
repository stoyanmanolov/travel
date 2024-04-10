import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';

const routes: Routes = [
  {
    path: '',
    loadChildren: () =>
      import('./features/home/home.module').then((m) => m.HomeModule),
  },
  {
    path: 'auth',
    loadChildren: () =>
      import('./features/auth/auth.module').then((m) => m.AuthModule),
  },
  {
    path: 'tours',
    loadChildren: () =>
      import('./features/tour/tour.module').then((m) => m.TourModule),
    canActivate: [authGuard],
  },
  {
    path: 'destinations',
    loadChildren: () =>
      import('./features/destination/destination.module').then(
        (m) => m.DestinationModule
      ),
    canActivate: [authGuard],
  },
  {
    path: 'bookings',
    loadChildren: () =>
      import('./features/booking/booking.module').then((m) => m.BookingModule),
    canActivate: [authGuard],
  },
  {
    path: 'blogs',
    loadChildren: () =>
      import('./features/blog/blog.module').then((m) => m.BlogModule),
    canActivate: [authGuard],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
