import { Component, Inject } from '@angular/core';
import { SnackBarService } from '../../services/snack-bar.service';
import { MAT_SNACK_BAR_DATA } from '@angular/material/snack-bar';
import { SnackBarType } from '../../models/SnackBarType';

type SnackBarData = {
  type: SnackBarType;
  message: string;
  action?: string;
};

@Component({
  selector: 'app-snack-bar',
  templateUrl: './snack-bar.component.html',
  styleUrl: './snack-bar.component.scss',
})
export class SnackBarComponent {
  snackBars: { type: SnackBarType; icon: string }[] = [
    { type: SnackBarType.INFO, icon: 'info' },
    { type: SnackBarType.SUCCESS, icon: 'done' },
    { type: SnackBarType.ERROR, icon: 'error' },
  ];

  constructor(
    @Inject(MAT_SNACK_BAR_DATA) public data: SnackBarData,
    private snackBarService: SnackBarService
  ) {}

  dismiss() {
    this.snackBarService.dismiss();
  }

  isInfo() {
    return this.data.type === SnackBarType.INFO;
  }

  isSuccess() {
    return this.data.type === SnackBarType.SUCCESS;
  }

  isError() {
    return this.data.type === SnackBarType.ERROR;
  }

  getIcon() {
    return this.snackBars.find((s) => s.type === this.data.type)?.icon;
  }
}
