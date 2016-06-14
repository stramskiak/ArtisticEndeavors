package com.leatherswan.artisticendeavors.config;  
import javax.persistence.Persistence;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration 
@ComponentScan("com.leatherswan.artisticendeavors.model")
@EnableTransactionManagement
@Import(DispatcherConfig.class)
public class AppConfig { 
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
//	       BasicDataSource dataSource = new BasicDataSource();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
	       dataSource.setUrl("jdbc:derby://localhost:1527/c:/Users/Anita/derbydata/artisticendeavors;create=true");
	       dataSource.setUsername("app");
	       dataSource.setPassword("app");
//	       dataSource.setConnectionProperties("classpath:application");
	       return dataSource;
	}
	@Bean
	public PlatformTransactionManager jpaTransMan(){
		JpaTransactionManager jtManager = new JpaTransactionManager(
				getEntityManagerFactoryBean().getObject());
		return jtManager;
	}
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
		lcemfb.setDataSource(getDataSource());
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        lcemfb.setJpaVendorAdapter(vendorAdapter);
		lcemfb.setPackagesToScan("com.leatherswan.artisticendeavors.model");
//		LoadTimeWeaver loadTimeWeaver = new InstrumentationLoadTimeWeaver();
//		lcemfb.setLoadTimeWeaver(loadTimeWeaver);
		lcemfb.afterPropertiesSet();
		return lcemfb;
	}
}