package com.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.TrService;
import com.demo.util.EDateUtil;

/**
 * 
 * @date 2019年6月14日
 * @author lihui
 */
@RestController
public class HController {

	@Autowired
	TrService trService;

	@RequestMapping("/ser")
	public void ser() throws Exception {
		this.trService.serviceT("有事务控制" + EDateUtil.getStringByDate(new Date(), EDateUtil.PATTERN_YYYY_DD_MM));
	}

	@RequestMapping("/sert")
	public void sert() throws Exception {
		this.trService.serviceNT("没有事务控制" + EDateUtil.getStringByDate(new Date(), EDateUtil.PATTERN_YYYY_DD_MM));
	}
}
