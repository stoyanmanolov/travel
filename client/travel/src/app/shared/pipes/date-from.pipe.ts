import { Pipe, PipeTransform } from '@angular/core';
import moment from 'moment';

@Pipe({
  name: 'dateFrom',
  pure: false,
})
export class DateFromPipe implements PipeTransform {
  transform(value: string): string {
    return moment(value).fromNow();
  }
}
