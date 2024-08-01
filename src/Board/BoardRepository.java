package Board;

import java.sql.SQLException;
import java.util.List;

public interface BoardRepository {
    int insert(BoardDTO boardDTO) throws SQLException;

    int update (BoardDTO boardDTO, String category) throws SQLException;
    int delete (BoardDTO boardDTO) throws SQLException;


    List<BoardDTO> selectAll() throws SQLException;

    BoardDTO selectOne(int id) throws SQLException;
}
