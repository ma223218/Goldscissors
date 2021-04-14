package com.itheruan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheruan.dao.MoneyrecordMapper;
import com.itheruan.domain.Moneyrecord;

@Service
public class MoneyrecordService {
	@Autowired
	private MoneyrecordMapper moneyrecordMapper;
	/**
	 * 查询所有
	 * @return
	 */
	public List<Moneyrecord> findAll() {
		
		return moneyrecordMapper.selectAll();
	}
	/**
	 * 添加一条消费记录
	 * @param moneyrecord
	 */
	public void add(Moneyrecord moneyrecord) {
		
		moneyrecordMapper.insert(moneyrecord);
	}
	
}
