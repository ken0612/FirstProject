package com.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import com.beans.MemberBean;

public class SignUpDao {
	 JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	public Boolean save(MemberBean mb) {
		String sql ="insert into memberinfo (m_account, m_password,m_name,m_birth, m_email, m_phone) values(?,?,?,?,?,?)";
		return jdbcTemplate.execute(sql,new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ps.setString(1,mb.getAccount());
				ps.setString(2,mb.getPassword());
				ps.setString(3,mb.getName());
				ps.setDate(4,mb.getBirthday());
				ps.setString(5,mb.getEmail());
				ps.setString(6,mb.getPhone());
				return ps.execute();
			}
		});
		
	}
	
	
	
	
	

}
