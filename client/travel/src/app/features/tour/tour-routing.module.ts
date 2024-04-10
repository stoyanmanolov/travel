import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ToursComponent } from './pages/tours/tours.component';
import { TourComponent } from './pages/tour/tour.component';

const routes: Routes = [
  {
    path: ':id',
    component: TourComponent,
  },
  {
    path: '',
    component: ToursComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TourRoutingModule {}
