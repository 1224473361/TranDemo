package com.demo.datasources;

import javax.transaction.UserTransaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

/**
 * 
 * @date 2019年6月14日
 * @author lihui
 */
@Configuration
public class JTranConfig {

	@Bean("jtx")
	public JtaTransactionManager jtaTransactionManager() {
		UserTransactionManager userTransaction = new UserTransactionManager();
		UserTransaction transactionManager = new UserTransactionImp();
		return new JtaTransactionManager(transactionManager, userTransaction);
	}

}
