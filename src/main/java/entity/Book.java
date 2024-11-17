package entity;

import Constant.BookStatus;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private String title;

    private String author;

    private String publisher;

    @Column(name = "published_date")
    private Date publishedDate;

    @Column(name = "total_copies", nullable = false)
    private Integer totalCopies;

    @Column(nullable = false)
    private BookStatus status;

    @Override
    public String toString() {
        return "-".repeat(30) + '\n' +
                "ID = " + id + '\n' +
                "Title = " + title + '\n' +
                "Author = " + author + '\n' +
                "Publisher = " + publisher + '\n' +
                "Published Date = " + publishedDate + '\n' +
                "Total Copies = " + totalCopies + '\n' +
                "Status = " + status;
    }
}
