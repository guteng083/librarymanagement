package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column()
    private int borrowedLimit;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BorrowingRecord> BorrowingRecords;

    @Override
    public String toString() {
        return "Id = " + id + '\n' +
                "Name = " + name + '\n' +
                "Email = " + email + '\n' +
                "Phone Number = " + phoneNumber + '\n' +
                "Borrowed Limit = " + borrowedLimit;
    }
}
