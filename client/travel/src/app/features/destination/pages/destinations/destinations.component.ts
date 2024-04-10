import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../../../core/services/api.service';
import { ResponsiveService } from '../../../../core/services/responsive.service';
import { Destination } from '../../../../core/models/Destination';

@Component({
  selector: 'app-destinations',
  templateUrl: './destinations.component.html',
  styleUrl: './destinations.component.scss',
})
export class DestinationsComponent implements OnInit {
  destinations: Destination[] = [];
  isSmall$ = this.responsiveService.isSmall$;
  isMedium$ = this.responsiveService.isMedium$;

  constructor(
    private apiService: ApiService,
    private responsiveService: ResponsiveService
  ) {}

  ngOnInit() {
    this.apiService.getDestinations().subscribe((destinations) => {
      this.destinations = destinations;
    });
  }
}
