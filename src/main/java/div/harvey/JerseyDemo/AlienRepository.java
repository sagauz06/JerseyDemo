package div.harvey.JerseyDemo;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class AlienRepository {
//	List<Alien> aliens;

	Connection con = null;

	public AlienRepository() {
//		aliens = new ArrayList<>();
//
//		Alien a1 = new Alien();
//		a1.setId(101);
//		a1.setName("Navin");
//		a1.setPoint(60);
//
//		Alien a2 = new Alien();
//		a2.setId(102);
//		a2.setName("Arati");
//		a2.setPoint(70);
//
//		aliens.add(a1);
//		aliens.add(a2);
		String url = "jdbc:mysql://192.168.1.103:3306/restdb";//localhost
		String username = "root";
		String password = "555";
		try {
			// Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public List<Alien> getAliens() {
//		return aliens;
		List<Alien> aliens = new ArrayList<>();
		String sql = "select * from alien";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoint(rs.getInt(3));

				aliens.add(a);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return aliens;
	}

	public Alien getAlien(int id) {
//		for (Alien a : aliens) {
//			if (a.getId() == id)
//				return a;
//		}
//		return null;
//		return new Alien();
		String sql = "select * from alien where id=" + id;
		Alien a = new Alien();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoint(rs.getInt(3));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return a;
	}

	public void create(Alien a1) {
		// TODO Auto-generated method stub
//		aliens.add(a1);
		String sql = "insert into alien values (?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, a1.getId());
			st.setString(2, a1.getName());
			st.setInt(3, a1.getPoint());
			st.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void update(Alien a1) {
		String sql = "update alien set name=?, points=? where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(3, a1.getId());
			st.setString(1, a1.getName());
			st.setInt(2, a1.getPoint());
			st.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from alien where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
