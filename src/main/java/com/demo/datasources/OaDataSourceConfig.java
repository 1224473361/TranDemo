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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.atomikos.jdbc.AtomikosDataSourceBean;

@Configuration
@MapperScan(basePackages = { OaDataSourceConfig.PACKAGES }, sqlSessionTemplateRef = "sqlSessionTemplate_oa")
public class OaDataSourceConfig {

	public static final String PACKAGES = "com.demo.oa.mapper";
	public static final String XMLPATH = "classpath:com/demo/oa/mapper/*.xml";

	@Bean(name = "dataSource_oa")
	@ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.oa") // 配置文件中数据源配置开头
	@Primary
	public DataSource dataSource() {
		return new AtomikosDataSourceBean();
	}

	@Bean(name = "sqlSessionFactory_oa")
	@Primary
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource_oa") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		// xml文件位置
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(XMLPATH));
		bean.getObject().getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
		return bean.getObject();
	}

	@Bean(name = "sqlSessionTemplate_oa")
	@Primary
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory_oa") SqlSessionFactory sqlSessionFactory)
			throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
