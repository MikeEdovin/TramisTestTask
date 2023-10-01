package Configs;

import Repositories.OrderRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = OrderRepository.class)
@EntityScan(basePackages = {"Entities"})
public class DBConfig {
}
