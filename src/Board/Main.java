package Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        BoardRepository repo = BoardRepositoryMySql.getInstance();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            System.out.println("1: 게시물작성, 2: 게시물목록 3: 게시물 수정 4: 게시물 삭제 -1 종료");
            int select = Integer.parseInt(br.readLine());

            if(select == -1)return;

            switch(select){
                case 1:
                    System.out.print("title>>");
                    String title = br.readLine();
                    System.out.println("writer>> ");
                    String writer = br.readLine();
                    System.out.println("content >>");
                    String content = br.readLine();
                    System.out.println("실행결과 : " + repo.insert(new BoardDTO(title, writer, content)));
                    break;
                case 2:
                    for(BoardDTO b: repo.selectAll()){
                        System.out.println(b);
                    }





            }
        }
    }
}
