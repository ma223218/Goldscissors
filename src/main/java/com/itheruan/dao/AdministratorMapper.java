package com.itheruan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.itheruan.domain.Administrator;

import tk.mybatis.mapper.common.Mapper;

public interface AdministratorMapper extends Mapper<Administrator> {
	
	@Select("SELECT * FROM administrator")
	List<Administrator> findAllAdministratorMapper();

	
}
