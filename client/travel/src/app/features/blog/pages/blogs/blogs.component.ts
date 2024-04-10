import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../../../core/services/api.service';
import { ResponsiveService } from '../../../../core/services/responsive.service';
import { Blog } from '../../../../core/models/Blog';

@Component({
  selector: 'app-blogs',
  templateUrl: './blogs.component.html',
  styleUrl: './blogs.component.scss',
})
export class BlogsComponent implements OnInit {
  blogs: Blog[] = [];

  isSmall$ = this.responsiveService.isSmall$;
  isMedium$ = this.responsiveService.isMedium$;

  constructor(
    private apiService: ApiService,
    private responsiveService: ResponsiveService
  ) {}

  ngOnInit() {
    this.apiService.getBlogs().subscribe((blogs) => {
      this.blogs = blogs;
    });
  }
}
