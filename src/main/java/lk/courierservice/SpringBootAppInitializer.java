package lk.courierservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 2/13/2022
 **/
@SpringBootApplication
public class SpringBootAppInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAppInitializer.class);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
