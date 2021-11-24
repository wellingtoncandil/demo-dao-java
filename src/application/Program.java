package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	public static void main(String[] args) {
		
		
		@SuppressWarnings("unused")
		SellerDao sellerDao = DaoFactory.createSellerDao();//desta forma o programa n�o conhece a implementa��o (conhece somente a interface)
		//tamb�m � uma forma de se fazer uma inje��o de dependencia sem deixar explicita a implementa��o
		
		System.out.println("=== TEST 1: seller findById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
	}

}
