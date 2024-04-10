import { Component, Input } from '@angular/core';
import { Blog } from '../../../../core/models/Blog';
import { Router } from '@angular/router';
import { ApiService } from '../../../../core/services/api.service';

@Component({
  selector: 'app-blog-card',
  templateUrl: './blog-card.component.html',
  styleUrl: './blog-card.component.scss',
})
export class BlogCardComponent {
  @Input() blog!: Blog;

  constructor(private router: Router, private apiService: ApiService) {}

  getImageUrl(id: number) {
    return this.apiService.getImageUrl(id);
  }

  handleExplore() {
    this.router.navigate(['/blogs', this.blog.id]);
  }
}
