package com.dsd26.bachkhoaxanh.config;

import java.util.Properties;

import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.dsd26.bachkhoaxanh.dao.IBaoCaoTinhTrangCayDAO;
import com.dsd26.bachkhoaxanh.dao.IBaoCaoTinhTrangDCNDAO;
import com.dsd26.bachkhoaxanh.dao.ICayDAO;
import com.dsd26.bachkhoaxanh.dao.IDiemCapNuocDAO;
import com.dsd26.bachkhoaxanh.dao.ILichSuTuoiDAO;
import com.dsd26.bachkhoaxanh.dao.ILoaiCayDAO;
import com.dsd26.bachkhoaxanh.dao.ILoaiThanhVienDAO;
import com.dsd26.bachkhoaxanh.dao.IThanhVienDAO;
import com.dsd26.bachkhoaxanh.dao.IThongBaoDAO;
import com.dsd26.bachkhoaxanh.dao.impl.BaoCaoTinhTrangCayDAO;
import com.dsd26.bachkhoaxanh.dao.impl.BaoCaoTinhTrangDCNDAO;
import com.dsd26.bachkhoaxanh.dao.impl.CayDAO;
import com.dsd26.bachkhoaxanh.dao.impl.DiemCapNuocDAO;
import com.dsd26.bachkhoaxanh.dao.impl.LichSuTuoiDAO;
import com.dsd26.bachkhoaxanh.dao.impl.LoaiCayDAO;
import com.dsd26.bachkhoaxanh.dao.impl.LoaiThanhVienDAO;
import com.dsd26.bachkhoaxanh.dao.impl.ThanhVienDAO;
import com.dsd26.bachkhoaxanh.dao.impl.ThongBaoDAO;

/*
 * author: Nguyễn Phúc Đạc
 */
 
@Configuration
@ComponentScan("com.dsd26.bachkhoaxanh.*")
@EnableTransactionManagement
// Load to Environment.
@PropertySource("classpath:ds-hibernate-cfg.properties")
public class ApplicationContextConfig {

	//ServerConnection ServerConnection = new ServerConnection();
   // Lưu trữ các giá thuộc tính load bởi @PropertySource.
   @Autowired
   private Environment env;
 
   @Bean
   public ResourceBundleMessageSource messageSource() {
       ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
       // Load property in message/validator.properties
       rb.setBasenames(new String[] { "messages/validator" });
       return rb;
   }
 
   @Bean(name = "viewResolver")
   public InternalResourceViewResolver getViewResolver() {
       InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
       viewResolver.setPrefix("/WEB-INF/views/");
       viewResolver.setSuffix(".jsp");
       return viewResolver;
   }
    
  
   // Cấu hình để Upload.
   @Bean(name = "multipartResolver")
   public CommonsMultipartResolver multipartResolver() {
       CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        
       // Set Max Size...
       // commonsMultipartResolver.setMaxUploadSize(...);
        
       return commonsMultipartResolver;
   }
 
   @Bean(name = "dataSource")
   public DataSource getDataSource() {
       DriverManagerDataSource dataSource = new DriverManagerDataSource();
 
  
       // Xem: ds-hibernate-cfg.properties
       dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
       dataSource.setUrl(env.getProperty("ds.url"));
       dataSource.setUsername(env.getProperty("ds.username"));
       dataSource.setPassword(env.getProperty("ds.password"));
        
       System.out.println("## getDataSource: " + dataSource);
        
       return dataSource;
   }
 
   @Autowired
   @Bean(name = "sessionFactory")
   public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
       Properties properties = new Properties();
 
  
       // Xem: ds-hibernate-cfg.properties
       properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
       properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
       properties.put("current_session_context_class", env.getProperty("current_session_context_class"));
        
 
       LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
  
       // Package chứa các entity class.
       factoryBean.setPackagesToScan(new String[] { "com.dsd26.bachkhoaxanh.entity" });
       factoryBean.setDataSource(dataSource);
       factoryBean.setHibernateProperties(properties);
       factoryBean.afterPropertiesSet();
       //
       SessionFactory sf = factoryBean.getObject();
       System.out.println("## getSessionFactory: " + sf);
       return sf;
   }
 
   @Autowired
   @Bean(name = "transactionManager")
   public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
       HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
 
       return transactionManager;
   }
 
   @Bean(name="cayDAO")
   public ICayDAO getCayDAO() {
	   return new CayDAO();
   }
   @Bean(name="loaiCayDAO")
   public ILoaiCayDAO getLoaiCayDAO() {
	   return new LoaiCayDAO();
   }

   @Bean(name="diemCapNuocDAO")
   public IDiemCapNuocDAO getDiemCapNuocDAO() {
	   return new DiemCapNuocDAO();
   }
   @Bean(name="thanhVienDAO")
   public IThanhVienDAO getThanhVienDAO() {
	   return new ThanhVienDAO();
   }

   @Bean(name="loaiThanhVienDAO")
   public ILoaiThanhVienDAO getLoaiThanhVienDAO() {
	   return new LoaiThanhVienDAO();
   }
 
   @Bean(name="lichSuTuoiDAO")
   public ILichSuTuoiDAO getLichSuTuoiDAO() {
	   return new LichSuTuoiDAO();
   }
   
   @Bean(name="thongBaoDAO")
   public IThongBaoDAO getThongBaoDAO() {
	   return new ThongBaoDAO();
   }
   
   @Bean(name="baoCaoTinhTrangCayDAO")
   public IBaoCaoTinhTrangCayDAO getBaoCaoTinhTrangCayDAO() {
	   return new BaoCaoTinhTrangCayDAO();
   }
   
   @Bean(name="baoCaoTinhTrangDCNDAO")
   public IBaoCaoTinhTrangDCNDAO getBaoCaoTinhTrangDCNDAO() {
	   return new BaoCaoTinhTrangDCNDAO();
   }
}
