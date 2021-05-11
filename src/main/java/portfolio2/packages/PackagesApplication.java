package portfolio2.packages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PackagesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PackagesApplication.class, args);
    }

}
