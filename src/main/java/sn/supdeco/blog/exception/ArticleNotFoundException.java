package sn.supdeco.blog.exception;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(String message) {
        super(message);
    }
}
