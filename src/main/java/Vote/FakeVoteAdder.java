package Vote;

import Database.DBConnection;

import java.security.SecureRandom;
import java.sql.*;

public class FakeVoteAdder {
    private static Connection conn = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;

    private static final String INSERT_VOTE = "insert into Votetwo( qid, totalvote, avote) values(?, ?, ?)";
    private static final String TIME_ZONE = "SET time_zone = 'US/Eastern'";

    public static void adder() {
        conn = DBConnection.getConnection();
        try {
            pstmt = conn.prepareStatement(INSERT_VOTE);
            pstmt.addBatch(TIME_ZONE);
//            218415
            for (int i = 51; i <= 218415; i++) {
                pstmt.setInt(1, i);
                Integer tVote = getTotalVote();
                pstmt.setInt(2, tVote);
                pstmt.setInt(3, getAVote(tVote));
                pstmt.addBatch();
                System.out.println(i + "question added");
            }
            pstmt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn, pstmt);
        }
    }

    private static String getValue() {
        return new SecureRandom().nextInt(2) == 0 ? "a" : "b";
    }

    private static Integer getTotalVote() {
        return new SecureRandom().nextInt(1_000) + 1;
    }

    private static Integer getAVote(Integer totalVote) {
        return new SecureRandom().nextInt(totalVote);
    }
}
