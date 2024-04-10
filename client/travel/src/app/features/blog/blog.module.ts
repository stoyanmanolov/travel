import { NgModule } from '@angular/core';
import { BlogsComponent } from './pages/blogs/blogs.component';
import { BlogRoutingModule } from './blog-routing.module';
import { SharedModule } from '../../shared/shared.module';
import { BlogCardComponent } from './components/blog-card/blog-card.component';
import { BlogComponent } from './pages/blog/blog.component';
import { CommentComponent } from './components/comment/comment.component';
import { CommentFormComponent } from './components/comment-form/comment-form.component';

@NgModule({
  declarations: [BlogsComponent, BlogCardComponent, BlogComponent, CommentComponent, CommentFormComponent],
  imports: [SharedModule, BlogRoutingModule],
})
export class BlogModule {}
