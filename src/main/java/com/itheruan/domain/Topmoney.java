package com.itheruan.domain;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "topmoney")
@Data
public class Topmoney{
	@Id
	private Integer id;
	private Integer money;
}

