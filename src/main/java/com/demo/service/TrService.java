package com.demo.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.emrp.service.EmrpService;
import com.demo.oa.service.OaService;

/**
 * 测试service
 * 
 * @date 2019年6月17日
 * @author lihui
 */
@Service
public class TrService {

	@Autowired
	private EmrpService emrpService;
	@Autowired
	private OaService oaService;

	/**
	 * 使用事务控制
	 * 
	 * @param n
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public boolean serviceT(String n) throws Exception {
		System.out.println("emrp插入-----------");
		this.emrpService.insert(n);
		System.out.println("emrp 插入结束-----------");
		System.out.println("oa插入-----------");
		this.oaService.insert(n);
		//抛出异常
		int t = 1 / 0;
		System.out.println(t);
		System.out.println("emrp插入结束-----------");
		return true;
	}

	/**
	 * 无事务控制
	 * 
	 * @param n
	 * @return
	 * @throws Exception
	 */
	public boolean serviceNT(String n) throws Exception {
		System.out.println("emrp插入-----------");
		this.emrpService.insert(n);
		System.out.println("emrp 插入结束-----------");
		//抛出异常
		int t = 1 / 0;
		System.out.println(t);
		System.out.println("oa插入-----------");
		this.oaService.insert(n);
		System.out.println("emrp插入结束-----------");
		return true;
	}
}
