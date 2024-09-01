package lk.ijse.notetaker.config;

import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "lk.ijse.notetaker")
@EnableWebMvc
@EnableJpaRepositories(basePackages = "lk.ijse.notetaker")
@EnableTransactionManagement
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,// yam kisi value ekak dammama ,file ekk upload karata passe tmp sve unama mekt wda ek value ekak awot gnne file system eka ,aduwen awoth primary memory eka
        maxFileSize = 1024 * 1024 * 10,//upload krn file eke size eka tiranaya karaai(dan tiyenne 10mb)
        maxRequestSize = 1024 * 1024 * 50 //upload krn file ekath athuluwa sampurana data wala ekathuwa tamai meken kiyanne
)
public class webAppConfig {

}
