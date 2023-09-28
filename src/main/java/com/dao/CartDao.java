package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.transaction.annotation.Transactional;

import com.beans.CartBean;
import com.beans.CartDetailBean;
import com.beans.ProductBean;

public class CartDao {
	JdbcTemplate jdbcTemplate;

	@Autowired
	ProductManageDao productManageDao;

	public void setProductManageDao(ProductManageDao productManageDao) {
		this.productManageDao = productManageDao;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public CartBean getCart(int uid) {
		String sql = "select * from cart";

		return jdbcTemplate.execute(sql, new PreparedStatementCallback<CartBean>() {

			@Override
			public CartBean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ResultSet rs = ps.executeQuery();
				CartBean cart = new CartBean();
				while (rs.next()) {
					cart.setCartId(rs.getInt("cart_id"));
					cart.setUserId(rs.getInt("user_id"));
					cart.setCreateDate(rs.getDate("create_date"));
					return cart;
				}
				return null;
			}
		});

	}

	@Transactional
	public Boolean addToCart(int pid, HttpSession session) {
		int uid = (int) session.getAttribute("loggedInUser");
		CartBean cart = getCart(uid);
		System.out.println(cart);
		String sql = "insert into cart(user_id,create_date) values(?,?) ";
		if (cart == null) {
			System.out.println("判斷成立");

			jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
				@Override
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					ps.setInt(1, uid);
					ps.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
					return ps.execute();
				}
			});
		}

		if (checkProductExist(pid, getCartId(uid))>0) {
			System.out.println("hello!!");
			sql = "update cart_detail set quantity=quantity+1 where product_id=? and cart_id=?";
			jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {

				@Override
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					// TODO Auto-generated method stub
					ps.setInt(1, pid);
					ps.setInt(2, getCartId(uid));

					return ps.execute();
				}

			});
			
		}else {
			System.out.println("hahahaha");
			sql = "insert into cart_detail (cart_id,product_id,quantity,price) values(?,?,?,?)";

			jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
				@Override
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					ProductBean pb = productManageDao.getProductBean(pid);
					ps.setInt(1, getCartId(uid));
					ps.setInt(2, pid);
					ps.setInt(3, 1);
					ps.setInt(4, pb.getPprice());
					return ps.execute();
				}

			});
		}
		return true;
	}

	
	public int getCartId(int uid) {
		String sql = "select cart_id from cart where user_id =?";
		return jdbcTemplate.execute(sql, new PreparedStatementCallback<Integer>() {
			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setInt(1, uid);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return rs.getInt("cart_id");
				} else {
					// 如果找不到匹配的 cartId，返回一个错误值，例如 -1
					return -1;
				}
			}
		});
	}

	public List<CartDetailBean> getCartDetailByUid(int uid) {
		String sql = "SELECT * FROM cart as c left JOIN cart_detail as cd ON c.cart_id = cd.cart_id left join productinfo as p ON cd.product_id =p.p_id where c.user_id=?";
		return jdbcTemplate.execute(sql, new PreparedStatementCallback<List<CartDetailBean>>() {

			@Override
			public List<CartDetailBean> doInPreparedStatement(PreparedStatement ps)
					throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ps.setInt(1, uid);
				ResultSet rs = ps.executeQuery();
				List<CartDetailBean> list = new ArrayList<CartDetailBean>();
				while (rs.next()) {
					CartDetailBean cdb = new CartDetailBean();
					cdb.setCartId(rs.getInt("cart_id"));
					cdb.setCartItemId(rs.getInt("cart_item_id"));
					cdb.setPrice(rs.getInt("price"));
					cdb.setProductId(rs.getInt("product_id"));
					cdb.setQuantity(rs.getInt("quantity"));
					cdb.setProductName(rs.getString("p_name"));
					if(cdb.getPrice()!=0) {
						list.add(cdb);						
					}
				}
				return list;
			}
		});
	}

	public Integer checkProductExist(int pid,int cartId) {
		String sql = "select count(*) from cart_detail where product_id =? and cart_id=?";
		return jdbcTemplate.queryForObject(sql, Integer.class,pid,cartId);
	}
	
	public Boolean delProductFormCart(int cartId,int pid) {
		String sql = "delete from cart_detail where cart_id=? and product_id=?";
		return jdbcTemplate.execute(sql,new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ps.setInt(1, cartId);
				ps.setInt(2,pid);
				return ps.execute();
			}
		});
		
	}
	
	public int getTotalPrice(int cartId) {
		String sql="select sum(quantity*price) as totalprice from cart_detail where cart_id=?";
		return jdbcTemplate.execute(sql,new PreparedStatementCallback<Integer>() {
			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				int totalPrice;

				ps.setInt(1,cartId);
				ps.execute();
				ResultSet rs= ps.executeQuery();
				rs.next();
				totalPrice=rs.getInt("totalprice");
				return totalPrice;
			}
		});
	
	}
	
	public Boolean editCartQuantity(int cartId,int pId,int quantity) {
		String sql ="update cart_detail set quantity=? where cart_id=? and product_id=?";
		return jdbcTemplate.execute(sql,new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ps.setInt(1,quantity);
				ps.setInt(2, cartId);
				ps.setInt(3,pId);
				return ps.execute();
			}
		});
	}
	
	public Boolean cleanCart(int cartId) {
		String sql = "delete cart,cart_detail from cart inner join cart_detail on cart.cart_id = cart_detail.cart_id where cart.cart_id=?";
		return jdbcTemplate.execute(sql,new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ps.setInt(1,cartId);
				return ps.execute();
			}
		});
	}
}
