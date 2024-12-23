package com.example.blogsystem.Controller;

import com.example.blogsystem.ApiResponse.ApiResponse;
import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.MyUser;
import com.example.blogsystem.Service.BlogService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@RestController
@AllArgsConstructor
@RequestMapping("/v1/api/Blog")
public class BlogController {
    private final BlogService blogService;


    @GetMapping("/get")
    public ResponseEntity getMyBlogs(@AuthenticationPrincipal MyUser myUser) {
        return ResponseEntity.status(200).body(blogService.getMyBlogs(myUser.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid Blog blog) {
        blogService.addBlog(myUser.getId(), blog);
        return ResponseEntity.status(200).body(new ApiResponse("Blog added successfully"));
    }

    @PutMapping("/update/{blogId}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer blogId, @RequestBody @Valid Blog blog) {
        blogService.updateBlog(myUser, blogId, blog);
        return ResponseEntity.status(200).body(new ApiResponse("Blog updated successfully"));
    }

    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer blogId) {
        blogService.deleteBlog(blogId, myUser.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Blog deleted successfully"));
    }


    @GetMapping("/get/{blogId}")
    public ResponseEntity getBlogById(@PathVariable Integer blogId) {
        return ResponseEntity.status(200).body(blogService.getBlogById(blogId));
    }

    @GetMapping("/getTitle/{title}")
    public ResponseEntity getBlogByTitle(@PathVariable String title) {
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title));
    }


}
