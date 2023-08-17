package com.community.board.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class BoardDAO {
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;


    public void getcon() {
        try {
            String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
            String dbID = "hr";
            String dbPassword = "1234";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(dbURL, dbID, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int getAllCount() {
        int count = 0;
        getcon();

        try {

            String sql = "select count(*) from board";
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

        return count;

    }

    public Vector<BoardBean> getAllBoard(int startRow, int endRow) {
        Vector<BoardBean> v = new Vector<>();
        getcon();
        try {

            String SQL = "select * from (select A.* ,Rownum Rnum from (select *from board order by ref desc ,re_stop asc)A)";

            pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);


            rs = pstmt.executeQuery();


            while (rs.next()) {

                BoardBean bean = new BoardBean();
                bean.setNum(rs.getInt(1));
                bean.setWrite(rs.getString(2));
                bean.setEmail(rs.getString(3));
                bean.setSubject(rs.getString(4));
                bean.setPassword(rs.getString(5));
                bean.setReg_date(rs.getDate(6).toString());
                bean.setRef(rs.getInt(7));
                bean.setRe_stop(rs.getInt(8));
                bean.setRe_level(rs.getInt(9));
                bean.setReadcount(rs.getInt(10));
                bean.setContent(rs.getString(11));

                v.add(bean);
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }


    public void insertBoard(BoardBean bean) {
        getcon();

        int ref = 0;
        int re_stop = 1;
        int re_level = 1;
        try {
            String refSQL = "SELECT max(ref) FROM BOARD";

            pstmt = con.prepareStatement(refSQL);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                ref = rs.getInt(1) + 1;
            }

            String SQL = "INSERT INTO BOARD VALUES(board_seq.NEXTVAL,?,?,?,?,sysdate,?,?,?,0,?)";
            pstmt = con.prepareStatement(SQL);

            pstmt.setString(1, bean.getWrite());
            pstmt.setString(2, bean.getEmail());
            pstmt.setString(3, bean.getSubject());
            pstmt.setString(4, bean.getPassword());
            pstmt.setInt(5, ref);
            pstmt.setInt(6, re_stop);
            pstmt.setInt(7, re_level);
            pstmt.setString(8, bean.getContent());

            pstmt.executeUpdate();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public BoardBean getOneBoard(int num) {

        BoardBean bean = new BoardBean();
        getcon();
        try {

            String readsql = "update board set readcount = readcount+1 where num=?";
            pstmt = con.prepareStatement(readsql);
            pstmt.setInt(1, num);
            pstmt.executeUpdate();


            String SQL = "select * from board where num=?";

            pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, num);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                bean.setNum(rs.getInt(1));
                bean.setWrite(rs.getString(2));
                bean.setEmail(rs.getString(3));
                bean.setSubject(rs.getString(4));
                bean.setPassword(rs.getString(5));
                bean.setReg_date(rs.getDate(6).toString());
                bean.setRef(rs.getInt(7));
                bean.setRe_stop(rs.getInt(8));
                bean.setRe_level(rs.getInt(9));
                bean.setReadcount(rs.getInt(10));
                bean.setContent(rs.getString(11));
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return bean;
    }


    public void reWriteBoard(BoardBean bean) {

        int ref = bean.getRef();
        int re_stop = bean.getRe_stop();
        int re_level = bean.getRe_level();

        getcon();

        try {

            String levelsql = "update board set re_level=re_level+1 where ref=? and re_level > ?";

            pstmt = con.prepareStatement(levelsql);
            pstmt.setInt(1, ref);
            pstmt.setInt(2, re_level);

            pstmt.executeUpdate();

            String sql = "insert into board values(board_seq.NEXTVAL,?,?,?,?,sysdate,?,?,?,0,?)";
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, bean.getWrite());
            pstmt.setString(2, bean.getEmail());
            pstmt.setString(3, bean.getSubject());
            pstmt.setString(4, bean.getPassword());
            pstmt.setInt(5, ref);
            pstmt.setInt(6, re_stop + 1);
            pstmt.setInt(7, re_level + 1);
            pstmt.setString(8, bean.getContent());


            pstmt.executeUpdate();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public BoardBean getOneUpdateBoard(int num) {

        BoardBean bean = new BoardBean();
        getcon();
        try {

            String SQL = "select * from board where num=?";
            pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                bean.setNum(rs.getInt(1));
                bean.setWrite(rs.getString(2));
                bean.setEmail(rs.getString(3));
                bean.setSubject(rs.getString(4));
                bean.setPassword(rs.getString(5));
                bean.setReg_date(rs.getDate(6).toString());
                bean.setRef(rs.getInt(7));
                bean.setRe_stop(rs.getInt(8));
                bean.setRe_level(rs.getInt(9));
                bean.setReadcount(rs.getInt(10));
                bean.setContent(rs.getString(11));
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return bean;
    }


    public void updateBoard(int num, String subject, String content) {
        getcon();

        try {

            String sql = "update board set subject=? , content= ? where num = ?";


            pstmt = con.prepareStatement(sql);


            pstmt.setString(1, subject);
            pstmt.setString(2, content);
            pstmt.setInt(3, num);


            pstmt.executeUpdate();


            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deleteBoard(int num) {
        getcon();

        try {

            String sql = "delete from board where num=?";


            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, num);


            pstmt.executeUpdate();


            con.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
