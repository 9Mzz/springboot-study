package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BoardMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void main(String[] args) {
        System.out.println("BoardMain.main");
        EntityManager     emm = emf.createEntityManager();
        EntityTransaction tx  = emm.getTransaction();

        tx.begin();
        logic(emm);

        tx.commit();
        emm.close();
        emf.close();

    }

    private static void logic(EntityManager emm) {
        System.out.println("BoardMain.logic");
        Board board = new Board();
        board.setName("안녕하세요");
        board.setDetail("반갑습니다");
        emm.persist(board);

        Board findBoard = emm.find(Board.class, 1L);
        System.out.println("findBoard = " + findBoard);
        findBoard.setName("수정된 안녕하세요");
        Board newBoard = emm.find(Board.class, 1L);
        System.out.println("newBoard = " + newBoard);
        emm.remove(board);

    }

}
