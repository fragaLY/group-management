package gm.vk.configuration.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
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

  private final Environment environment;

  @Autowired
  public HibernateConfiguration(Environment environment) {
    this.environment = environment;
  }

  @Bean(name = "dataSource")
  public DataSource getDataSource() {

    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();

    return builder
        .continueOnError(true)
        .setType(EmbeddedDatabaseType.H2)
        .addScript("") // todo vk: add scripts
        .build();
  }

  private Properties getHibernateProperties() {

    Properties properties = new Properties();

    properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
    properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
    properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
    properties.put(
        "hibernate.generate_statistics",
        environment.getRequiredProperty("hibernate.generate_statistics"));

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
