package com.kh.mybatis.student.model.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.student.model.dao.StudentDao;
import com.kh.mybatis.student.model.dao.StudentDaoImpl;
import com.kh.mybatis.student.model.vo.Student;

import static com.kh.mybatis.common.MybatisUtils.*;

import java.util.Map;

public class StudentServiceImpl implements StudentService {
	
	private StudentDao studentDao = new StudentDaoImpl();

	@Override
	public int insertStudent(Student student) {
		int result = 0;
		//1. Connection 대신 SqlSession 생성
		SqlSession session = getSqlSession();
		
		try {
			//2. dao 업무위임
			result = studentDao.insertStudent(session,student);
			//3. transaction 처리: SqlSession에 대해서 commit|rollback 
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
		//4. SqlSession 자원반납
		return 0;
	}

	@Override
	public int insertStudentMap(Map<String, Object> student) {
		int result = 0;
		SqlSession session = getSqlSession();
		try {
			result = studentDao.insertStudentMap(session, student);
			session.commit();
		} catch(Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public int selectStudentCount() {
		SqlSession session = getSqlSession();
		int total = studentDao.selectStudentCount(session);
		session.close();
		return total;
	}

	@Override
	public Student selectOneStudent(int no) {
		SqlSession session = getSqlSession();
		Student student = studentDao.selectOneStudent(session,no);
		session.close();
		return student;
	}

	@Override
	public int updateStudentMap(Map<String, Object> student) {
		int result = 0;
		SqlSession session = getSqlSession();
		try {
			result = studentDao.updateStudentMap(session, student);
			session.commit();
		} catch(Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public Map<String, Object> selectOneStudentMap(int no) {
		SqlSession session = getSqlSession();
		Map<String, Object> student = studentDao.selectOneStudentMap(session,no);
		session.close();
		return student;
	}
}
