package com.mithilesh.blog.repository;
import com.mithilesh.blog.entity.Category;
import com.mithilesh.blog.entity.Post;
import com.mithilesh.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);
}
