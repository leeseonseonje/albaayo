package albaAyo.albaayo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AlbaayoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbaayoApplication.class, args);
	}
}
