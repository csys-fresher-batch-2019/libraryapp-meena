package com.books.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.books.logger.Logger;

import com.books.model.BookStockDetails;
import com.books.util.ConnectionUtil;
import com.books.dao.BookStockDetailsDAO;
import com.books.exception.DbException;

public class BookStockDetailsDAOImpl implements BookStockDetailsDAO {

	private static final Logger log = Logger.getInstance();

	/**
	 * Used to insert the new stock in the stock room.
	 */
	public int saveBookStockDetails(int isbnNo) throws DbException {
		int row = 0;
		String sql = "insert into stock_room(book_id,isbn_no)values(book_id_seq.nextval,?)";
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			pst.setInt(1, isbnNo);
			row = pst.executeUpdate();
			log.getInput(row + "row inserted");
		} catch (SQLException e) {
			throw new DbException("Unable to insert");
		}
		return row;
	}

	/**
	 * Used to display all the stocks with book id.
	 */
	public List<BookStockDetails> findAllBookStockDetails() throws DbException {
		List<BookStockDetails> list = new ArrayList<BookStockDetails>();
		String sql1 = "select *from stock_room order by book_id";
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql1);
				ResultSet rs = pst.executeQuery(sql1);) {
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				int isbnNo = rs.getInt("isbn_no");
				int active = rs.getInt("active");
				BookStockDetails bd = new BookStockDetails(bookId, isbnNo, active);
				list.add(bd);
			}
		} catch (SQLException e) {
			throw new DbException("Invalid Select");
		}
		return list;
	}

	/**
	 * Used to update the stock room for each book which is taken.
	 */
	public int updateStockRoom() throws DbException {
		String sql = "update stock_room set active=0 where book_id in(select f.book_id from stock_room s,fine_calc f where f.book_id=s.book_id and f.status='Issued')";
		int row = 0;
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);) {
			row = pst.executeUpdate(sql);
			log.getInput(row + " row updated");
		} catch (SQLException e) {
			throw new DbException("Unable to update");
		}
		return row;
	}

	/**
	 * Unused code
	 */
	@Override
	public List<BookStockDetails> findTotalStocks() throws DbException {
		List<BookStockDetails> list = new ArrayList<BookStockDetails>();
		String sql = "select isbn_no,count(*)as total_books from stock_room group by isbn_no order by isbn_no asc";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					int isbnNo = rs.getInt("isbn_no");
					int totalBooks = rs.getInt("total_books");
					BookStockDetails bd = new BookStockDetails();
					bd.setIsbnNo(isbnNo);
					bd.setTotalBooks(totalBooks);
					list.add(bd);
				}
			}
		} catch (SQLException e) {
			throw new DbException("Invalid Select");
		} catch (DbException e) {
			throw new DbException("Connection error");
		}
		return list;
	}

	/**
	 * Unused code
	 */
	@Override
	public int updateActive(int bookId) throws DbException {
		String sql = "update stock_room set active=0 where book_id=?";
		int row = 0;
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setInt(1, bookId);
			row = pst.executeUpdate();
			log.getInput(row + " row updated");
		} catch (SQLException e) {
			throw new DbException("Unable to update");
		}
		return row;
	}

	/**
	 * Used to find all the remaining stocks of each books
	 */
	@Override
	public List<BookStockDetails> findAllIndividualRemaining() throws DbException {
		List<BookStockDetails> list = new ArrayList<BookStockDetails>();
		String sql = "select isbn_no,count(*)as remaining from stock_room where active=1 group by isbn_no order by isbn_no asc";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				int isbnNo = rs.getInt("isbn_no");
				int remaining = rs.getInt("remaining");
				BookStockDetails e = new BookStockDetails(isbnNo, remaining);
				list.add(e);
			}
		} catch (SQLException e) {
			throw new DbException("Invalid select");
		}
		return list;
	}

	/**
	 * Used to find all the issued stocks of each books
	 */
	@Override
	public List<BookStockDetails> findAllIndividualIssued() throws DbException {
		List<BookStockDetails> list = new ArrayList<BookStockDetails>();
		String sql = "select isbn_no,count(*)as issued from stock_room where active=0 group by isbn_no order by isbn_no asc";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				BookStockDetails d = new BookStockDetails();
				d.setIsbnNo(rs.getInt("isbn_no"));
				d.setIssued(rs.getInt("issued"));
				list.add(d);
			}
		} catch (SQLException e) {
			throw new DbException("Invalid Select");
		}
		return list;
	}

	/**
	 * Used to remove the stock of the book.
	 */
	@Override
	public int deleteStock(int bookId) throws DbException {
		String sql = "delete from stock_room where book_id=?";
		int row = 0;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, bookId);
			row = pst.executeUpdate();
			log.getInput(row + " row deleted");
		} catch (SQLException e) {
			throw new DbException("Unable to delete");
		}
		return row;
	}
}