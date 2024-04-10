import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../../../core/services/api.service';
import { Tour } from '../../../../core/models/Tour';
import { DialogService } from '../../../../core/services/dialog.service';
import { TourBookingDialogComponent } from '../../components/tour-booking-dialog/tour-booking-dialog.component';

@Component({
  selector: 'app-tour',
  templateUrl: './tour.component.html',
  styleUrl: './tour.component.scss',
})
export class TourComponent implements OnInit {
  id: string | null | undefined;
  tour: Tour | undefined;

  constructor(
    private route: ActivatedRoute,
    private apiService: ApiService,
    private dialogService: DialogService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.id = params['id'];

      if (this.id) {
        this.apiService.getTour(parseInt(this.id)).subscribe((tour) => {
          this.tour = tour;
        });
      }
    });
  }

  handleOpenBookingDialog() {
    this.dialogService.open(TourBookingDialogComponent, {
      data: {
        tour: this.tour,
      },
    });
  }
}
