import { ComponentType } from '@angular/cdk/portal';
import { Injectable } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';

@Injectable({
  providedIn: 'root',
})
export class DialogService {
  constructor(private dialog: MatDialog) {}

  open(component: ComponentType<unknown>, config?: MatDialogConfig<any>) {
    this.dialog.open(component, config);
  }

  close() {
    this.dialog.closeAll();
  }
}
