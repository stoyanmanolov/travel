package com.example.travel.controller;

import com.example.travel.dto.BlogCreationDto;
import com.example.travel.dto.BlogResponseDto;
import com.example.travel.dto.CommentCreationDto;
import com.example.travel.dto.CommentResponseDto;
import com.example.travel.exception.NotFoundException;
import com.example.travel.exception.RequestException;
import com.example.travel.mapper.BlogMapper;
import com.example.travel.mapper.CommentMapper;
import com.example.travel.model.Blog;
import com.example.travel.model.Comment;
import com.example.travel.model.User;
import com.example.travel.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    private final BlogService blogService;
    private final BlogMapper blogMapper;
    private final CommentMapper commentMapper;

    public BlogController(BlogService blogService, BlogMapper blogMapper, CommentMapper commentMapper) {
        this.blogService = blogService;
        this.blogMapper = blogMapper;
        this.commentMapper = commentMapper;
    }

    @GetMapping
    public ResponseEntity<List<BlogResponseDto>> getBlogs() {
        List<Blog> blogList = blogService.getBlogs();

        return ResponseEntity.ok(
                blogList
                        .stream()
                        .map(blogMapper::toDto)
                        .toList()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<BlogResponseDto> getBlog(@PathVariable Long id) {
        Blog blog = blogService.getBlog(id);

        return ResponseEntity.ok(
                blogMapper.toDto(blog)
        );
    }

    @PostMapping
    public ResponseEntity<BlogResponseDto> createBlog(@ModelAttribute @Valid BlogCreationDto dto) throws IOException, NotFoundException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Blog blog = blogService.createBlog(
                dto.getTitle(),
                dto.getSubtitle(),
                dto.getContent(),
                dto.getImageFile(),
                user.getId(),
                dto.getTourId()
        );

        return ResponseEntity.ok(
                blogMapper.toDto(blog)
        );
    }

    @DeleteMapping("{id}")
    ResponseEntity<String> deleteBlog(@PathVariable Long id) throws RequestException, NotFoundException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        blogService.deleteBlog(id, user);

        return ResponseEntity.ok("The blog is successfully deleted");
    }

    @PostMapping("{id}/tags")
    public ResponseEntity<BlogResponseDto> addTags(@PathVariable Long id, String[] tagNames) throws NotFoundException {
        Blog blog = blogService.addTags(id, tagNames);

        return ResponseEntity.ok(
                blogMapper.toDto(blog)
        );
    }

    @GetMapping("{id}/comments")
    public ResponseEntity<List<CommentResponseDto>> getBlogComments(@PathVariable Long id) {
        List<Comment> comments = blogService.getBlogComments(id);

        comments.sort((c1, c2) -> c2.getCreatedAt().compareTo(c1.getCreatedAt()));

        return ResponseEntity.ok(
                comments
                        .stream()
                        .map(commentMapper::toDto)
                        .toList()
        );
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<CommentResponseDto> addBlogComment(
            @PathVariable Long id,
            @RequestBody @Valid CommentCreationDto dto
            ) throws NotFoundException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = blogService.addBlogComment(id, user, dto.getContent());

        return ResponseEntity.ok(
                commentMapper.toDto(comment)
        );
    }
}
