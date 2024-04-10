import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../../../core/services/api.service';
import { SnackBarService } from '../../../../core/services/snack-bar.service';
import { SnackBarType } from '../../../../core/models/SnackBarType';
import { Booking } from '../../../../core/models/Booking';

type DataSource = {
  number: number;
  tourTitle: string;
  username: string;
  phoneNumber: string;
  paid: string;
};

@Component({
  selector: 'app-all-bookings',
  templateUrl: './all-bookings.component.html',
  styleUrl: './all-bookings.component.scss',
})
export class AllBookingsComponent implements OnInit {
  displayedColumns: string[] = [
    'number',
    'tourTitle',
    'username',
    'phoneNumber',
    'paid',
  ];
  dataSource: DataSource[] = [];

  constructor(
    private apiService: ApiService,
    private snackBarService: SnackBarService
  ) {}

  mapToDataSource(booking: Booking) {
    return {
      number: booking.id,
      tourTitle: booking.tourTitle,
      username: booking.username,
      phoneNumber: booking.phoneNumber,
      paid: booking.paid ? 'Yes' : 'No',
    };
  }

  populateDataSource(bookings: Booking[]) {
    this.dataSource = bookings.map(this.mapToDataSource);
  }

  updateDataSource(booking: Booking) {
    this.dataSource = this.dataSource.map((b) =>
      b.number === booking.id ? this.mapToDataSource(booking) : b
    );
  }

  ngOnInit(): void {
    this.apiService.getBookings().subscribe((bookings) => {
      this.populateDataSource(bookings);
    });
  }

  handleMarkAsPaid(id: number) {
    this.apiService.markBookingAsPaid(id).subscribe((booking: Booking) => {
      this.updateDataSource(booking);

      this.snackBarService.open(
        SnackBarType.SUCCESS,
        'Successfully marked as paid'
      );
    });
  }
}
