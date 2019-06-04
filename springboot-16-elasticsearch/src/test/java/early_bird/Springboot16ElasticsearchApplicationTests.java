package early_bird;

import early_bird.bean.Article;
import early_bird.bean.Book;
import early_bird.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot16ElasticsearchApplicationTests {

	@Autowired
	JestClient jestClient;

	@Autowired
	BookRepository bookRepository;

	@Test
	public void testJPAES2(){

		for (Book book : bookRepository.findBooksByBookNameLike("游记")) {
			System.out.println(book);
		}
	}

	@Test
	public void testJPAES1(){
		//注意在使用 JPA ElasticSearch 时， 索引和类型的声明使用 @Document 注解 标注在 bean 上
		Book book = new Book();
		book.setId(1);
		book.setBookName("西游记");
		book.setAuthor("吴承恩");
		bookRepository.index(book);
	}

	@Test
	public void test1() {
		Article article = new Article(1,"微服务", "马丁","微服务如何落地呢？");
		//给 文档数据 article 设置 索引、类型
		Index index = new Index.Builder(article).index("掘金").type("news").build();
		try {
			jestClient.execute(index);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSearch(){
		//查询表达式
		String json = "{\n" +
				"    \"query\" : {\n" +
				"        \"match\" : {\n" +
				"            \"content\" : \"微服务\"\n" +
				"        }\n" +
				"    }\n" +
				"}";
		//构建搜索功能
		Search search = new Search.Builder(json).addIndex("掘金").addType("news").build();

		try {
			SearchResult result = jestClient.execute(search);
			System.out.println(result.getJsonString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
