package com.itheruan.domain;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "usercard")
@Data
public class Usercard{
	@Id
	private Integer id;
	private Integer phone;
	private Integer type;
	private Double money;
	private String time;
}

