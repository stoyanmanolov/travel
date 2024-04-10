import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-auth-form',
  templateUrl: './auth-form.component.html',
  styleUrl: './auth-form.component.scss',
})
export class AuthFormComponent {
  @Input() title = '';
  @Input() buttonText = '';
  @Output() credentialsSubmit = new EventEmitter();

  validators = [Validators.required, Validators.minLength(6)];

  username = new FormControl('', this.validators);
  password = new FormControl('', this.validators);

  authForm: FormGroup = new FormGroup({
    username: this.username,
    password: this.password,
  });

  getErrorMessage(formControl: FormControl) {
    if (formControl.hasError('required')) {
      return 'You must enter a value';
    } else if (formControl.hasError('minlength')) {
      return 'You must enter at least 6 characters';
    }

    return '';
  }

  onSubmit() {
    if (this.authForm.valid) {
      this.credentialsSubmit.emit({
        username: this.authForm.value.username,
        password: this.authForm.value.password,
      });
    }
  }
}
