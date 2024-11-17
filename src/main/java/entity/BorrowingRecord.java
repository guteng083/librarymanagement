package entity;

import Constant.BorrowingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
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
    private Date borrowDate;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "return_date")
    private Date returnDate;

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
