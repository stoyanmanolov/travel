import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../../../core/services/api.service';

type DataSource = {
  number: number;
  tourTitle: string;
};

@Component({
  selector: 'app-user-bookings',
  templateUrl: './user-bookings.component.html',
  styleUrl: './user-bookings.component.scss',
})
export class UserBookingsComponent implements OnInit {
  displayedColumns: string[] = ['number', 'tourTitle'];
  dataSource: DataSource[] = [];

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.apiService.getUserBookings().subscribe((bookings) => {
      this.dataSource = bookings.map((b) => ({
        number: b.id,
        tourTitle: b.tourTitle,
      }));
    });
  }
}
