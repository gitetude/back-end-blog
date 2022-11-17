package sn.supdeco.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import sn.supdeco.blog.models.Article;
import sn.supdeco.blog.services.ArticleService;
import sn.supdeco.blog.services.dtos.ArticleCreationDto;
import sn.supdeco.blog.services.impl.ArticleServiceImpl;

@RestController
@RequestMapping("/api/v1/articles")
@AllArgsConstructor
public class ArticleController {
    
   // private final ArticleService articleService;
    @Autowired
    private ArticleServiceImpl postService;

    @PostMapping
    public ResponseEntity createArticle(@RequestBody ArticleCreationDto postDto) {
        postService.create(postDto);
        return new ResponseEntity(HttpStatus.OK);
    }
   // @PostMapping("")
   // public ResponseEntity<Article> createArticle(@RequestBody ArticleCreationDto articleDto){
     //   Article article = new Article();
       // article.setContent(articleDto.getContent());
        //article.setTitle(articleDto.getTitle());

        //return ResponseEntity.ok(articleService.create(article));
    //}
        @GetMapping("/all")
        public ResponseEntity<List<ArticleCreationDto>> getAllArticle() {
            return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
        }

    @GetMapping("/get/{id}")
    public ResponseEntity<ArticleCreationDto> getSinglePost(@PathVariable @RequestBody Long id) {
        return new ResponseEntity<>(postService.readSinglePost(id), HttpStatus.OK);
    }
   // @GetMapping("")
   // public ResponseEntity<List<Article>> getAllArticle() {
     //   return ResponseEntity.ok(articleService.getAll());
   // }

}
