package sn.supdeco.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import sn.supdeco.blog.models.Article;
import sn.supdeco.blog.services.dtos.ArticleCreationDto;

@Service
public interface ArticleService {
   // Article create(Article article);
   // List<Article> getAll();
    List<ArticleCreationDto> getAll();

    void create(ArticleCreationDto postDto);

    ArticleCreationDto readSinglePost(Long id);
}
