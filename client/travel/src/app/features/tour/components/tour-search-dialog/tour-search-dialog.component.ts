import { Component, OnInit } from '@angular/core';
import { Subject, debounceTime, distinctUntilChanged } from 'rxjs';
import { ApiService } from '../../../../core/services/api.service';
import { Tour } from '../../../../core/models/Tour';
import { Router } from '@angular/router';
import { DialogService } from '../../../../core/services/dialog.service';

@Component({
  selector: 'app-tour-search-dialog',
  templateUrl: './tour-search-dialog.component.html',
  styleUrl: './tour-search-dialog.component.scss',
})
export class TourSearchDialogComponent implements OnInit {
  searchModel: string = '';
  searchModelChanged: Subject<string> = new Subject<string>();

  tours: Tour[] = [];

  constructor(
    private apiService: ApiService,
    private dialogService: DialogService,
    private router: Router
  ) {}

  searchTours(query: string) {
    this.apiService.searchTours(query).subscribe((tours) => {
      this.tours = tours;
    });
  }

  ngOnInit(): void {
    this.searchModelChanged
      .pipe(debounceTime(300), distinctUntilChanged())
      .subscribe((searchModel) => {
        this.searchTours(searchModel);
        this.searchModel = searchModel;
      });
  }

  handleTourClick(tour: Tour) {
    this.router.navigate(['/tours', tour.id]);
    this.dialogService.close();
  }
}
