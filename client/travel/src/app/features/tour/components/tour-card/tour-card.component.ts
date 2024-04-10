import { Component, Input } from '@angular/core';
import { Tour } from '../../../../core/models/Tour';
import { ApiService } from '../../../../core/services/api.service';
import { Router } from '@angular/router';
import { DialogService } from '../../../../core/services/dialog.service';
import { TourBookingDialogComponent } from '../tour-booking-dialog/tour-booking-dialog.component';
import { TourReviewsDialogComponent } from '../tour-reviews-dialog/tour-reviews-dialog.component';

@Component({
  selector: 'app-tour-card',
  templateUrl: './tour-card.component.html',
  styleUrl: './tour-card.component.scss',
})
export class TourCardComponent {
  @Input() tour!: Tour;

  constructor(
    private router: Router,
    private apiService: ApiService,
    private dialogService: DialogService
  ) {}

  getImageUrl(id: number) {
    return this.apiService.getImageUrl(id);
  }

  handleExplore() {
    this.router.navigate(['/tours', this.tour.id]);
  }

  handleOpenBookingDialog() {
    this.dialogService.open(TourBookingDialogComponent, {
      data: {
        tour: this.tour,
      },
    });
  }

  handleOpenReviewsDialog() {
    this.dialogService.open(TourReviewsDialogComponent, {
      data: {
        tour: this.tour,
      },
    });
  }
}
