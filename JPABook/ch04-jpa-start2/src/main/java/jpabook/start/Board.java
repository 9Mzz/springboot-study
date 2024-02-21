package jpabook.start;

import javax.persistence.*;

@Entity
/*@TableGenerator(name = "BOARD_SEQ_GENERATOR",       //TABLE전략
                table = "MY_SEQUENCE", pkColumnValue = "BOARD_SEQ", allocationSize = 1)*/
//@Table(name = "board")
public class Board {

    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GENERATOR")
    //    @SequenceGenerator(name = "BOARD_SEQ_GENERATOR", sequenceName = "BOARD_SEQ", initialValue = 1, allocationSize = 1)
    //    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BOARD_SEQ_GENERATOR")
    //        @GeneratedValue(strategy = GenerationType.AUTO)
    private Long   board_id;
    @Column(name = "name", length = 50)
    private String name;
    @Column(name = "detail", unique = true, nullable = false)
    private String detail;
    //


    public Long getBoard_id() {
        return board_id;
    }

    public void setBoard_id(Long board_id) {
        this.board_id = board_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Board{" + "board_id=" + board_id + ", name='" + name + '\'' + ", detail='" + detail + '\'' + '}';
    }
}
