import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../../../core/services/api.service';
import { Tour, TourStatus } from '../../../../core/models/Tour';
import { ResponsiveService } from '../../../../core/services/responsive.service';

@Component({
  selector: 'app-tours',
  templateUrl: './tours.component.html',
  styleUrl: './tours.component.scss',
})
export class ToursComponent implements OnInit {
  sections: {
    title: string;
    list: Tour[];
  }[] = [];
  upcomingTours: Tour[] = [];
  otherTours: Tour[] = [];

  isSmall$ = this.responsiveService.isSmall$;
  isMedium$ = this.responsiveService.isMedium$;

  constructor(
    private apiService: ApiService,
    private responsiveService: ResponsiveService
  ) {}

  ngOnInit() {
    this.apiService.getTours().subscribe((tours) => {
      this.sections.push({
        title: 'Upcoming tours',
        list: tours.filter((t) => t.status === TourStatus.UPCOMING),
      });
      this.sections.push({
        title: 'Other tours',
        list: tours.filter((t) => t.status !== TourStatus.UPCOMING),
      });
    });
  }
}
