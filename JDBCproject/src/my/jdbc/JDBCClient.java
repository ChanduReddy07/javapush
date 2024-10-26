package my.jdbc;

import java.sql.*;
import java.util.*;

public class JDBCClient {
	public static void insertWithDynamicValues(Connection con,int id,String fname, String lname)throws SQLException {
		String query="insert into users values(?,?,?)";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(1, id);
		ps.setString(2, fname);
		ps.setString(3, lname);
		ps.execute();
		System.out.println("Sucessfully inserted **************");
	}
	public static void deleteDataWithDynamicValue(Connection con,int id) throws SQLException {
		String query="delete from users where id = '"+id+"'";
		PreparedStatement ps=con.prepareStatement(query);
		ps.execute();
		
	}
	public static void updateDataWithDynamicValue(Connection con,int id,String lname) throws SQLException {
		String query="update users set last_name='varma' where id = '"+id+"'";
		PreparedStatement ps=con.prepareStatement(query);
		ps.execute();
	}
	
	public static void fetchDataWithDynamicValue(Connection con,String lname) throws SQLException {
		String query="select * from users where last_name = '"+lname+"'";
		PreparedStatement ps=con.prepareStatement(query);
		ResultSet rs=ps.executeQuery(query);
		while(rs.next()) {
			int id=rs.getInt(1);
			String fnam=rs.getString(2);
			String lnam=rs.getString(3);
			System.out.println(id+" "+fnam+" "+lnam);
		}
	}
	
	public static void insertDataObj(Connection con,Users obj) throws SQLException {
		String query="insert into users values(?,?,?)";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(1,obj.getId());
		ps.setString(2, obj.getFirstName());
		ps.setString(3, obj.getLastName());
		ps.execute();
		System.out.println("inseredDataUSingObject Sucessfully");
	}
	public static Users fetchAsObjectWithId(Connection con,int id) throws SQLException {
		String query="Select * from users where id = '"+id+"'";
		PreparedStatement ps=con.prepareStatement(query);
		ResultSet rs=ps.executeQuery(query);
		Users u = null;
		while(rs.next()) {
			u=new Users(rs.getInt(1), rs.getString(2), rs.getString(2));
		}
		return u;
	}
	public static List<Users> fetchObjects(Connection con) throws SQLException{
		String query="Select * from users where last_name = 'varma'";
		PreparedStatement ps=con.prepareStatement(query);
		ResultSet rs=ps.executeQuery(query);
		List<Users> l=new LinkedList<>();
		while(rs.next()) {
			Users u=new Users(rs.getInt(1), rs.getString(2), rs.getString(3));
			l.add(u);
		}
		return l;
	}

	public static void main(String[] args) throws SQLException {
		//Load Driver
//		Class.forName("com.mysql.cj.jdbc.Driver"); //statement not required in current version
		String url="jdbc:mysql://localhost:3306/tribe_project";
		String unam="root";
		String pass="Sbaby@123";
		Connection conn=DriverManager.getConnection(url, unam, pass);
		System.out.println("Sucessfully Connected");
		
		Statement st=null;
		System.out.println(fetchObjects(conn));
//		readData(st,conn);
//		System.out.println(fetchAsObjectWithId(conn,100));
		
		
//		insertData(st, conn);
//		DeleteData(st, conn);
		
//		insertWithDynamicValues(conn, 800, "boruto", "kumar");
//		System.out.println("Inserted Sucessfully \n \n");
		
//		fetchDataWithDynamicValue(conn, "varma");
//		System.out.println("fetched Sucessfully \n \n");
		
//		deleteDataWithDynamicValue(conn, 700);
//		System.out.println("deleted Sucessfully \n \n");
		
//		updateDataWithDynamicValue(conn, 100, "varma");
//		System.out.println("Updated Sucessfullt /n /n");
		
//		Users u=new Users(900,"jagan","mohan");
//		insertDataObj(conn, u);
		
		System.out.println("\n \n");
		readData(st, conn);
		
	}
	public static void readData(Statement stmt,Connection con) throws SQLException {
		String query="select * from users";  //Tables_in_tribe_project
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(query);
		while(rs.next()) {
			int id=rs.getInt(1);
			String fname=rs.getString(2);
			String lname=rs.getString(3);
			
			System.out.println(id+"  "+fname+"  "+lname);
		}
	}
	//not dynamic
	public static void insertData(Statement stmt,Connection con)throws SQLException {
		String query="insert into users values(400,'pprincess','Hiinata')";
		stmt=con.createStatement();
		stmt.execute(query);
	}
	public static void DeleteData(Statement stmt,Connection con)throws SQLException {
		String query="delete from users where id=400";
		stmt=con.createStatement();
		stmt.execute(query);
	}
	
	
	

}
