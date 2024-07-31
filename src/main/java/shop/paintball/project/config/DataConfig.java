package shop.paintball.project.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataConfig {

    @Bean
    public DataSource dataSource() {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/floatingballs?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(100);
        dataSource.setMaxIdle(30);
        dataSource.setMinIdle(5);
        dataSource.setMaxWaitMillis(10000);

        return dataSource;

    }

}
