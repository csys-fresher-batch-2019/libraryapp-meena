package com.books.service;

import java.sql.Date;
import java.util.List;

import com.books.dao.PenalityCalcDAO;
import com.books.daofactory.DAOFactory;
import com.books.dto.Additional;
import com.books.dto.CalcCard;
import com.books.dto.CategorySettings;
import com.books.dto.LanguageSettings;
import com.books.model.PenalityCalc;

public class PenaltyCalcService {
	private PenalityCalcDAO penaltyCalcDAO = DAOFactory.getPenalityCalcDAO();

	public void calculateFineAmount(int bookId, int userId) throws Exception {
		penaltyCalcDAO.findFineAmount(bookId, userId);
	}

	public List<PenalityCalc> displayFineDetails() throws Exception {
		return penaltyCalcDAO.findAllFineDetails();
	}

	public int updateDueDate(int bookId, int userId) throws Exception {
		return penaltyCalcDAO.updateDueDate(bookId, userId);
	}

	public int setBookLimit(int count) throws Exception {
		return penaltyCalcDAO.saveBookLimit(count);
	}

	public int setPenality(int amount) throws Exception {
		return penaltyCalcDAO.savePenality(amount);
	}

	public int setDueDays(int days) throws Exception {
		return penaltyCalcDAO.saveDueDays(days);
	}

	public List<Additional> booksCount() throws Exception {
		return penaltyCalcDAO.findBybooksCount();
	}

	public int updateReturnStatus(int bookId, int userId, Date returnedDate) throws Exception {
		return penaltyCalcDAO.updateReturnStatus(bookId, userId, returnedDate);
	}

	public List<CalcCard> userCardCount() throws Exception {
		return penaltyCalcDAO.findAlluserCardCount();
	}

	public int insertUserBookDetails(int bookId, int userId, Date issuedDate) throws Exception {
		return penaltyCalcDAO.saveUserBookDetails(bookId, userId, issuedDate);
	}

	public int insertNewLanguage(String language) throws Exception {
		return penaltyCalcDAO.saveNewLanguage(language);
	}

	public int deleteLanguage(String language) throws Exception {
		return penaltyCalcDAO.deleteLanguage(language);
	}

	public List<LanguageSettings> displayLanguages() throws Exception {
		return penaltyCalcDAO.findAllLanguages();
	}

	public int insertNewCategory(String category) throws Exception {
		return penaltyCalcDAO.saveNewCategory(category);
	}

	public int deteleCategory(String category) throws Exception {
		return penaltyCalcDAO.deteleCategory(category);
	}

	public List<CategorySettings> displayCategories() throws Exception {
		return penaltyCalcDAO.findAllCategories();
	}

	public int updateDueDateAll() throws Exception {
		return penaltyCalcDAO.updateDueDateAll();
	}

	public int updateFineAll() throws Exception {
		return penaltyCalcDAO.updateFineAll();
	}

	public int updatePopup(int popup) throws Exception {
		return penaltyCalcDAO.updatePopup(popup);
	}
}
