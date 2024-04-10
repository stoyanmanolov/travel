import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SnackBarType } from '../models/SnackBarType';
import { SnackBarComponent } from '../components/snack-bar/snack-bar.component';

@Injectable({
  providedIn: 'root',
})
export class SnackBarService {
  constructor(private snackBar: MatSnackBar) {}

  open(type: SnackBarType, message: string, action?: string) {
    this.snackBar.openFromComponent(SnackBarComponent, {
      duration: 10000,
      horizontalPosition: 'right',
      verticalPosition: 'bottom',
      data: {
        type,
        message,
        ...(action && { action }),
      },
    });
  }

  dismiss() {
    this.snackBar.dismiss();
  }
}
