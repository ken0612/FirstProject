package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.transaction.annotation.Transactional;

import com.beans.CartDetailBean;
import com.beans.OrderBean;
import com.beans.OrderDetailBean;

public class OrderDao {
	CartDao cartDao;
	ProductManageDao productManageDao;
	JdbcTemplate jdbcTemplate;
	
	public void setProductManageDao(ProductManageDao productManageDao) {
		this.productManageDao = productManageDao;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}




	@Transactional
	public Boolean orderSubmit(int uid) {
		int cartId = cartDao.getCartId(uid);
		createOrder(uid);
		createOrderDetail(uid);
		cartDao.cleanCart(cartId);
		return true;
		
	}
	
	public int getOid(int cartId) {
		String sql="select o_id from orders where cart_id=?";
		return jdbcTemplate.queryForObject(sql, Integer.class,cartId);
	}
	
	private Boolean createOrder(int uid) {
		String sql="insert into orders(cart_id,u_id,total_price,create_date) values(?,?,?,?)";
		return jdbcTemplate.execute(sql,new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ps.setInt(1,cartDao.getCartId(uid));
				ps.setInt(2, uid);
				ps.setInt(3, cartDao.getTotalPrice(cartDao.getCartId(uid)));
				ps.setTimestamp(4,new java.sql.Timestamp(System.currentTimeMillis()));
				
				return ps.execute();
			}
		});
	}
	
	private Boolean createOrderDetail(int uid) {
		List<CartDetailBean> list=  cartDao.getCartDetailByUid(uid);
		int oId =getOid(cartDao.getCartId(uid));
		String sql="insert into order_detail(o_id,product_id,quantity,price,product_name,product_url) values(?,?,?,?,?,?)";
		for(CartDetailBean cdb :list) {
			jdbcTemplate.execute(sql,new PreparedStatementCallback<Boolean>() {

				@Override
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					// TODO Auto-generated method stub
					ps.setInt(1,oId);
					ps.setInt(2,cdb.getProductId());
					ps.setInt(3, cdb.getQuantity());
					ps.setInt(4, cdb.getPrice());
					ps.setString(5, cdb.getProductName());
					ps.setString(6,productManageDao.getProductUrl(cdb.getProductId()));
					return ps.execute();
				}
			});
			
		}
		return true;
				
	}
	
	public List<OrderBean> getHistoryOrders(int uid){
		String sql="select * from orders where o_id =?";
		List<Integer> allOidOrderNum = getAllOrdersIdByUid(uid) ;
		List<OrderBean> orderBeans= new ArrayList<>();
		for(Integer ele:allOidOrderNum) {
			jdbcTemplate.execute(sql,new PreparedStatementCallback<Boolean>() {

				@Override
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					// TODO Auto-generated method stub
					ps.setInt(1,ele);
					ResultSet rs = ps.executeQuery();
					rs.next();
					OrderBean ob = new OrderBean();
					ob.setoId(rs.getInt("o_id"));
					ob.setTotalPrice(rs.getInt("total_price"));
					ob.setCreateDate(rs.getDate("create_date"));
					orderBeans.add(ob);
					return true;
				}
				
			});
		}
		return orderBeans;
	}
	
	public List<Integer> getAllOrdersIdByUid(int uid){
		String sql="select o_id from orders where u_id =?";
		return  jdbcTemplate.execute(sql,new PreparedStatementCallback<List<Integer>>() {

			@Override
			public List<Integer> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ps.setInt(1, uid);
				ResultSet rs =ps.executeQuery();
				List<Integer> list =new ArrayList<Integer>();
				while(rs.next()) {
					int oId=rs.getInt("o_id");
					list.add(oId);
				}
				return list;
			}
			
		});
	}
	
	public List<OrderDetailBean> getOderDetail(int orderId) {
		String sql="select * from order_detail where o_id =?";
		List<OrderDetailBean> list =new ArrayList<>();
		return jdbcTemplate.execute(sql,new PreparedStatementCallback<List<OrderDetailBean>>() {

			@Override
			public List<OrderDetailBean> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ps.setInt(1,orderId);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					OrderDetailBean orderDetailBean = new OrderDetailBean();
					orderDetailBean.setoId(orderId);
					orderDetailBean.setpId(rs.getInt("product_id"));
					orderDetailBean.setPrice(rs.getInt("price"));
					orderDetailBean.setQuantity(rs.getInt("quantity"));
					orderDetailBean.setProductName(rs.getString("product_name"));
					orderDetailBean.setProductUrl(productManageDao.getProductUrl(rs.getInt("product_id")));
					list.add(orderDetailBean);
				}
				return list;
			}
		});
				
	}
	
	
	
}
