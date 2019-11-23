package Question;

import Database.DBConnection;
import WouldYouRatherWebParser.QuestionTwo;

import java.sql.*;

import static Database.DBConnection.closeConnection;
import static Database.DBConnection.getConnection;
import static Question.QuestionQuery.*;

public class QuestionHelper {
    private static Connection conn = null;
    private static PreparedStatement pstmt = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static Boolean checkDuplicate(String query, String question, String red, String blue) {
        Boolean result = false;
        conn = getConnection();
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, question);
            pstmt.setString(2, red);
            pstmt.setString(3, blue);

            rs = pstmt.executeQuery();
            if (rs.next()) return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, pstmt, rs);
        }
        return false;
    }

    public static Integer insertQuestion(Question q) {
        conn = getConnection();
        try {
            pstmt = conn.prepareStatement(INSERTQUESTION);
            pstmt.setString(1, q.getQuestion());
            pstmt.setString(2, q.getRed());
            pstmt.setString(3, q.getBlue());
            pstmt.setString(4, q.getCategory());
            pstmt.setString(5, q.getIsnsfw());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, pstmt);
        }
        return null;
    }

    public static Integer insertQuestionTwo(QuestionTwo q) {
        conn = getConnection();
        try {
            pstmt = conn.prepareStatement(INSERTQUESTIONTWO);
            pstmt.setString(1, q.getQuestion());
            pstmt.setString(2, q.getOptionOne());
            pstmt.setString(3, q.getOptionTwo());
            pstmt.setString(4, q.getOneImage());
            pstmt.setString(5, q.getTwoImage());
            pstmt.setString(6, q.getCategories());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, pstmt);
        }
        return null;
    }
}
