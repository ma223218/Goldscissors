package com.itheruan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheruan.dao.AdministratorMapper;
import com.itheruan.domain.Administrator;

@Service
public class AdministratorService {

	@Autowired
	private AdministratorMapper administratorMapper;
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<Administrator> findOne(Administrator administrator) {
        return administratorMapper.selectAll();
		
	}
	/**
	 * 查询一条
	 * @return
	 */
	public Administrator findCS(Administrator administrator) {
		// TODO Auto-generated method stub
		return administratorMapper.selectOne(administrator);
	}
	 
}
