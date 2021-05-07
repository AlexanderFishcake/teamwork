package com.kh.mybatis.student.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.student.model.vo.Student;

public class StudentDaoImpl implements StudentDao {

	@Override
	public int insertStudent(SqlSession session, Student student) {
		// statement : namespace.queryTagId
		return session.insert("student.insertStudent",student);
	}

	@Override
	public int insertStudentMap(SqlSession session, Map<String, Object> student) {
		return session.insert("student.insertStudentMap",student);
	}

	@Override
	public int selectStudentCount(SqlSession session) {
		return session.selectOne("student.selectStudentCount");
	}

	@Override
	public Student selectOneStudent(SqlSession session, int no) {
		return session.selectOne("student.selectOneStudent",no);
	}

	@Override
	public int updateStudentMap(SqlSession session, Map<String, Object> student) {
		return session.insert("student.updateStudentMap",student);
	}
	
	/**
	 * Map<String, Object>
	 * 컬럼명: String
	 * 컬럼값: Object
	 */
	@Override
	public Map<String, Object> selectOneStudentMap(SqlSession session, int no) {
		return session.selectOne("student.selectOneStudentMap",no);
	}

}
