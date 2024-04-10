import { TourResponseDto } from '../dto/TourResponseDto';

export interface Tour extends TourResponseDto {}

export enum TourStatus {
  UPCOMING = 'UPCOMING',
  ACTIVE = 'ACTIVE',
  FINISHED = 'FINISHED',
}
