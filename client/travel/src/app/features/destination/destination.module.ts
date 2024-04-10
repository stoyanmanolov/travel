import { NgModule } from '@angular/core';
import { DestinationsComponent } from './pages/destinations/destinations.component';
import { DestinationRoutingModule } from './destination-routing.module';
import { DestinationCardComponent } from './components/destination-card/destination-card.component';
import { SharedModule } from '../../shared/shared.module';

@NgModule({
  declarations: [DestinationsComponent, DestinationCardComponent],
  imports: [DestinationRoutingModule, SharedModule],
})
export class DestinationModule {}
