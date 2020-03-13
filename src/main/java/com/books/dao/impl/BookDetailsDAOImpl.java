package com.books.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.books.util.ConnectionUtil;
import com.books.model.BookDetails;

import com.books.dao.BookDetailsDAO;
import com.books.exception.DbException;
import com.books.logger.Logger;

public class BookDetailsDAOImpl implements BookDetailsDAO {
	private static final Logger log = Logger.getInstance();

	/**
	 * Used for display all the books.
	 */
	public List<BookDetails> findAllBooks() throws DbException {

		String sqlQuery = "select * from book where active=1 order by isbn_no";
		List<BookDetails> list = new ArrayList<BookDetails>();

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(sqlQuery)) {
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					int isbnNo = rs.getInt("isbn_no");
					String bookName = rs.getString("book_name");
					String authorName = rs.getString("author_name");
					String pub = rs.getString("publisher");
					int ver = rs.getInt("version_no");
					String category = rs.getString("categories");
					String language = rs.getString("languages");
					int totalStock = rs.getInt("total_stocks");
					int active = rs.getInt("active");

					BookDetails bd = new BookDetails(isbnNo, bookName, authorName, pub, ver, category, language,
							totalStock, active);
					list.add(bd);
				}
			} catch (SQLException e) {
				throw new DbException("Invalid select");
			}
		} catch (SQLException e) {
			throw new DbException("Connection Error with Database");
		}

		return list;
	}

	/**
	 * Used to insert a new book into the database.
	 */
	public int saveBookDetails(BookDetails bookDetail) throws DbException {
		int row = 0;
		String sql = ("insert into book(isbn_no,book_name,author_name,publisher,version_no,categories,languages)values(?,?,?,?,?,?,?)");
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setInt(1, bookDetail.getIsbnNo());
			pst.setString(2, bookDetail.getBookName());
			pst.setString(3, bookDetail.getAuthorName());
			pst.setString(4, bookDetail.getPublisher());
			pst.setInt(5, bookDetail.getVersion());
			pst.setString(6, bookDetail.getCategories());
			pst.setString(7, bookDetail.getLanguages());
			row = pst.executeUpdate();
			log.getInput(row + " row inserted");
			log.getInput(sql);
		} catch (SQLException e) {
			throw new DbException("Unable to insert");
		}
		return row;
	}

	/**
	 * Used to remove the book from the list
	 */
	public int deleteBookDetails(int isbnNo) throws DbException {
		int row = 0;
		String sql = ("update book set active=0 where isbn_no=?");
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setInt(1, isbnNo);
			try (ResultSet rs = pst.executeQuery()) {

				row = pst.executeUpdate();
				log.getInput(row + " row deleted");
			}
		} catch (SQLException e) {
			throw new DbException("Unable to update");
		}
		return row;
	}

	/**
	 * Used to find the total books in the library.
	 */
	public int findTotalBooks() throws DbException {
		int count = 0;
		String sql = "select count(isbn_no)as total_books from book where active=1";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();) {
			if (rs.next()) {
				count = rs.getInt("total_books");
			}
		} catch (SQLException e) {
			throw new DbException("Invalid Select");
		}
		return count;
	}

	/**
	 * Used to check the admin login.
	 */
	@Override
	public int findByAdmin(String admin, String password) throws DbException {
		int status = 0;
		String sql = "select * from admin where admin_name=?and admin_pwd=?";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, admin);
			pst.setString(2, password);
			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {
					status = 1;
					String adminName = rs.getString("admin_name");
					log.getInput("Welcome " + adminName);
				} else {
					log.getInput("Invalid Login");
				}
			}
		} catch (SQLException e) {
			throw new DbException("Invalid select");
		}
		return status;
	}

	/**
	 * Used to update the book table to get the total stock of the particular book.
	 */
	@Override
	public int updateTotalStock() throws DbException {
		String sql = "update book set total_stocks=ISBN_COUNT(isbn_no)";
		log.getInput(sql);
		int row = 0;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			row = pst.executeUpdate();
			log.getInput(row + "updated");

		} catch (SQLException e) {
			throw new DbException("Unable to update");
		}

		return row;
	}
}