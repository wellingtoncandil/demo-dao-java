package model.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
			 		"SELECT seller.*,department.Name as DepName "
			        + "FROM seller INNER JOIN department "
		            + "ON seller.DepartmentId = department.Id "
			        + "WHERE seller.Id = ?");

			st.setInt(1, id);// seta o valor para o "?"

			rs = st.executeQuery();
			if (rs.next()) {
				Department dep = instantiatingDepartment(rs);
				Seller seller = instantiatingSeller(rs, dep);

				return seller;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department d) {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Seller> list = new ArrayList<>();
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
			        + "FROM seller INNER JOIN department "
			        + "ON seller.DepartmentId = department.Id "
			        + "WHERE DepartmentId = ? " + "ORDER BY Name");
			
			st.setInt(1, d.getId());
			rs = st.executeQuery();
			
			Map<Integer, Department> map = new HashMap<>();//instancia um map vazio que receber� os departamento sem que haja repeti��o
			
			while(rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));//busca, atrav�s do get, em "map" algum departamento que tenha o id informado
				//rs.getInt("DepartmentId")
				if(dep == null) {
					dep = instantiatingDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
			    
				Seller seller = instantiatingSeller(rs, dep);
				list.add(seller);
			}
			
		if(list.size() != 0) {
			return list;
		}
		
		return null;
		
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
	}

	private Seller instantiatingSeller(ResultSet rs, Department dep) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setDepartment(dep);
		return seller;
	}

	private Department instantiatingDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
