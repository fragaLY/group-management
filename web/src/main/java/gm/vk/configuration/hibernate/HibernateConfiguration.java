package gm.vk.configuration.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories({
  "gm.vk.core.dao.data.contacts.address",
  "gm.vk.core.dao.data.contacts",
  "gm.vk.core.dao.data.personal",
  "gm.vk.core.dao.person.role",
  "gm.vk.core.dao.person",
  "gm.vk.core.dao.subject.examination.grade",
  "gm.vk.core.dao.subject.examination.type",
  "gm.vk.core.dao.subject.examination",
  "gm.vk.core.dao.subject",
  "gm.vk.core.dao.group",
  "gm.vk.core.dao.group.course",
  "gm.vk.core.dao.group.faculty",
  "gm.vk.core.dao.user"
})
@PropertySource("classpath:properties/jdbc.properties")
@ComponentScan({"gm.vk.core", "gm.vk.service"})
public class HibernateConfiguration {

  private static final String DATASOURCE_PROPERTY_DRIVER = "javax.persistence.jdbc.driver";
  private static final String DATASOURCE_PROPERTY_URL = "javax.persistence.jdbc.url";
  private static final String DATASOURCE_PROPERTY_USER = "javax.persistence.jdbc.user";
  private static final String DATASOURCE_PROPERTY_PASSWORD = "javax.persistence.jdbc.password";

  private static final String HIBERNATE_PROPERTY_DIALECT = "hibernate.dialect";
  private static final String HIBERNATE_PROPERTY_SHOW_SQL = "hibernate.show_sql";
  private static final String HIBERNATE_PROPERTY_FORMAT_SQL = "hibernate.format_sql";
  private static final String HIBERNATE_PROPERTY_GENERATE_STATISTICS = "hibernate.generate_statistics";
  private static final String HIBERNATE_PROPERTY_MAX_POOL_SIZE = "hibernate.c3p0.max_size";
  private static final String HIBERNATE_PROPERTY_TIME_OUT = "hibernate.c3p0.timeout";
  private static final String HIBERNATE_PROPERTY_SQL_EXTRACTOR = "hibernate.hbm2ddl.import_files_sql_extractor";
  private static final String HIBERNATE_PROPERTY_IMPORT_FILES = "hibernate.hbm2ddl.import_files";

  private final Environment environment;

  @Autowired
  public HibernateConfiguration(Environment environment) {
    this.environment = environment;
  }

  @Bean(name = "dataSource")
  public DataSource getDataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName(environment.getRequiredProperty(DATASOURCE_PROPERTY_DRIVER));
    dataSource.setUrl(environment.getRequiredProperty(DATASOURCE_PROPERTY_URL));
    dataSource.setUsername(environment.getRequiredProperty(DATASOURCE_PROPERTY_USER));
    dataSource.setPassword(environment.getRequiredProperty(DATASOURCE_PROPERTY_PASSWORD));
    return dataSource;
  }

  private Properties getHibernateProperties() {

    Properties properties = new Properties();

    properties.put(HIBERNATE_PROPERTY_DIALECT, environment.getRequiredProperty(HIBERNATE_PROPERTY_DIALECT));
    properties.put(HIBERNATE_PROPERTY_SHOW_SQL, environment.getRequiredProperty(HIBERNATE_PROPERTY_SHOW_SQL));
    properties.put(HIBERNATE_PROPERTY_FORMAT_SQL, environment.getRequiredProperty(HIBERNATE_PROPERTY_FORMAT_SQL));
    properties.put(HIBERNATE_PROPERTY_GENERATE_STATISTICS, environment.getRequiredProperty(HIBERNATE_PROPERTY_GENERATE_STATISTICS));
    properties.put(HIBERNATE_PROPERTY_MAX_POOL_SIZE, environment.getRequiredProperty(HIBERNATE_PROPERTY_MAX_POOL_SIZE));
    properties.put(HIBERNATE_PROPERTY_TIME_OUT, environment.getRequiredProperty(HIBERNATE_PROPERTY_TIME_OUT));
    properties.put(HIBERNATE_PROPERTY_SQL_EXTRACTOR, environment.getRequiredProperty(HIBERNATE_PROPERTY_SQL_EXTRACTOR));
    properties.put(HIBERNATE_PROPERTY_IMPORT_FILES, "/sql-scripts/*");

    return properties;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

    LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();

    emfb.setDataSource(getDataSource());
    emfb.setPackagesToScan(
        "gm.vk.core.domain.data.contacts",
        "gm.vk.core.domain.data.contacts.address",
        "gm.vk.core.domain.data.personal",
        "gm.vk.core.domain.person",
        "gm.vk.core.domain.person.role",
        "gm.vk.core.domain.subject",
        "gm.vk.core.domain.subject.examination",
        "gm.vk.core.domain.subject.examination.grade",
        "gm.vk.core.domain.subject.examination.type",
        "gm.vk.core.domain.group",
        "gm.vk.core.domain.group.course",
        "gm.vk.core.domain.group.faculty",
        "gm.vk.core.domain.user");
    emfb.setJpaVendorAdapter(getJpaVendorAdapter());
    emfb.setJpaProperties(getHibernateProperties());

    return emfb;
  }

  @Bean
  public JpaVendorAdapter getJpaVendorAdapter() {

    HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
    hibernateJpaVendorAdapter.setPrepareConnection(true);

    return hibernateJpaVendorAdapter;
  }

  @Bean
  public PlatformTransactionManager transactionManager() {

    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

    return transactionManager;
  }
}
