package com.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import com.beans.MemberBean;



public class MemberBeanDao {
	JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public MemberBean getMemberBean(int memberId) {
		String sql = "select * from memberinfo where m_id =?";
		return jdbcTemplate.execute(sql,new PreparedStatementCallback<MemberBean>() {

			@Override
			public MemberBean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				MemberBean mb =new MemberBean();
				ps.setInt(1, memberId);
				ps.execute();
				ResultSet rs=ps.getResultSet();
				while(rs.next()) {
					mb.setAccount(rs.getString(2));
					mb.setPassword(rs.getString(3));
					mb.setName(rs.getString(4));
					mb.setBirthday(rs.getDate(5));
					mb.setEmail(rs.getString(6));
					mb.setPhone(rs.getString(7));
					mb.setAddress(rs.getString("m_address"));
				}
				return mb;
			}
		});
	}
	
	public Boolean editMemberInfo(String name,Date birth,String email,String phone,String address,int uid) {
		String sql = "update memberInfo set m_name=?,m_birth=?,m_email=?,m_phone=?,m_address=? where m_id=?";
		return jdbcTemplate.execute(sql,new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ps.setString(1,name);
				ps.setDate(2,birth);
				ps.setString(3,email);
				ps.setString(4, phone);
				ps.setString(5,address);
				ps.setInt(6,uid);
				return ps.execute();
			}

		});
		
	}

}
