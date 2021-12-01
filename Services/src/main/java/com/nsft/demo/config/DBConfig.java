package com.nsft.demo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.nsft.demo.repository",
					   entityManagerFactoryRef = "firstEntityManagerFactoryRef", 
					   transactionManagerRef = "firstTransactionManagerFactoryRef")
public class DBConfig {
	
	@Autowired
	private Environment env;

	@Bean(name = "first-db")
	@Primary
	public DataSource firstDb() {
	   HikariDataSource dataSource = new HikariDataSource();
	   dataSource.setJdbcUrl(env.getProperty("primary.datasource.url"));
	   dataSource.setUsername(env.getProperty("primary.datasource.username"));
	   dataSource.setPassword(env.getProperty("primary.datasource.password"));
	   dataSource.setDriverClassName(env.getProperty("primary.datasource.driver-class-name"));
	   
	   return dataSource;
	}
	
	@Bean(name = "firstEntityManagerFactoryRef")
	@Primary
	public LocalContainerEntityManagerFactoryBean firstEntityManager() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.POSTGRESQL);
		jpaVendorAdapter.setShowSql(Boolean.parseBoolean(env.getProperty("primary.jpa.show-sql")));
		jpaVendorAdapter.setGenerateDdl(Boolean.parseBoolean(env.getProperty("primary.jpa.generate-ddl")));
		
		Properties properties = new Properties();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("primary.jpa.hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("primary.jpa.hibernate.dialect"));
		
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = 
				new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setJpaProperties(properties);
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		localContainerEntityManagerFactoryBean.setDataSource(firstDb());
		localContainerEntityManagerFactoryBean.setPackagesToScan(env.getProperty(
				"primary.jpa.packages-to-scan"));
		

		return localContainerEntityManagerFactoryBean;
	}
	
	@Bean(name = "firstTransactionManagerFactoryRef")
	public PlatformTransactionManager transactionManager() {
		
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(firstEntityManager().getObject());
		return jpaTransactionManager;
	}
	
	
}
