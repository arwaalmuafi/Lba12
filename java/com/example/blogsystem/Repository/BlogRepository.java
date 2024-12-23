package com.example.blogsystem.Repository;

import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Integer> {

    Blog findBlogById(Integer id);

    List<Blog> findAllByMyUser(MyUser myUser);

    Blog findBlogByTitle(String title);
}
