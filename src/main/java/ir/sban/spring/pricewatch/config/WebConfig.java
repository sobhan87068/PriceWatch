package ir.sban.spring.pricewatch.config;
  import org.springframework.context.MessageSource;
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;
  import org.springframework.context.support.MessageSourceAccessor;
  import org.springframework.context.support.ReloadableResourceBundleMessageSource;
  import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
  import org.springframework.web.bind.annotation.RestController;
  import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
  import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
  @Configuration
  public class WebConfig implements WebMvcConfigurer {
      @Override
      public void configurePathMatch(PathMatchConfigurer configurer) {
          configurer.addPathPrefix("/api", c -> c.isAnnotationPresent(RestController.class));
      }

      @Bean
      public MessageSource messageSource() {
          ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
          messageSource.setDefaultEncoding("UTF-8");
          messageSource.setBasename("classpath:messages");
          return messageSource;
      }

      @Bean
      public LocalValidatorFactoryBean validator() {
          LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
          bean.setValidationMessageSource(messageSource());
          return bean;
      }

      @Bean
      public MessageSourceAccessor messageSourceAccessor() {
          return new MessageSourceAccessor(messageSource());
      }
  }