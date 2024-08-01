package Board;

import java.sql.SQLException;
import java.util.List;

public class BoardRepositoryOracle implements BoardRepository{

    @Override
    public int insert(BoardDTO boardDTO) throws SQLException {
        return 0;
    }

    @Override
    public int update(BoardDTO boardDTO, String category) throws SQLException {
        return 0;
    }

    @Override
    public int delete(BoardDTO boardDTO) throws SQLException {
        return 0;
    }


    @Override
    public List<BoardDTO> selectAll() throws SQLException {
        return List.of();
    }


    @Override
    public BoardDTO selectOne(int id) throws SQLException {
        return null;
    }
}
