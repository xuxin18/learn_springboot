package early_bird.bean;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.bean
 * @date 2019/6/4 19:34
 * @description
 */

@Document(indexName = "掘金", type = "book")
public class Book {
    private Integer id;
    private String author;
    private String bookName;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
