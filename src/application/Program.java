package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {
	public static void main(String[] args) {
		
		Department dep = new Department(2, "books");
		System.out.println(dep);
		
		Seller seller = new Seller(2, "Well", "wcandil@gmail", new Date(), 4500.0, dep);
		
		System.out.println(seller);
		
	}

}
