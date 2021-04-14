package com.itheruan.domain;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "commodity")
@Data
public class Commodity{
	@Id
	private Integer id;
	private String kind;
	private Integer model;
	private Integer money;
}

