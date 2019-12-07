package com.demo.oa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.oa.mapper.OaDealMapper;

/**
 * 
 * @date 2019年6月14日
 * @author lihui
 */
@Service
public class OaService {

	@Autowired
	OaDealMapper oaDealMapper;

	public boolean insert(String name) throws Exception{
		this.oaDealMapper.insert(name);
		return true;
	}

}
