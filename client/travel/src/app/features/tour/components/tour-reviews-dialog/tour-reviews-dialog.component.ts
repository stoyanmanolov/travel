import { Component, Inject, OnInit } from '@angular/core';
import { Tour } from '../../../../core/models/Tour';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Review } from '../../../../core/models/Review';
import { ApiService } from '../../../../core/services/api.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-tour-reviews-dialog',
  templateUrl: './tour-reviews-dialog.component.html',
  styleUrl: './tour-reviews-dialog.component.scss',
})
export class TourReviewsDialogComponent implements OnInit {
  reviews: Review[] = [];

  validators = [Validators.required];

  rating = new FormControl('', [Validators.required]);
  description = new FormControl();

  reviewForm = new FormGroup({
    rating: this.rating,
    description: this.description,
  });

  onSubmit() {
    if (this.reviewForm.valid) {
      this.apiService
        .reviewTour(
          this.data.tour.id,
          this.reviewForm.value.rating ?? '',
          this.reviewForm.value.description
        )
        .subscribe((review) => {
          this.reviews.unshift(review);
        });
    }
  }

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { tour: Tour },
    private apiService: ApiService
  ) {}

  ngOnInit(): void {
    this.apiService.getTourReviews(this.data.tour.id).subscribe((reviews) => {
      this.reviews = reviews;
    });
  }
}
