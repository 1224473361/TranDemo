package com.demo.emrp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.emrp.mapper.EmrpDealMapper;

/**
 * 
 * @date 2019年6月14日
 * @author lihui
 */
@Service
public class EmrpService {

	@Autowired
	EmrpDealMapper emrpDealMapper;

	public boolean insert(String name) throws Exception {
		return this.emrpDealMapper.insert(name);
	}
}
