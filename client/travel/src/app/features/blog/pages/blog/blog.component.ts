import { Component } from '@angular/core';
import { Blog } from '../../../../core/models/Blog';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../../../core/services/api.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Comment } from '../../../../core/models/Comment';

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrl: './blog.component.scss',
})
export class BlogComponent {
  id: string | null | undefined;
  blog: Blog | undefined;
  comments: Comment[] = [];

  content = new FormControl('', [Validators.required]);

  commentForm = new FormGroup({
    content: this.content,
  });

  constructor(private route: ActivatedRoute, private apiService: ApiService) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.id = params['id'];
    });

    if (this.id) {
      this.apiService.getBlog(parseInt(this.id)).subscribe((blog) => {
        this.blog = blog;
      });

      this.apiService
        .getBlogComments(parseInt(this.id))
        .subscribe((comments) => {
          this.comments = comments;
        });
    }
  }

  handleNewCommentAdded(comment: Comment) {
    this.comments.unshift(comment);
  }
}
