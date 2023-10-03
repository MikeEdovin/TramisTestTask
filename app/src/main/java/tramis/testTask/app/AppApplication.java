package tramis.testTask.app;

import Configs.DBConfig;
import Configs.ServiceConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import({DBConfig.class, ServiceConfig.class})
public class AppApplication {
	public static void main(String[] args) {}
}
