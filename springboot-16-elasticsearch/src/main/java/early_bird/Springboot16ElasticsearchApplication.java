package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
在docker中启动 elasticsearch
	docker run -e ES_JAVA_OPTS="-Xms256m -Xmx256m" -d -p 9200:9200 -p 9300:9300 --name ES01 镜像id
	启动成功后，在浏览器访问： docker主机所在的ip:9200 看能不能访问elasticsearch
	ps： -p 后面第一个 9200 是虚拟机端口，第二个 9200 是 docker 内部端口

elasticsearch：是目前全文搜索引擎的首选，他可以快速的存储、搜索和分析海量数据。elasticsearch是一个分布式搜索服务，提供Restful API
	采用多shard（分片）的方式保证数据安全。（例如：github的搜索功能采用的就是elasticsearch）
	ps：elasticsearch中：
		索引：
			作为名词时：类似与传统关系型数据库中的数据库
			作为动词时：索引一个文档 就是指 将一个一个文档存储到索引

SpringBoot 默认支持两种技术与ES交互：
	1.Jest（默认不生效）
		需要导入jest工具包（io.searchbox.client.JestClient）
	2.SpringData ElasticSearch(springboot默认的 ES 版本可能不适合，版本适配说明：https://github.com/spring-projects/spring-data-elasticsearch)
			如果版本不适配：
				1.升级springboot版本
				2.安装对应版本的ES（推荐）
		client节点信息：clusterNodes、clusterName
		ElasticsearchTemplate操作es
		编写一个 ElasticsearchRepository的子接口来操作es
			这两站方式详见：https://github.com/spring-projects/spring-data-elasticsearch
*/
@SpringBootApplication
public class Springboot16ElasticsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot16ElasticsearchApplication.class, args);
	}

}
