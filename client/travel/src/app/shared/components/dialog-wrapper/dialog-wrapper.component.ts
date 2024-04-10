import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-dialog-wrapper',
  templateUrl: './dialog-wrapper.component.html',
  styleUrl: './dialog-wrapper.component.scss',
})
export class DialogWrapperComponent {
  @Input() title: String = '';
  @Input() hideActions: boolean = false;
  @Input() hideConfirm: boolean = false;
  @Input() closeBtnText: String = 'Close';
  @Input() confirmBtnText: String = 'Confirm';
}
