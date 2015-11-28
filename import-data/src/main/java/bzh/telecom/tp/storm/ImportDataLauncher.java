package bzh.telecom.tp.storm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Clement on 28/11/15.
 */
@SpringBootApplication
@Configuration
@EnableScheduling
public class ImportDataLauncher {

    public static void main(String[] args) {
        SpringApplication.run(ImportDataLauncher.class, args);
    }
}
