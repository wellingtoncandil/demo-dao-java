package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	public static void main(String[] args) {

		@SuppressWarnings("unused")
		SellerDao sellerDao = DaoFactory.createSellerDao();// desta forma o programa não conhece a implementação
															// (conhece somente a interface)
		// também é uma forma de se fazer uma injeção de dependencia sem deixar
		// explicita a implementação

		System.out.println("=== TEST 1: seller findById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);

		System.out.println("=== TEST 2: seller findByDepartment ===");
		List<Seller> sellerList = sellerDao.findByDepartment(new Department(1, null));
		
		if (sellerList == null) {
			System.out.println("Departamento inexistente!");
		} else {
			for (Seller s : sellerList) {
				System.out.println(s);
			}

		}
	}
}