package entity;

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

    @Override
    public String toString() {
        return "ID = " + id + "\n" +
                "Title = " + title + '\n' +
                "Author = " + author + '\n' +
                "Publisher = " + publisher + '\n' +
                "Published Date = " + publishedDate;
    }
}
