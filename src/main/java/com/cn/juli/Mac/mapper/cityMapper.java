package com.cn.juli.Mac.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cn.juli.Mac.model.City;

@Mapper
public interface cityMapper {
	/**
	 * 按城市首字母排序，获取所有城市
	 * 
	 * @return
	 */
	List<City> getCityList();
}
