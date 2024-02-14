package com.dletc.dao.impl;

import com.dletc.dao.StudentDao;
import com.dletc.domain.Student;
import com.dletc.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class StudentDaoImpl implements StudentDao {
    @Override
    public int insertStudent(Student student) {
        int count = 0;
        try {
            QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
            String sql = "insert into student values(null,?,?,?,?,?,?,null)";
            count = qr.update(sql, student.getSname(), student.getSex(), student.getHobby(), student.getBirthdate(), student.getPhone(), student.getReamrk(), student.getCid(), null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;

    }
}
