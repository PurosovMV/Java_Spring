package PMV.HW8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		MyServiceBean bean = context.getBean(MyServiceBean.class);
		System.out.println(bean.method1(19999999));
	}

}
