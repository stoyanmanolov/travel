import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserBookingsComponent } from './pages/user-bookings/user-bookings.component';
import { AllBookingsComponent } from './pages/all-bookings/all-bookings.component';
import { adminGuard } from '../../core/guards/admin.guard';

const routes: Routes = [
  {
    path: 'user-bookings',
    component: UserBookingsComponent,
  },
  {
    path: 'all',
    component: AllBookingsComponent,
    canActivate: [adminGuard],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class BookingRoutingModule {}
