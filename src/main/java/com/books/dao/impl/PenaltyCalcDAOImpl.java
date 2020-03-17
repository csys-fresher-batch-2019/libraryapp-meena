package com.books.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.books.util.ConnectionUtil;
import com.books.dto.BookCount;
import com.books.dto.CalcCard;
import com.books.dto.CategorySettings;
import com.books.dto.LanguageSettings;
import com.books.exception.DbException;
import com.books.model.PenaltyCalc;
import com.books.dao.PenaltyCalcDAO;
import com.books.logger.Logger;

public class PenaltyCalcDAOImpl implements PenaltyCalcDAO {
	private static final Logger log = Logger.getInstance();

	/**
	 * Used to calculate the fine amount for user.
	 */
	public void findFineAmount(int bookId2, int userId2) throws DbException {

		String sql = "update fine_calc set fine_amount=FUNCTION3( book_id, user_id) where status='Issued'and book_id=? and user_id=?";
		log.getInput(sql);
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement stmt = con.prepareStatement(sql);) {
			stmt.setInt(1, bookId2);
			stmt.setInt(2, userId2);
			int row = stmt.executeUpdate();
			log.getInput(row);
		} catch (SQLException e) {
			throw new DbException("Unable to update");
		}
	}

	/**
	 * Used to display all the book issued records.
	 */
	public List<PenaltyCalc> findAllFineDetails() throws DbException {
		String sql = "select *from fine_calc";
		List<PenaltyCalc> list = new ArrayList<PenaltyCalc>();
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					int itemId = rs.getInt("item_id");
					int bookId = rs.getInt("book_id");
					int userId = rs.getInt("user_id");
					LocalDate issuedDate = LocalDate.parse(rs.getDate("issued_date") + "");
					LocalDate dueDate = LocalDate.parse(rs.getDate("due_date") + "");
					LocalDate returnedDate = LocalDate.parse(rs.getDate("returned_date") + "");
					int fineAmount = rs.getInt("fine_amount");
					String status = rs.getString("status");

					PenaltyCalc bd = new PenaltyCalc(itemId, bookId, userId, issuedDate, dueDate, returnedDate,
							fineAmount, status);
					list.add(bd);
				}
			}
		} catch (SQLException e) {
			throw new DbException("Invalid select");
		} catch (DbException e) {
			throw new DbException("Connection error");
		}
		return list;
	}

	/**
	 * Used to update the due date for each user.
	 */
	public int updateDueDate(int bookId2, int userId2) throws DbException {
		int row = 0;
		String sql = "update fine_calc set due_date=(issued_date+(select days from duedate))where book_id=? and user_id=?";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement stmt = con.prepareStatement(sql);) {
			log.getInput(sql);

			stmt.setInt(1, bookId2);
			stmt.setInt(2, userId2);
			row = stmt.executeUpdate();
			con.close();

		} catch (SQLException e) {
			throw new DbException("Unable to update");
		}
		return row;
	}

	/**
	 * Used to set a book limit.
	 */
	public int saveBookLimit(int count) throws DbException {
		String sql = "update allocated set allocated_count=?";
		int row = 0;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			log.getInput(sql);
			pst.setInt(1, count);
			row = pst.executeUpdate();
			log.getInput(row + " row updated");
		} catch (SQLException e) {
			throw new DbException("Unable to update");
		}
		return row;
	}

	/**
	 * Used to set a penalty.
	 */
	public int savePenality(int amount) throws DbException {

		int row = 0;
		String sql = "update fine_table set fine_amount=?";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			log.getInput(sql);
			pst.setInt(1, amount);
			row = pst.executeUpdate();
			log.getInput(row + " row updated");
		} catch (SQLException e) {
			throw new DbException("Unable to update");
		}
		return row;
	}

	/**
	 * Used to set due date count.
	 */
	public int saveDueDays(int days) throws DbException {
		String sql = "update duedate set days=?";
		int row = 0;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			log.getInput(sql);
			pst.setInt(1, days);
			row = pst.executeUpdate();
			log.getInput(row + " row updated");
		} catch (SQLException e) {
			throw new DbException("Unable to update");
		}
		return row;
	}

	/**
	 * Used to display the books Count
	 */
	public List<BookCount> findBybooksCount() throws DbException {
		String sql = "select n.isbn_no,count(*) as cnt from fine_calc k,stock_room n where k.book_id=n.book_id group by n.isbn_no";
		List<BookCount> list = new ArrayList<BookCount>();
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				int isbnno = rs.getInt("isbn_no");
				int count = rs.getInt("cnt");
				BookCount bd = new BookCount(isbnno, count);
				list.add(bd);
			}
		} catch (SQLException e) {
			throw new DbException("Invalid select");
		}

		return list;

	}

	/**
	 * Used to list the remaining and taken books count for all the users to the
	 * admin .
	 */
	public List<CalcCard> findAlluserCardCount() throws DbException {
		String sql = "select user_id,count2(user_id)as taken_books, (case when count2(user_id)<=(select allocated_count from allocated) then((select allocated_count from allocated)-count2(user_id) )else 0 end)as remaining from users group by user_id";
		List<CalcCard> list = new ArrayList<CalcCard>();
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);
				ResultSet rs = pst.executeQuery(sql);) {
			while (rs.next()) {
				int userId = rs.getInt("user_id");
				int takenBooks = rs.getInt("taken_books");
				int remaining = rs.getInt("remaining");

				CalcCard k = new CalcCard(userId, takenBooks, remaining);
				list.add(k);
			}
		} catch (SQLException e) {
			throw new DbException("Invalid Select");
		}
		return list;
	}

	/**
	 * Used to insert the return status in the database.
	 * 
	 * @throws SQLException
	 */
	public int updateReturnStatus(int bookId, int userId, Date returnedDate) throws DbException {
		int row = 0;
		String sql = "update fine_calc set returned_date=?,status='Returned', fine_amount=fine_amount-fine_amount where book_id=? and user_id=? and status='Issued'";
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setDate(1, returnedDate);
			pst.setInt(2, bookId);
			pst.setInt(3, userId);
			row = pst.executeUpdate();
			log.getInput(row + " row updated");
		} catch (SQLException e) {
			throw new DbException("Unable to update");
		}
		return row;
	}

	/**
	 * Used to insert the issued details in the database.
	 */
	public int saveUserBookDetails(int bookId, int userId, Date issuedDate) throws DbException {
		int row = 0;
		String str1 = "select isbn_no  from stock_room sr,fine_calc fc where sr.book_id = fc.book_id and user_id=? and isbn_no = (select isbn_no  from stock_room where book_id =?)";
		String sql = "insert into fine_calc(item_id,book_id,user_id,issued_date)values(item_id_seq.nextval,?,?,?)";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst1 = con.prepareStatement(str1);) {
			pst1.setInt(1, userId);
			pst1.setInt(2, bookId);
			ResultSet rs = pst1.executeQuery();
			if (rs.next()) {
				log.getInput("Already taken");
			} else {

				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, bookId);
				pst.setInt(2, userId);
				pst.setDate(3, issuedDate);
				row = pst.executeUpdate();
				log.getInput(row + " row updated");
			}
		} catch (SQLException e) {
			throw new DbException("Unable to insert");
		}
		return row;

	}

	/**
	 * Used to insert a new language in the library.
	 */
	public int saveNewLanguage(String language) throws DbException {
		int row = 0;
		String sql = "insert into languages(languages) values(?)";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, language);
			row = pst.executeUpdate();
			log.getInput(row + " row inserted");
		} catch (SQLException e) {
			throw new DbException("Unable to insert");
		}
		return row;
	}

	public int deleteLanguage(String language1) throws DbException {
		String sql = "update languages set active=0 where languages=?";
		int row = 0;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, language1);
			row = pst.executeUpdate();
			log.getInput(row + " row deleted");
		} catch (SQLException e) {
			throw new DbException("Unable to update");
		}
		return row;
	}

	/**
	 * Used to display all the languages in the database.
	 */
	public List<LanguageSettings> findAllLanguages() throws DbException {
		List<LanguageSettings> list = new ArrayList<LanguageSettings>();
		String sql = "select * from languages where active=1";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery(sql)) {
			while (rs.next()) {
				String language = rs.getString("languages");
				LanguageSettings k = new LanguageSettings(language);
				list.add(k);
			}

		} catch (SQLException e) {
			throw new DbException("Invalid Select");
		}

		return list;
	}

	/**
	 * Used to insert the new category.
	 */
	public int saveNewCategory(String category) throws DbException {
		String sql = "insert into category(categories)values(?)";
		int row = 0;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, category);
			row = pst.executeUpdate();
			log.getInput(row + " row inserted");
		} catch (SQLException e) {
			throw new DbException("Unable to insert");
		}
		return row;
	}

	/**
	 * Used to delete category.
	 */
	public int deteleCategory(String category1) throws DbException {
		int row = 0;
		String sql = "update category set active=0 where categories=?";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, category1);
			row = pst.executeUpdate();
			log.getInput(row + " row deleted");
		} catch (SQLException e) {
			throw new DbException("Unable to update");
		}
		return row;
	}

	/**
	 * Used to display all the categories.
	 */
	public List<CategorySettings> findAllCategories() throws DbException {
		List<CategorySettings> list = new ArrayList<CategorySettings>();
		String sql = "select * from category where active=1";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery(sql);) {
			while (rs.next()) {
				String category = rs.getString("categories");
				CategorySettings d = new CategorySettings(category);
				list.add(d);
			}
		} catch (SQLException e) {
			throw new DbException("Invalid select");
		}
		return list;
	}

	/**
	 * Used to update due date for the total records in the database.
	 */
	@Override
	public int updateDueDateAll() throws DbException {
		int row = 0;
		String sql = "update fine_calc set due_date=(issued_date+(select days from duedate))";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement stmt = con.prepareStatement(sql);) {
			log.getInput(sql);
			row = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DbException("Unable to update");
		}
		return row;
	}

	/**
	 * Used to update fine amount for all the records.
	 */
	@Override
	public int updateFineAll() throws DbException {
		String sql = "update fine_calc set fine_amount=FUNCTION3( book_id, user_id) where status='Issued'";
		log.getInput(sql);
		int row = 0;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {

			row = pst.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DbException("Unable to update");
		}
		return row;
	}

	/**
	 * Used to update the due date remaining days in the table.
	 */
	@Override
	public int updatePopup(int popup) throws DbException {
		String sql = "update popup set popup=?";
		int row = 0;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, popup);
			row = pst.executeUpdate();
		} catch (SQLException e) {
			throw new DbException("Unable to update");
		}
		return row;
	}
}