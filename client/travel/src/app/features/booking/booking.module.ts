import { NgModule } from '@angular/core';
import { UserBookingsComponent } from './pages/user-bookings/user-bookings.component';
import { SharedModule } from '../../shared/shared.module';
import { BookingRoutingModule } from './booking-routing.module';
import { AllBookingsComponent } from './pages/all-bookings/all-bookings.component';

@NgModule({
  declarations: [UserBookingsComponent, AllBookingsComponent],
  imports: [BookingRoutingModule, SharedModule],
})
export class BookingModule {}
