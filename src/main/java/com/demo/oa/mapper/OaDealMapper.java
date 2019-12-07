package com.demo.oa.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @date 2019年6月14日
 * @author lihui
 */
@Mapper
public interface OaDealMapper {

	boolean insert(String name);

	List<Map<String, Object>> select();

}
