package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import com.beans.ProductBean;

public class ProductManageDao {
	
	JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	
	public List<ProductBean> getAllProductBeans(){
		String sql="select * from productinfo";
		return jdbcTemplate.execute(sql,new PreparedStatementCallback<List<ProductBean>>() {

			@Override
			public List<ProductBean> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ResultSet rs = ps.executeQuery();
				List<ProductBean> list = new ArrayList<>();
				while(rs.next()) {
					ProductBean pb = new ProductBean();
					pb.setPid(rs.getInt(1));
					pb.setPname(rs.getString(2));
					pb.setPprice(rs.getInt(3));
					pb.setPdesc(rs.getString(4));
					pb.setPurl(rs.getString(5));
					pb.setPavalible(rs.getBoolean(6));
					list.add(pb);
				}
				return list;
			}
			
		});
		
	}
	
	public void addProduct(String pname,int pprice,String pdesc,String purl) {
		String sql="insert into productinfo(p_name,p_price,p_desc,p_imageurl) values(?,?,?,?)";
		jdbcTemplate.execute(sql,new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ps.setString(1, pname);
				ps.setInt(2, pprice);
				ps.setString(3,pdesc);
				ps.setString(4,purl);
				return ps.execute();
			}
		});
		
	}
	
	public Boolean delProduct(int pid) {
		String sql= "delete from productinfo where p_id=?";
		return jdbcTemplate.execute(sql,new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ps.setInt(1, pid);
				return ps.execute();
			}
		});

	}
	
	
	public ProductBean getProductBean(int pid) {
		String sql="select * from productinfo where p_id = ?";
		return jdbcTemplate.execute(sql,new PreparedStatementCallback<ProductBean>() {

			@Override
			public ProductBean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ps.setInt(1, pid);
				ResultSet rs = ps.executeQuery();
				rs.next();
				ProductBean pb= new ProductBean();
				pb.setPid(rs.getInt(1));
				pb.setPname(rs.getString(2));
				pb.setPprice(rs.getInt(3));
				pb.setPdesc(rs.getString(4));
				pb.setPurl(rs.getString(5));
				pb.setPavalible(rs.getBoolean(6));
				
				return pb;
			}
		});
	}
	
	public Boolean editProduct(String pname,int price,String pdesc,String purl,int pid) {
		String sql ="update productinfo set p_name=?,p_price=?,p_desc=?,p_imageurl=? where p_id=?";
		return jdbcTemplate.execute(sql,new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ps.setString(1, pname);
				ps.setInt(2, price);
				ps.setString(3, pdesc);
				ps.setString(4, purl);
				ps.setInt(5, pid);
				return ps.execute();
			}
			
		});
	}
	
	
	
	public Object getProductName(int pid) {
		String sql ="select p_name from productinfo where p_id=?";
		return jdbcTemplate.queryForObject(sql, String.class,pid);
	}

}
