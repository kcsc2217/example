package Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardRepositoryMySql implements BoardRepository{
    private static final String INSERT_SQL = "INSERT_INTO BOARD(TITLE,WRITER,CONTENT,REG_DATE) VALUES(?,?,?,NOT())";


    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;


    private BoardRepositoryMySql() {
    }
    private static BoardRepository instance = new BoardRepositoryMySql();

    public static BoardRepository getInstance() {
        return instance;
    }


    @Override
    public int insert(BoardDTO boardDTO) throws SQLException {
        int result = 0;
        try {
            String sql = "INSERT INTO BOARD(TITLE, WRITER, CONTENT, REG_DATE) VALUES(?, ?, ?, NOW())";
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, boardDTO.getTitle());
            ps.setString(2, boardDTO.getWriter());
            ps.setString(3, boardDTO.getContent());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("insert error");
            throw e;
        } finally {
            DBUtil.close(ps, conn);
        }
        return result;
    }


    @Override
    public int update(BoardDTO boardDTO, String category) throws SQLException {
        int result = 0;


        try{
            String sql = "UPDATE BOARD SET ";
            if("title:".equals(category)){
                sql += "TITLE = " + boardDTO.getTitle();
            }
            else if("writer:".equals(category)){
                sql += "WRITER = " + boardDTO.getWriter();
            }
            else if("content:".equals(category)){
                sql += "CONTENT =  " + boardDTO.getContent();
            }
            sql += "WHERE NO = " + boardDTO.getNo();
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            result = ps.executeUpdate();

        }catch (SQLException e){
            System.out.println("update error");
            e.printStackTrace();
        }
        finally {
            DBUtil.close(ps,conn);
        }

        return result;
    }

    @Override
    public int delete(BoardDTO boardDTO) throws SQLException {
        return 0;
    }

    @Override
    public List<BoardDTO> selectAll() throws SQLException {
        List<BoardDTO> list = new ArrayList<BoardDTO>();


        try {
            conn = DBUtil.getConnection();
            String sql = " SELECT NO, TITLE, WRITER, CONTENT, READ_COUNT, REG_DATE FROM BOARD ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                BoardDTO boardDTO = getBoardDTO();
                list.add(boardDTO);
            }
        } catch (SQLException e) {
            System.out.println("insert error");
            throw e;
        } finally {
            DBUtil.close(rs, ps, conn);
        }


        return list;
    }



    @Override
    public BoardDTO selectOne(int id) throws SQLException {
       BoardDTO boardDTO = null;


        try {
            conn = DBUtil.getConnection();
            String sql = " SELECT NO, TITLE, WRITER, CONTENT, READ_COUNT, REG_DATE FROM BOARD WHERE NO=  " + id;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if(rs.next()){ //글 번호 이상함녀 없을수 도 있음
               boardDTO = getBoardDTO();
            }
        } catch (SQLException e) {
            System.out.println("insert error");
            throw e;
        } finally {
            DBUtil.close(rs, ps, conn);
        }

        return boardDTO;

    }

    private BoardDTO getBoardDTO() throws SQLException {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setNo(rs.getInt(1));
        boardDTO.setTitle(rs.getString(2));
        boardDTO.setWriter(rs.getString(3));
        boardDTO.setContent(rs.getString(4));
        boardDTO.setRegDate(rs.getString(5));
        boardDTO.setRegDate(rs.getString(6));
        return boardDTO;
    }
}
