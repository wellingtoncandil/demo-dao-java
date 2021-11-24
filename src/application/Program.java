package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	public static void main(String[] args) {
		
		Department dep = new Department(2, "books");
		System.out.println(dep);
		
		Seller seller = new Seller(2, "Well", "wcandil@gmail", new Date(), 4500.0, dep);
		
		@SuppressWarnings("unused")
		SellerDao sellerDao = DaoFactory.createSellerDao();//desta forma o programa n�o conhece a implementa��o (conhece somente a interface)
		//tamb�m � uma forma de se fazer uma inje��o de dependencia sem deixar explicita a implementa��o
		System.out.println(seller);
		
		Seller seller2 = sellerDao.findById(3);
		
		System.out.println(seller2);
	}

}
