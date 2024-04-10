import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ApiService } from '../../../../core/services/api.service';
import { SnackBarService } from '../../../../core/services/snack-bar.service';
import { Blog } from '../../../../core/models/Blog';
import { SnackBarType } from '../../../../core/models/SnackBarType';

@Component({
  selector: 'app-comment-form',
  templateUrl: './comment-form.component.html',
  styleUrl: './comment-form.component.scss',
})
export class CommentFormComponent {
  @Output() success = new EventEmitter();
  @Input() blog!: Blog;

  commentForm = new FormGroup({
    content: new FormControl('', [Validators.required]),
  });

  constructor(
    private apiService: ApiService,
    private snackBarService: SnackBarService
  ) {}

  onSubmit() {
    if (this.commentForm.valid && this.commentForm.value.content) {
      this.apiService
        .addBlogComment(this.blog.id, this.commentForm.value.content)
        .subscribe((comment) => {
          this.success.emit(comment);
          this.snackBarService.open(
            SnackBarType.SUCCESS,
            'Comment successfully added'
          );
          this.commentForm.reset();
        });
    }
  }
}
