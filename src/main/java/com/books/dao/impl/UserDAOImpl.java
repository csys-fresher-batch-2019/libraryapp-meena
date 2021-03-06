package com.books.dao.impl;

import java.sql.Connection;
import com.books.logger.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.books.util.ConnectionUtil;
import com.books.model.BookDetails;
import com.books.dto.CalcCard;
import com.books.exception.DbException;
import com.books.model.User;
import com.books.dao.UserDAO;

public class UserDAOImpl implements UserDAO {
	private static final Logger log = Logger.getInstance();

	/**
	 * Used to display the history of books to the user.
	 * 
	 * @throws SQLException
	 */
	public List<User> findAllHistory(int userId) throws DbException {
		String sql = "select book_id,issued_date,due_date,returned_date,fine_amount,status from  fine_calc where user_id=? order by issued_date desc";
		List<User> list = new ArrayList<User>();
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();
			int flag = 0;
			while (rs.next()) {
				flag = 1;
				int bookId = rs.getInt("book_id");
				LocalDate issuedDate = LocalDate.parse(rs.getDate("issued_date") + "");
				LocalDate returnedDate = LocalDate.parse(rs.getDate("returned_date") + "");
				LocalDate dueDate = LocalDate.parse(rs.getDate("due_date") + "");
				int fineAmount = rs.getInt("fine_amount");
				String status = rs.getString("status");
				User u = new User(bookId, issuedDate, dueDate, returnedDate, fineAmount, status);
				list.add(u);

			}

			if (flag == 0) {
				log.getInput("No history");
			}
		} catch (SQLException e) {
			throw new DbException("Invalid select");
		}

		return list;

	}

	/**
	 * Used to validate the login details of the user.
	 * 
	 * @throws SQLException
	 */
	public int findByUser(String email, String password) throws DbException, SQLException {
		int uid = 0;
		String sql = "select * from users where email=?and password=?";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, email);
			pst.setString(2, password);
			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {
					uid = rs.getInt("user_id");
					String uname = rs.getString("user_name");
					log.getInput("Welcome " + uname);
				} else {
					log.getInput("Invalid Login");
				}
			} catch (SQLException e) {
				throw new DbException("Invalid select");
			}
		}

		catch (DbException e) {
			throw new DbException("Connection error");
		}
		return uid;
	}

	/**
	 * Used to display all the current books to the user.
	 */
	@Override
	public List<User> findAllCurrentBooks(int userId) throws DbException {
		String sql = "select book_id,issued_date,due_date,returned_date,fine_amount,status from  fine_calc where user_id=? and status='Issued' order by issued_date desc";
		List<User> list = new ArrayList<User>();
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();
			int flag = 0;
			while (rs.next()) {
				flag = 1;
				int bookId = rs.getInt("book_id");
				LocalDate issuedDate = LocalDate.parse(rs.getDate("issued_date") + "");
				LocalDate returnedDate = LocalDate.parse(rs.getDate("returned_date") + "");
				LocalDate dueDate = LocalDate.parse(rs.getDate("due_date") + "");
				int fineAmount = rs.getInt("fine_amount");
				String status = rs.getString("status");
				User u = new User(bookId, issuedDate, dueDate, returnedDate, fineAmount, status);
				list.add(u);
			}
			if (flag == 0) {
				log.getInput("No current Books in your account");

			}
		}

		catch (SQLException e) {
			throw new DbException("Invalid select");
		}
		return list;
	}

	/**
	 * Used to search a book by book name.
	 * 
	 * @throws SQLException
	 */
	@Override
	public List<BookDetails> findByBookName(String bookName) throws DbException, SQLException {
		List<BookDetails> list = new ArrayList<BookDetails>();
		String sql = "select *from book where lower(book_name) like ?";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, "%" + bookName + "%");
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					int isbnNo = rs.getInt("isbn_no");
					String bookName1 = rs.getString("book_name");
					String authorName = rs.getString("author_name");
					String pub = rs.getString("publisher");
					int ver = rs.getInt("version_no");
					String category = rs.getString("categories");
					String language = rs.getString("languages");
					int totalBooks = rs.getInt("total_stocks");
					int active = rs.getInt("active");
					BookDetails bd = new BookDetails(isbnNo, bookName1, authorName, pub, ver, category, language,
							totalBooks, active);
					list.add(bd);
				}
			} catch (SQLException e) {
				throw new DbException("No book Found");
			}
		} catch (DbException e) {
			throw new DbException("Connection error");
		}

		return list;

	}

	/**
	 * Used to search the book by author name.
	 */
	@Override
	public List<BookDetails> findByAuthorName(String author) throws DbException {
		List<BookDetails> list = new ArrayList<BookDetails>();
		String sql = "select *from book where lower(author_name) like ?";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();) {
			pst.setString(1, "%" + author + "%");
			while (rs.next()) {
				int isbnNo = rs.getInt("isbn_no");
				String bookName = rs.getString("book_name");
				String authorName = rs.getString("author_name");
				String pub = rs.getString("publisher");
				int ver = rs.getInt("version_no");
				String category = rs.getString("categories");
				String language = rs.getString("languages");
				int totalBooks = rs.getInt("total_stocks");
				int active = rs.getInt("active");
				BookDetails bd = new BookDetails(isbnNo, bookName, authorName, pub, ver, category, language, totalBooks,
						active);
				list.add(bd);
			}
		} catch (SQLException e) {
			throw new DbException("No book found");
		}
		return list;
	}

	/**
	 * Used to calculate and display the remaining card for the user.
	 */
	public List<CalcCard> findRemainingCard(int userId) throws DbException {
		CalcCard c = new CalcCard();
		List<CalcCard> list = new ArrayList<CalcCard>();
		String sql = "select distinct user_id,count2(user_id)as taken_books, (case when count2(user_id)<=(select allocated_count from allocated) then((select allocated_count from allocated)-count2(user_id) )else 0 end)as remaining from users where user_id=?";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, userId);
			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {
					c.setTakenBooks(rs.getInt(2));
					c.setRemaining(rs.getInt(3));
				}
				list.add(c);
			}
		} catch (SQLException e) {
			throw new DbException("Invalid select");
		}
		return list;
	}

	/**
	 * Used to change the phone number for the user.
	 */
	@Override
	public int updatePhoneNumber(int userId, long phoneNumber) throws DbException {
		String sql = "update users set ph_no=? where user_id=?";
		int row = 0;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setLong(1, phoneNumber);
			pst.setInt(2, userId);
			row = pst.executeUpdate();
		} catch (SQLException e) {
			throw new DbException("Unable to update");
		}
		return row;
	}

	/**
	 * Used to change the address for the user.
	 */
	@Override
	public int updateAddress(int userId, String address) throws DbException {
		String sql = "update users set address=? where user_id=?";
		int row = 0;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, address);
			pst.setInt(2, userId);
			row = pst.executeUpdate();
		} catch (SQLException e) {
			throw new DbException("Unable to update");
		}
		return row;
	}

	/**
	 * Used to change the password for the user.
	 */
	@Override
	public int updatePassword(int userId, String password) throws DbException {
		String sql = "update users set password=? where user_id=?";
		int row = 0;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, password);
			pst.setInt(2, userId);
			row = pst.executeUpdate();
		} catch (SQLException e) {
			throw new DbException("Unable to update");
		}
		return row;
	}

	/**
	 * Used to display the due books for the user.
	 */
	@Override
	public List<User> findDueDate(int userId) throws DbException {
		String sql = "select *from fine_calc where due_date-sysdate=(select popup from popup)and user_id=?";
		List<User> list = new ArrayList<User>();
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				while (rs.next()) {

					int bookId = rs.getInt("book_id");

					LocalDate issuedDate = LocalDate.parse(rs.getDate("issued_date") + "");
					LocalDate returnedDate = LocalDate.parse(rs.getDate("returned_date") + "");
					LocalDate dueDate = LocalDate.parse(rs.getDate("due_date") + "");
					int fineAmount = rs.getInt("fine_amount");
					String status = rs.getString("status");
					User u = new User(bookId, issuedDate, dueDate, returnedDate, fineAmount, status);
					list.add(u);
				}

			} else {
				log.getInput("No Due Books in your account");
			}

		} catch (SQLException e) {
			throw new DbException("Invalid select");
		}
		return list;

	}

	/**
	 * @throws SQLException
	 * 
	 */
	@Override
	public int findAvailable(int userId) throws DbException, SQLException {
		int status = 0;
		String sql = "select *from fine_calc where user_id=?";
		String sql1 = "select * from allocated";
		UserDAOImpl s = new UserDAOImpl();
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, userId);
			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {
					status = 1;
					s.findRemainingCard(userId);
				} else {
					try (PreparedStatement pst1 = con.prepareStatement(sql1); ResultSet rs1 = pst1.executeQuery();) {
						if (rs1.next()) {

							int remaining = rs1.getInt("allocated_count");
							log.getInput("Remaining:" + remaining);
						}
					}
				}
			} catch (SQLException e) {
				throw new DbException("Invalid select");
			}
		}
		return status;
	}

}