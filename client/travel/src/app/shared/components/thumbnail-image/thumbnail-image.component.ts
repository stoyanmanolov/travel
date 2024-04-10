import { Component, Input } from '@angular/core';
import { ApiService } from '../../../core/services/api.service';

@Component({
  selector: 'app-thumbnail-image',
  templateUrl: './thumbnail-image.component.html',
  styleUrl: './thumbnail-image.component.scss',
})
export class ThumbnailImageComponent {
  @Input() title: string = '';
  @Input() thumbnailImageId: number | undefined;

  constructor(private apiService: ApiService) {}

  getImageUrl(id: number | undefined) {
    if (!id) return '';

    return this.apiService.getImageUrl(id);
  }
}
