import { Component, Input } from '@angular/core';
import { Destination } from '../../../../core/models/Destination';

@Component({
  selector: 'app-destination-card',
  templateUrl: './destination-card.component.html',
  styleUrl: './destination-card.component.scss',
})
export class DestinationCardComponent {
  @Input() destination!: Destination;
}
