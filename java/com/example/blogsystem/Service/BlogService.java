package com.example.blogsystem.Service;

import com.example.blogsystem.ApiResponse.ApiException;
import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.MyUser;
import com.example.blogsystem.Repository.AuthRepository;
import com.example.blogsystem.Repository.BlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlogService {

    private final AuthRepository authRepository;

    private final BlogRepository blogRepository;

    public List<Blog> getMyBlogs(Integer userId) {
        MyUser myUser = authRepository.findMyUserById(userId);
        if (myUser == null) {
            throw new ApiException("Invalid user ID");
        }
        return blogRepository.findAllByMyUser(myUser);
    }

    public void addBlog(Integer userId, Blog blog) {
        MyUser myUser = authRepository.findMyUserById(userId);
        if (myUser == null) {
            throw new ApiException("Invalid user ID");
        }
        blog.setMyUser(myUser);
        blogRepository.save(blog);
    }

    public void updateBlog(MyUser myUser, Integer blogId, Blog blog) {
        MyUser myUser1=authRepository.findMyUserById(myUser.getId());
        Blog oldBlog=blogRepository.findBlogById(blogId);
        if(oldBlog==null){
            throw new ApiException("no blog found");
        }
        oldBlog.setTitle(blog.getTitle());
        oldBlog.setBody(blog.getBody());
        blogRepository.save(oldBlog);
    }

    public void deleteBlog(Integer blogId, Integer userId) {
        Blog blog = blogRepository.findBlogById(blogId);
        if (blog == null) {
            throw new ApiException("Blog not found");
        } else if (blog.getMyUser().getId()!=userId) {
            throw new ApiException("You do not have permission to delete this blog");
        }
        blogRepository.delete(blog);
    }

    public Blog getBlogByTitle(String title) {
        Blog blog = blogRepository.findBlogByTitle(title);
        if (blog == null) {
            throw new ApiException("Blog with the given title not found");
        }
        return blog;
    }

    public Blog getBlogById(Integer blogId) {
        Blog blog = blogRepository.findBlogById(blogId);
        if (blog == null) {
            throw new ApiException("Blog not found");
        }
        return blog;
    }
}
