package com.example.travel.service;

import com.example.travel.exception.NotFoundException;
import com.example.travel.exception.RequestException;
import com.example.travel.model.*;
import com.example.travel.repository.BlogRepository;
import com.example.travel.repository.CommentRepository;
import com.example.travel.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final TagRepository tagRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final TourService tourService;
    private final ImageService imageService;

    public BlogService(BlogRepository blogRepository, TagRepository tagRepository, CommentRepository commentRepository, UserService userService, TourService tourService, ImageService imageService) {
        this.blogRepository = blogRepository;
        this.tagRepository = tagRepository;
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.tourService = tourService;
        this.imageService = imageService;
    }

    public List<Blog> getBlogs() {
        return blogRepository.findAll();
    }

    public Blog getBlog(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Transactional
    public Blog createBlog(
            String title,
            String subtitle,
            String content,
            MultipartFile file,
            Long userId,
            Long tourId
    ) throws IOException, NotFoundException {
        User user = userService.getUser(userId);
        Tour tour = tourService.getTour(tourId);

        Blog blog = new Blog(title, subtitle, content, user, tour);

        Image image = imageService.upload(file, ImageType.THUMBNAIL);
        blog.setThumbnailImage(image);

        return blogRepository.save(blog);
    }

    public void deleteBlog(Long id, User user) throws RequestException, NotFoundException {
        if (!this.isOwner(id, user)) {
            throw new RequestException("You are not the owner of the blog");
        }
        blogRepository.deleteById(id);
    }

    @Transactional
    public Blog addTags(Long blogId, String[] tagNames) throws NotFoundException {
        Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new NotFoundException("Blog not found"));

        for (String tagName : tagNames) {
            boolean foundForBlog = tagRepository.findByBlogIdAndName(blogId, tagName).isPresent();
            Set<Tag> tags = blog.getTags();

            if (!foundForBlog) {
                Tag tag;
                Optional<Tag> optionalTag = tagRepository.findByName(tagName);

                if (optionalTag.isPresent()) {
                    tag = optionalTag.get();
                } else {
                    tag = new Tag(tagName);
                    tagRepository.save(tag);
                }

                tags.add(tag);
                blog.setTags(tags);
            }
        }

        return blogRepository.save(blog);
    }

    public Comment addBlogComment(Long blogId, User user, String content) throws NotFoundException {
        Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new NotFoundException("Blog not found"));

        Comment comment = new Comment(content, user, blog);

        return commentRepository.save(comment);
    }

    public List<Comment> getBlogComments(Long blogId) {
        return commentRepository.findAllByBlogId(blogId);
    }

    public boolean isOwner(Long blogId, User user) throws NotFoundException {
        Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new NotFoundException("Blog not found"));

        return Objects.equals(user.getId(), blog.getUser().getId());
    }
}
