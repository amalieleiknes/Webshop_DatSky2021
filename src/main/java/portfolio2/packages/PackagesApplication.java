package portfolio2.packages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import portfolio2.packages.Objects.CustomerRegister;
import portfolio2.packages.Objects.ProductRegister;

@SpringBootApplication
public class PackagesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PackagesApplication.class, args);

        CustomerRegister.initialize();
        ProductRegister.initialize();
    }

}
