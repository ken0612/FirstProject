package com.dao;


import org.springframework.jdbc.core.JdbcTemplate;

public class LoginDao {
	JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Boolean loginValidate(String account ,String password) {
		String sql="select count(*) from memberinfo where m_account = ? and m_password = ?";
		int count=jdbcTemplate.queryForObject(sql, Integer.class,account,password);
		System.out.println(count);
		if(count>0) {
			return true;
		}
		return false;
	}

}
