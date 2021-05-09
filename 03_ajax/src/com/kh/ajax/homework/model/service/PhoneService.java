package com.kh.ajax.homework.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.ajax.homework.model.dao.PhoneDao;
import com.kh.ajax.homework.model.vo.Phone;
import static com.kh.common.filter.JDBCTemplate.*;

public class PhoneService {
	private PhoneDao phoneDao = new PhoneDao();

	public List<Phone> getPhonelist(String orderby) {
		Connection conn = getConnection();
		List<Phone> list = new ArrayList<>();
		try {
			list = phoneDao.getPhonelist(conn,orderby);
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(conn);
		}
		return list;
	}

	public int insertPhone(Phone phone) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = phoneDao.insertPhone(conn,phone);
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(conn);
		}
		return result;
	}
	
}
