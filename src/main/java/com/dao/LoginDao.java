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
	public int getMemberId(String account,String password) {
		String sql="select m_id from memberinfo where m_account = ? and m_password = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class,account,password);
	}
	
	public String getMemberName(String account,String password) {
		String sql="select m_name from memberinfo where m_account = ? and m_password = ?";
		return jdbcTemplate.queryForObject(sql, String.class,account,password);
	}
	
	

}
