import { NgModule } from '@angular/core';
import { ToursComponent } from './pages/tours/tours.component';
import { TourRoutingModule } from './tour-routing.module';
import { TourComponent } from './pages/tour/tour.component';
import { SharedModule } from '../../shared/shared.module';
import { TourBookingDialogComponent } from './components/tour-booking-dialog/tour-booking-dialog.component';
import { TourReviewsDialogComponent } from './components/tour-reviews-dialog/tour-reviews-dialog.component';
import { TourCardComponent } from './components/tour-card/tour-card.component';
import { TourSearchDialogComponent } from './components/tour-search-dialog/tour-search-dialog.component';

@NgModule({
  declarations: [
    ToursComponent,
    TourComponent,
    TourBookingDialogComponent,
    TourReviewsDialogComponent,
    TourCardComponent,
    TourSearchDialogComponent,
  ],
  imports: [TourRoutingModule, SharedModule],
  exports: [],
})
export class TourModule {}
