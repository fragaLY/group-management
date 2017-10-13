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

  private static final String HIBERNATE_PROPERTY_DIALECT = "hibernate.dialect";
  private static final String HIBERNATE_PROPERTY_SHOW_SQL = "hibernate.show_sql";
  private static final String HIBERNATE_PROPERTY_FORMAT_SQL = "hibernate.format_sql";
  private static final String HIBERNATE_PROPERTY_GENERATE_STATISTICS = "hibernate.generate_statistics";
  private static final String HIBERNATE_PROPERTY_MAX_POOL_SIZE = "hibernate.c3p0.max_size";
  private static final String HIBERNATE_PROPERTY_TIME_OUT = "hibernate.c3p0.timeout";
  private static final String HIBERNATE_SQL_EXTRACTOR = "hibernate.hbm2ddl.import_files_sql_extractor";

  private static final String CREATE_SCHEMAS = "sql-scripts/create/Create_Schemas.sql";
  private static final String CREATE_ADDRESS_TABLES = "sql-scripts/create/Create_Address_Tables.sql";
  private static final String CREATE_CONTACTS_TABLES = "sql-scripts/create/Create_Contacts_Tables.sql";
  private static final String CREATE_PERSONAL_DATA_TABLES = "sql-scripts/create/Create_PersonalData_Tables.sql";
  private static final String CREATE_PERSON_ROLE_TABLES = "sql-scripts/create/Create_PersonRole_Tables.sql";
  private static final String CREATE_PERSON_TABLES = "sql-scripts/create/Create_Person_Tables.sql";
  private static final String CREATE_USER_TABLES = "sql-scripts/create/Create_User_Tables.sql";
  private static final String CREATE_GRADE_TABLES = "sql-scripts/create/Create_Grade_Tables.sql";
  private static final String CREATE_EXAMINATION_TYPE_TABLES = "sql-scripts/create/Create_ExaminationType_Tables.sql";
  private static final String CREATE_EXAMINATION_TABLES = "sql-scripts/create/Create_Examination_Tables.sql";
  private static final String CREATE_SUBJECT_TABLES = "sql-scripts/create/Create_Subject_Tables.sql";
  private static final String CREATE_COURSE_TABLES = "sql-scripts/create/Create_Course_Tables.sql";
  private static final String CREATE_SEMESTER_TABLES = "sql-scripts/create/Create_Semester_Tables.sql";
  private static final String CREATE_FACULTY_TABLES = "sql-scripts/create/Create_Faculty_Tables.sql";
  private static final String CREATE_STUDENTGROUP_TABLES = "sql-scripts/create/Create_StudentGroup_Tables.sql";
  private static final String CREATE_PERSONID_GROUPID_TABLES = "sql-scripts/create/Create_PersonId_GroupId_Tables.sql";
  private static final String CREATE_PERSONID_SUBJECTID_TABLES = "sql-scripts/create/Create_PersonId_SubjectId_Tables.sql";
  private static final String CREATE_SUBJECTID_GROUPID_TABLES = "sql-scripts/create/Create_SubjectId_GroupId_Tables.sql";

  private static final String INIT_ADDRESS_TABLE = "sql-scripts/init/Init_Address_Table.sql";
  private static final String INIT_CONTACTS_TABLE = "sql-scripts/init/Init_Contacts_Table.sql";
  private static final String INIT_PERSONAL_DATA_TABLE = "sql-scripts/init/Init_PersonalData_Table.sql";
  private static final String INIT_PERSON_ROLE_TABLE = "sql-scripts/init/Init_PersonRole_Table.sql";
  private static final String INIT_PERSON_TABLE = "sql-scripts/init/Init_Person_Table.sql";
  private static final String INIT_USER_TABLE = "sql-scripts/init/Init_User_Table.sql";
  private static final String INIT_COURSE_TABLE = "sql-scripts/init/Init_Course_Table.sql";
  private static final String INIT_SEMESTER_TABLE = "sql-scripts/init/Init_Semester_Table.sql";
  private static final String INIT_FACULTY_TABLE = "sql-scripts/init/Init_Faculty_Table.sql";
  private static final String INIT_STUDENTGROUP_TABLE = "sql-scripts/init/Init_Group_Table.sql";
  private static final String INIT_PERSONID_GROUPID_TABLE = "sql-scripts/init/Init_PersonId_GroupId_Table.sql";
  private static final String INIT_GRADE_TABLE = "sql-scripts/init/Init_Grade_Table.sql";
  private static final String INIT_EXAMINATION_TYPE_TABLE = "sql-scripts/init/Init_ExaminationType_Table.sql";
  private static final String INIT_EXAMINATION_TABLE = "sql-scripts/init/Init_Examination_Table.sql";
  private static final String INIT_SUBJECT_TABLE = "sql-scripts/init/Init_Subject_Table.sql";
  private static final String INIT_PERSONID_SUBJECTID_TABLE = "sql-scripts/init/Init_PersonId_SubjectId_Table.sql";
  private static final String INIT_SUBJECTID_GROUPID_TABLE = "sql-scripts/init/Init_SubjectId_GroupId_Table.sql";

  private final Environment environment;

  @Autowired
  public HibernateConfiguration(Environment environment) {
    this.environment = environment;
  }

  @Bean(name = "dataSource")
  public DataSource getDataSource() {

    return new EmbeddedDatabaseBuilder()
            .continueOnError(true)
            .setType(EmbeddedDatabaseType.H2)
            .addScript(CREATE_SCHEMAS)
            .addScripts(CREATE_ADDRESS_TABLES, CREATE_CONTACTS_TABLES)
            .addScripts(CREATE_PERSONAL_DATA_TABLES, CREATE_PERSON_ROLE_TABLES, CREATE_PERSON_TABLES, CREATE_USER_TABLES)
            .addScripts(CREATE_GRADE_TABLES, CREATE_EXAMINATION_TYPE_TABLES, CREATE_EXAMINATION_TABLES, CREATE_SUBJECT_TABLES)
            .addScripts(CREATE_COURSE_TABLES, CREATE_SEMESTER_TABLES, CREATE_FACULTY_TABLES, CREATE_STUDENTGROUP_TABLES)
            .addScript(CREATE_PERSONID_SUBJECTID_TABLES)
            .addScript(CREATE_PERSONID_GROUPID_TABLES)
            .addScript(CREATE_SUBJECTID_GROUPID_TABLES)
            .addScripts(INIT_ADDRESS_TABLE, INIT_CONTACTS_TABLE)
            .addScripts(INIT_PERSONAL_DATA_TABLE, INIT_PERSON_ROLE_TABLE, INIT_PERSON_TABLE, INIT_USER_TABLE)
            .addScripts(INIT_GRADE_TABLE, INIT_EXAMINATION_TYPE_TABLE, INIT_EXAMINATION_TABLE, INIT_SUBJECT_TABLE)
            .addScripts(INIT_COURSE_TABLE, INIT_SEMESTER_TABLE, INIT_FACULTY_TABLE, INIT_STUDENTGROUP_TABLE)
            .addScript(INIT_PERSONID_SUBJECTID_TABLE)
            .addScript(INIT_PERSONID_GROUPID_TABLE)
            .addScript(INIT_SUBJECTID_GROUPID_TABLE)
            .build();
  }

  private Properties getHibernateProperties() {

    Properties properties = new Properties();

    properties.put(HIBERNATE_PROPERTY_DIALECT, environment.getRequiredProperty(HIBERNATE_PROPERTY_DIALECT));
    properties.put(HIBERNATE_PROPERTY_SHOW_SQL, environment.getRequiredProperty(HIBERNATE_PROPERTY_SHOW_SQL));
    properties.put(HIBERNATE_PROPERTY_FORMAT_SQL, environment.getRequiredProperty(HIBERNATE_PROPERTY_FORMAT_SQL));
    properties.put(HIBERNATE_PROPERTY_GENERATE_STATISTICS, environment.getRequiredProperty(HIBERNATE_PROPERTY_GENERATE_STATISTICS));
    properties.put(HIBERNATE_PROPERTY_MAX_POOL_SIZE, environment.getRequiredProperty(HIBERNATE_PROPERTY_MAX_POOL_SIZE));
    properties.put(HIBERNATE_PROPERTY_TIME_OUT, environment.getRequiredProperty(HIBERNATE_PROPERTY_TIME_OUT));
    properties.put(HIBERNATE_SQL_EXTRACTOR, environment.getRequiredProperty(HIBERNATE_SQL_EXTRACTOR));

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
