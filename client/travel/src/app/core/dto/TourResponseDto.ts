import { TourStatus } from '../models/Tour';

export interface TourResponseDto {
  id: number;
  title: string;
  description: string;
  price: number;
  startDate: string;
  endDate: string;
  status: TourStatus;
  thumbnailImageId: number;
  destinationId: number;
  destinationName: string;
  destinationCountry: string;
  reviewsCount: number;
  reviewsRatingAverage: number;
}
