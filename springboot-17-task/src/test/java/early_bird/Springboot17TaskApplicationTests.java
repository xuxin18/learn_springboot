package early_bird;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot17TaskApplicationTests {

	@Autowired
	JavaMailSenderImpl mailSender;

	@Value("${spring.mail.username}")
	private String myEmail;

	@Test
	public void sendSimpleMail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("通知");
		message.setText("今天下午3点钟开会");
		message.setTo("335314862@qq.com");
		message.setFrom(myEmail);

		mailSender.send(message);
	}

	@Test
	public void sendMimeMail() throws MessagingException {
		//创建一个复杂的消息邮件
		MimeMessage message = mailSender.createMimeMessage();
		// multipart 为 true 代表 邮件总有附件
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setSubject("通知");
		// html 设置为 true 是为了 开启复杂邮件的功能
		helper.setText("今天下午3点钟开会",true);
		helper.setTo("335314862@qq.com");
		helper.setFrom(myEmail);
		helper.addAttachment("1.jpg", new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Hydrangeas.jpg"));

		mailSender.send(message);
	}

}
