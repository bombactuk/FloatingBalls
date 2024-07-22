package shop.paintball.project.config;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("shop.paintball.project.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;

    }

    private Properties hibernateProperties() {

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.HBM2DDL_AUTO, "update");

        // C3P0 properties
        properties.put(Environment.C3P0_MIN_SIZE, 5);
        properties.put(Environment.C3P0_MAX_SIZE, 100);
        properties.put(Environment.C3P0_ACQUIRE_INCREMENT, 5);
        properties.put(Environment.C3P0_TIMEOUT, 600);
        properties.put(Environment.C3P0_MAX_STATEMENTS, 200);
        properties.put(Environment.C3P0_IDLE_TEST_PERIOD, 3000);

        return properties;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);

        return transactionManager;

    }

}
