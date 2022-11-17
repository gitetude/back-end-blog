package sn.supdeco.blog.services.impl;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import sn.supdeco.blog.exception.ArticleNotFoundException;
import sn.supdeco.blog.models.Article;
import sn.supdeco.blog.repositories.ArticleRepository;
import sn.supdeco.blog.repositories.UserRepository;
import sn.supdeco.blog.services.ArticleService;
import sn.supdeco.blog.services.dtos.ArticleCreationDto;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;
    @Autowired
    private UserServiceImpl authService;
    private final UserRepository userRepository;

   // @Override
   // public Article create(Article article) {
       // return articleRepository.save(article);
    //}

  //  @Override
   // public List<Article> getAll() {
     //   return articleRepository.findAll();
    //}
  @Transactional
  public List<ArticleCreationDto> getAll() {
      List<Article> posts = articleRepository.findAll();
      return posts.stream().map(this::mapFromPostToDto).collect(toList());
  }

    @Transactional
    public void create(ArticleCreationDto postDto) {
        Article post = mapFromDtoToPost(postDto);
        articleRepository.save(post);
    }
    @Transactional
    public ArticleCreationDto readSinglePost(Long id) {
        Article post = articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException("For id " + id));
        return mapFromPostToDto(post);
    }

    private ArticleCreationDto mapFromPostToDto(Article post) {
        ArticleCreationDto postDto = new ArticleCreationDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        return postDto;
    }


    private Article mapFromDtoToPost(ArticleCreationDto postDto) {
        Article post = new Article();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        return post;
    }
    
}
