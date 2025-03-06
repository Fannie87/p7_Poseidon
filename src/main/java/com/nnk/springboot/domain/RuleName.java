package com.nnk.springboot.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class RuleName {
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String description;
	
	@NotNull
	private String json;
	
	@NotNull
	private String template;
	
	@NotNull
	private String sqlStr;
	
	@NotNull
	private String sqlPart;

	public RuleName() {
	}
	public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
		this.name = name;
		this.description = description;
		this.json = json;
		this.template = template;
		this.sqlStr = sqlStr;
		this.sqlPart = sqlPart;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getSqlStr() {
		return sqlStr;
	}

	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}

	public String getSqlPart() {
		return sqlPart;
	}

	public void setSqlPart(String sqlPart) {
		this.sqlPart = sqlPart;
	}

}
