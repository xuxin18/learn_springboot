package early_bird.repository;

import early_bird.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.repository
 * @date 2019/6/4 19:36
 * @description
 */
public interface BookRepository extends ElasticsearchRepository<Book, Integer> {
    //自定义一些方法，参考 https://docs.spring.io/spring-data/elasticsearch/docs/3.1.8.RELEASE/reference/html/#elasticsearch.query-methods
    public List<Book> findBooksByBookNameLike(String bookName);
}
