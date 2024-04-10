import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Tour } from '../../../../core/models/Tour';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ApiService } from '../../../../core/services/api.service';
import { SnackBarService } from '../../../../core/services/snack-bar.service';
import { SnackBarType } from '../../../../core/models/SnackBarType';
import { DialogService } from '../../../../core/services/dialog.service';

@Component({
  selector: 'app-tour-booking-dialog',
  templateUrl: './tour-booking-dialog.component.html',
  styleUrl: './tour-booking-dialog.component.scss',
})
export class TourBookingDialogComponent {
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { tour: Tour },
    private apiService: ApiService,
    private snackBarService: SnackBarService,
    private dialogService: DialogService
  ) {}

  phoneNumber = new FormControl('', [
    Validators.required,
    Validators.pattern('^[+]?[(]?[0-9]{3}[)]?[-s.]?[0-9]{3}[-s.]?[0-9]{4,6}$'),
  ]);

  bookingForm = new FormGroup({
    phoneNumber: this.phoneNumber,
  });

  getErrorMessage(formControl: FormControl) {
    if (formControl.hasError('required')) {
      return 'You must enter a value';
    } else if (formControl.hasError('pattern')) {
      return 'Invalid phone number';
    }

    return '';
  }

  onSubmit() {
    if (this.bookingForm.valid && this.bookingForm.value.phoneNumber) {
      this.apiService
        .bookTour(this.data.tour.id, this.bookingForm.value.phoneNumber)
        .subscribe((msg) => {
          this.snackBarService.open(SnackBarType.SUCCESS, msg);
          this.dialogService.close();
        });
    }
  }
}
