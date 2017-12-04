package gm.vk.configuration.mvc;

import com.fasterxml.jackson.core.JsonEncoding;
import com.google.common.collect.Lists;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.http.MediaType;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan("gm.vk.controllers")
public class ServletConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

  private static final String DEFAULT_ENCODING = "UTF-8";

  private static final String PREFIX = "/WEB-INF/templates/";
  private static final String SUFFIX = ".html";

  private static final String RESOURCE_HANDLER = ".addResourceHandler";
  private static final String RESOURCE_LOCATION = "classpath:/META-INF/resources/webjars/";

  private ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  @Bean
  public ViewResolver contentNegotiationResolver(ContentNegotiationManager cnm) {

    ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();

    resolver.setContentNegotiationManager(cnm);
    resolver.setApplicationContext(applicationContext);
    resolver.setOrder(1);
    resolver.setDefaultViews(Lists.newArrayList(getJsonView()));

    return resolver;
  }

  private MappingJackson2JsonView getJsonView() {

    MappingJackson2JsonView view = new MappingJackson2JsonView();

    view.setPrettyPrint(true);
    view.setEncoding(JsonEncoding.UTF8);

    return view;
  }

  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

    final MediaType json = MediaType.APPLICATION_JSON;

    configurer
            .favorPathExtension(true)
            .favorParameter(false)
            .ignoreAcceptHeader(true)
            .useJaf(false)
            .defaultContentType(json)
            .parameterName("mediaType")
            .mediaType("json", json);
  }

  @Bean
  public LocalValidatorFactoryBean getValidator() {
    LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
    validator.setParameterNameDiscoverer(new LocalVariableTableParameterNameDiscoverer());
    return validator;
  }

  @Bean
  @Autowired
  public MethodValidationPostProcessor getValidationPostProcessor(
      LocalValidatorFactoryBean validator) {
    MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
    processor.setValidator(validator);
    return processor;
  }

  @Bean(name = "messageSource")
  public MessageSource messageSource() {

    ReloadableResourceBundleMessageSource messageSource =
            new ReloadableResourceBundleMessageSource();

    messageSource.setBasename("i18n");
    messageSource.setDefaultEncoding(DEFAULT_ENCODING);
    messageSource.setCacheSeconds(1800);

    return messageSource;
  }

  @Bean(name = "localeResolver")
  public LocaleResolver localeResolver() {

    CookieLocaleResolver resolver = new CookieLocaleResolver();

    resolver.setDefaultLocale(new Locale("en"));
    resolver.setCookieName("localCookie");
    resolver.setCookieMaxAge(1800);

    return resolver;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();

    interceptor.setParamName("locale");
    registry.addInterceptor(interceptor);
  }

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    registry
            .addResourceHandler(RESOURCE_HANDLER)
            .addResourceLocations(RESOURCE_LOCATION)
            .setCachePeriod(86400);
  }

  @Bean(name = "viewResolver")
  public ViewResolver getViewResolver() {
    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
    resolver.setTemplateEngine(getTemplateEngine());
    resolver.setCharacterEncoding(DEFAULT_ENCODING);
    return resolver;
  }

  @Bean
  public TemplateEngine getTemplateEngine() {
    SpringTemplateEngine engine = new SpringTemplateEngine();
    engine.setTemplateResolver(getTemplateResolver());
    return engine;
  }

  @Bean
  public ITemplateResolver getTemplateResolver() {
    SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
    resolver.setApplicationContext(applicationContext);
    resolver.setPrefix(PREFIX);
    resolver.setSuffix(SUFFIX);
    resolver.setTemplateMode(TemplateMode.HTML);
    resolver.setCacheable(true);
    return resolver;
  }

}
