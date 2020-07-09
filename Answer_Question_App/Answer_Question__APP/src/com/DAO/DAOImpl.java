package com.DAO;

import com.model.Question;
import com.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOImpl implements DAO {

    @Override
    public List<Question> getAllQuestions() {
        List<Question> allQuestion = new ArrayList<>();

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT id,description,answer,help FROM question";

        c = DBHelper.getConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Question q = new Question();
                    q.setId(rs.getInt("id"));
                    q.setDescription(rs.getString("description"));
                    q.setAnswer(rs.getString("answer"));
                    q.setHelp(rs.getString("help"));
                    allQuestion.add(q);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                Util.closeAll(c, ps, rs);
            }
        } else {
            System.err.println("Error...");
        }

        return allQuestion;

    }

    @Override
    public List<String> getChoicesBy_QuestionId(int question_id) {
        List<String> allChoices = new ArrayList<>();

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select name from choices where question_id=" + question_id + " ";

        c = DBHelper.getConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    allChoices.add(rs.getString("name"));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                Util.closeAll(c, ps, rs);
            }
        } else {
            System.err.println("Error...");
        }
        return allChoices;
    }

}
