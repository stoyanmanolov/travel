import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { BehaviorSubject } from 'rxjs';
import { LoaderComponent } from '../components/loader/loader.component';

@Injectable({
  providedIn: 'root',
})
export class LoaderService {
  isLoadingSubject = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoadingSubject.asObservable();

  constructor(private dialog: MatDialog) {}

  getName(id?: string): string {
    return 'loader' + (id || 0);
  }

  show(id?: string): void {
    this.dialog.open(LoaderComponent, {
      id: this.getName(id),
      disableClose: true,
    });
    this.isLoadingSubject.next(true);
  }

  hide(id?: string): void {
    this.dialog.getDialogById(this.getName(id))?.close();
    this.isLoadingSubject.next(false);
  }
}
