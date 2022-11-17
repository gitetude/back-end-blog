package sn.supdeco.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sn.supdeco.blog.models.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{
    
}
