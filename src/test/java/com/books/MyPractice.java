package com.books;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.sql.Statement;
import java.time.LocalDate;

public class MyPractice {

	public static void main(String[] args) throws Exception
	{
		//int active=1;
		String releasedDate="2020-01-20";
		//int inactive=0;
		String movieName="hero";
		LocalDate releasedDate1=LocalDate.parse(releasedDate);
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","oracle");
		System.out.println(connection);
		//Statement stmt=connection.createStatement();
		//String sql="update movies set active="+inactive+"where movie_name='"+movieName+"'";
		String sql="update movies set released_date=? where movie_name=?";
		PreparedStatement pst=connection.prepareStatement(sql);
		//pst.setString(1, releasedDate);
		Date rd=Date.valueOf(releasedDate1);
		pst.setDate(1, rd);
		pst.setString(2, movieName);
		
		//ResultSet rs=pst.executeQuery();
		int row=pst.executeUpdate();
		System.out.println(row);

		System.out.println(sql);
	}
}

/*{
	//BookDetails b1=new BookDetails();
			//BookDetailsDAOImpl bi1=new BookDetailsDAOImpl();
			//bi1.insertBookDetails(1,"Harry Potter","J K Rowling","Bubble rummer",1,"Fiction","English");
			//bi1.displayBooks();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","oracle");
			System.out.println(connection);
			Statement stmt=connection.createStatement();
			//String sql="insert into stock_room(book_stock_id,book_id,total_quantity)values(book_stock_id_seq.nextval,7,50)";
			//String sql="update stock_room set total_quantity=100 where book_id=4";
			//String sql="delete from stock_room where book_stock_id=5";
			
			//System.out.println(sql);
			//int row=stmt.executeUpdate(sql);
			//System.out.println(row);
			String sql="select book_name from book";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) 
			{
				String username=rs.getString("book_name");
				System.out.println(username);
			}
			System.out.println(sql);

}*/