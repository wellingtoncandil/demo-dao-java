package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		@SuppressWarnings("unused")
		SellerDao sellerDao = DaoFactory.createSellerDao();// desta forma o programa não conhece a implementação
															// (conhece somente a interface)
		// também é uma forma de se fazer uma injeção de dependencia sem deixar
		// explicita a implementação

		System.out.println("=== TEST 1: seller findById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);

		System.out.println("\n=== TEST 2: seller findByDepartment ===");
		Department department = new Department(2, null);
		List<Seller> sellerList = sellerDao.findByDepartment(department);
		
		if (sellerList == null) {
			System.out.println("Departamento inexistente!");
		} else {
			for (Seller s : sellerList) {
				System.out.println(s);
			}

		}
		
		System.out.println("\n=== TEST 3: seller findAll ===");
		sellerList = sellerDao.findAll();
		for (Seller s : sellerList) {
			System.out.println(s);
		}
		
		System.out.println("\n=== TEST 4: seller insert ===");
		Date date = new Date();
		date = sdf.parse("13/12/1991");
		Seller seller2 = new Seller(null, "Well", "well@gmail.com", date, 3800.0, department);
		//sellerDao.insert(seller2);
		System.out.println("Inserted! New seller id = " + seller2.getId());
		
		System.out.println("\n=== TEST 5: seller update ===");
		seller = sellerDao.findById(12);
		seller.setName("Chris");
		sellerDao.update(seller);
		System.out.println("Update completed!");
		
		System.out.println("\n=== TEST 5: seller delete ===");
		System.out.println("Enter id for delete test: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("User deleted");
		
		sc.close();
		
	}
}








