package com.demo.datasources;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.atomikos.jdbc.AtomikosDataSourceBean;

@Configuration
@MapperScan(basePackages = { EmrpDataSourceConfig.PACKAGES }, sqlSessionTemplateRef = "sqlSessionTemplate_emerp")
public class EmrpDataSourceConfig {

	public static final String PACKAGES = "com.demo.emrp.mapper";
	public static final String XMLPATH = "classpath:com/demo/emrp/mapper/*.xml";

	@Bean(name = "dataSource_emerp")
	@ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.emerp") // 配置文件中数据源配置开头
	public DataSource dataSource() {
		return new AtomikosDataSourceBean();
	}

	@Bean(name = "sqlSessionFactory_emerp")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource_emerp") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		// xml文件位置
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(XMLPATH));
		bean.getObject().getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
		return bean.getObject();
	}

	@Bean(name = "sqlSessionTemplate_emerp")
	public SqlSessionTemplate sqlSessionTemplate(
			@Qualifier("sqlSessionFactory_emerp") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
