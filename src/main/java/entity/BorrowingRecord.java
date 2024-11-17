package entity;

import Constant.BorrowingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "borrowing_record")
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "borrow_date")
    private LocalDateTime borrowDate;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "status")
    private BorrowingStatus status;

    @Column(name = "late_fees")
    private double lateFees;

    @Override
    public String toString() {
        return "Id = " + id + '\n' +
                "Book = " + book + '\n' +
                "Member = " + member + '\n' +
                "Borrow Date = " + borrowDate + '\n' +
                "Due Date = " + dueDate + '\n' +
                "Return Date = " + returnDate + '\n' +
                "Status = " + status + '\n' +
                "Late Fees = " + lateFees;
    }
}
