import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../material/material.module';
import { HttpClientModule } from '@angular/common/http';
import { DialogWrapperComponent } from './components/dialog-wrapper/dialog-wrapper.component';
import { ThumbnailImageComponent } from './components/thumbnail-image/thumbnail-image.component';
import { DateFromPipe } from './pipes/date-from.pipe';

const SharedModules = [
  CommonModule,
  MaterialModule,
  ReactiveFormsModule,
  FormsModule,
  HttpClientModule,
];

const SharedComponents = [
  DialogWrapperComponent,
  ThumbnailImageComponent,
  DateFromPipe,
];

@NgModule({
  imports: [SharedModules],
  exports: [SharedModules, SharedComponents],
  declarations: [SharedComponents],
})
export class SharedModule {}
