import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthResponseDto } from '../dto/AuthResponseDto';
import { environment } from '../../../environments/environment';
import { TourResponseDto } from '../dto/TourResponseDto';
import { DestinationResponseDto } from '../dto/DestinationResponseDto';
import { ReviewResponseDto } from '../dto/ReviewResponseDto';
import { BookingResponseDto } from '../dto/BookingResponseDto';
import { BlogResponseDto } from '../dto/BlogResponseDto';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private readonly API_URL = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getImageUrl(id: number) {
    const url = `${this.API_URL}/images/download/${id}`;
    return url;
  }

  getAuthenticatedUser() {
    const url = `${this.API_URL}/auth/user`;
    return this.http.get<AuthResponseDto>(url);
  }

  login(username: string, password: string) {
    const url = `${this.API_URL}/auth/login`;
    return this.http.post<AuthResponseDto>(url, { username, password });
  }

  register(username: string, password: string) {
    const url = `${this.API_URL}/auth/register`;
    return this.http.post<AuthResponseDto>(url, { username, password });
  }

  getTours() {
    const url = `${this.API_URL}/tours`;
    return this.http.get<TourResponseDto[]>(url);
  }

  getTour(id: number) {
    const url = `${this.API_URL}/tours/${id}`;
    return this.http.get<TourResponseDto>(url);
  }

  getFeaturedTour() {
    const url = `${this.API_URL}/tours/featured`;
    return this.http.get<TourResponseDto>(url);
  }

  searchTours(query: string) {
    const url = `${this.API_URL}/tours/search`;
    return this.http.get<TourResponseDto[]>(url, { params: { query } });
  }

  reviewTour(tourId: number, rating: string, description?: string) {
    const url = `${this.API_URL}/tours/${tourId}/review`;
    return this.http.post<ReviewResponseDto>(url, {
      rating,
      description,
    });
  }

  bookTour(tourId: number, phoneNumber: string) {
    const url = `${this.API_URL}/tours/${tourId}/book`;
    return this.http.post(
      url,
      {
        phoneNumber,
      },
      {
        responseType: 'text',
      }
    );
  }

  markBookingAsPaid(bookingId: number) {
    const url = `${this.API_URL}/tours/bookings/${bookingId}`;
    return this.http.post<BookingResponseDto>(url, {});
  }

  getUserBookings() {
    const url = `${this.API_URL}/tours/user-bookings`;
    return this.http.get<BookingResponseDto[]>(url);
  }

  getBookings() {
    const url = `${this.API_URL}/tours/bookings`;
    return this.http.get<BookingResponseDto[]>(url);
  }

  getTourReviews(id: number) {
    const url = `${this.API_URL}/tours/${id}/reviews`;
    return this.http.get<ReviewResponseDto[]>(url);
  }

  getDestinations() {
    const url = `${this.API_URL}/destinations`;
    return this.http.get<DestinationResponseDto[]>(url);
  }

  getBlogs() {
    const url = `${this.API_URL}/blogs`;
    return this.http.get<BlogResponseDto[]>(url);
  }

  getBlog(id: number) {
    const url = `${this.API_URL}/blogs/${id}`;
    return this.http.get<BlogResponseDto>(url);
  }

  getBlogComments(id: number) {
    const url = `${this.API_URL}/blogs/${id}/comments`;
    return this.http.get<BlogResponseDto[]>(url);
  }

  addBlogComment(id: number, content: string) {
    const url = `${this.API_URL}/blogs/${id}/comments`;
    return this.http.post<BlogResponseDto>(url, {
      content,
    });
  }
}
