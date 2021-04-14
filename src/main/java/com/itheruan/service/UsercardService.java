package com.itheruan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheruan.dao.UsercardMapper;
import com.itheruan.domain.Usercard;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class UsercardService {

	@Autowired
	private UsercardMapper usercardMapper;
	
	
	/**
	 * 修改用户余额
	 * @param usercard2
	 */
	public void updete(Usercard usercard2) {
		System.out.println("剩余金额"+usercard2.getMoney());
		usercardMapper.updateByPrimaryKeySelective(usercard2);
		
		
	}
	
	/**
	 * 查询一条
	 * @param usercard
	 * @return
	 */
	public Usercard findOne(Usercard usercard) {
		
		return usercardMapper.selectOne(usercard);
	}

	/**
	 * 根据会员类型查询
	 * @param type
	 * @return
	 */
	public List<Usercard> findByType(int type) {
		Example example = new Example(Usercard.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("type", type);
		
		return usercardMapper.selectByExample(example);
	}

	
	
}
