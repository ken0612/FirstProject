import org.springframework.beans.factory.annotation.Autowired;

import com.beans.ProductBean;
import com.dao.ProductManageDao;

public class Test {
	@Autowired
	static
	ProductManageDao tt;

	
	public static void main(String args[]) {
		
		try {
			System.out.println(tt.getProductName(31));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
