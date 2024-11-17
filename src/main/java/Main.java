import Constant.BookStatus;
import Constant.BorrowingStatus;
import Repository.BookRepository;
import Repository.BorrowingRepository;
import Repository.MemberRepository;
import entity.Book;
import entity.BorrowingRecord;
import entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utils.ScannerUtils;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        BookRepository bookRepository = new BookRepository(entityManager);
        MemberRepository memberRepository = new MemberRepository(entityManager);
        BorrowingRepository borrowingRepository = new BorrowingRepository(entityManager);

        while (true) {
            System.out.println("-".repeat(30));
            System.out.println("Welcome to Library Management System");
            System.out.println("-".repeat(30));
            System.out.println("1. Book Management");
            System.out.println("2. Member Management");
            System.out.println("3. Borrowing");
            System.out.println("4. Reports");
            System.out.println("5. Exit");
            System.out.print("What do you want to do? ");
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    System.out.println("-".repeat(30));
                    System.out.println("1. Add Book");
                    System.out.println("2. View All Books");
                    System.out.println("3. Search Book");
                    System.out.println("4. Update Books");
                    System.out.println("5. Delete Book");
                    System.out.print("What do you want to do? ");
                    String bookCommand = scanner.nextLine();
                    switch (bookCommand) {
                        case "1":
                            String title = ScannerUtils.inputText("Title");
                            String author = ScannerUtils.inputText("Author");
                            String publisher = ScannerUtils.inputText("Publisher");
                            Date publishedDate = Date.valueOf(ScannerUtils.inputText("Published Date"));
                            Integer totalCopies = Integer.parseInt(ScannerUtils.inputText("Total Copies"));
                            BookStatus status = BookStatus.AVAILABLE;
                            Book book = Book.builder()
                                    .title(title)
                                    .author(author)
                                    .publisher(publisher)
                                    .publishedDate(publishedDate)
                                    .totalCopies(totalCopies)
                                    .status(status)
                                    .build();
                            bookRepository.save(book);
                            break;
                        case "2":
                            List<Book> result =  bookRepository.findAll();
                            result.forEach(b -> {
                                System.out.println(b.toString());
                            });
                            break;
                        case "3":
                            System.out.println("Search Book");
                            System.out.println("-".repeat(30));
                            System.out.println("1. Search Book by ID");
                            System.out.println("2. Search Book by Title");
                            System.out.println("3. Search Book by Author");
                            String choice = ScannerUtils.inputText("Choice");
                            switch (choice) {
                                case "1":
                                    String id = ScannerUtils.inputText("ID");
                                    System.out.println(bookRepository.findById(id).toString());
                                    break;
                                case "2":
                                    String searchTitle = ScannerUtils.inputText("Title");
                                    List<Book> searchTitleResult = bookRepository.findByTitle(searchTitle);
                                    searchTitleResult.forEach(b -> {
                                        System.out.println(b.toString());
                                    });
                                    break;
                                case "3":
                                    String searchAuthor = ScannerUtils.inputText("Author");
                                    List<Book> searchAuthorResult = bookRepository.findByAuthor(searchAuthor);
                                    searchAuthorResult.forEach(b -> {
                                        System.out.println(b.toString());
                                    });
                                    break;
                                default:
                                    System.out.println("Invalid command");
                                    break;
                            }
                            break;
                        case "4":
                            String bookId = ScannerUtils.inputText("Book ID");
                            Book book1 = bookRepository.findById(bookId);
                            String updatedTitle = ScannerUtils.inputText("Title");
                            String updatedAuthor = ScannerUtils.inputText("Author");
                            String updatedPublisher = ScannerUtils.inputText("Publisher");
                            String updatedPublishedDate = ScannerUtils.inputText("Published Date");
                            Integer updatedTotalCopies = Integer.parseInt(ScannerUtils.inputText("Total Copies"));
                            if(!updatedTitle.isEmpty()){
                                book1.setTitle(updatedTitle);
                            } else{
                                book1.setTitle(book1.getTitle());
                            }

                            if(!updatedAuthor.isEmpty()){
                                book1.setAuthor(updatedAuthor);
                            } else{
                                book1.setAuthor(book1.getAuthor());
                            }

                            if(!updatedPublisher.isEmpty()){
                                book1.setPublisher(updatedPublisher);
                            } else{
                                book1.setPublisher(book1.getPublisher());
                            }

                            if(!updatedPublishedDate.isEmpty()){
                                book1.setPublishedDate(Date.valueOf(updatedPublishedDate));
                            } else{
                                book1.setPublishedDate(book1.getPublishedDate());
                            }

                            if(!updatedTotalCopies.toString().isEmpty()){
                                book1.setTotalCopies(updatedTotalCopies);
                            } else{
                                book1.setTotalCopies(book1.getTotalCopies());
                            }

                            if(book1.getTotalCopies() > 0){
                                book1.setStatus(BookStatus.AVAILABLE);
                            } else{
                                book1.setStatus(BookStatus.BORROWED);
                            }

                            bookRepository.update(book1);
                            break;
                        case "5":
                            String deleteId = ScannerUtils.inputText("Book ID");
                            bookRepository.delete(bookRepository.findById(deleteId));
                            break;
                        default:
                            System.out.println("Invalid command");
                            break;
                    }
                    break;
                case "2":
                    System.out.println("-".repeat(30));
                    System.out.println("1. Add Member");
                    System.out.println("2. Update Member");
                    System.out.println("3. Delete Member");
                    System.out.println("4. View All Members");
                    System.out.print("What do you want to do? ");
                    String memberCommand = scanner.nextLine();
                    switch (memberCommand) {
                        case "1":
                            String name = ScannerUtils.inputText("Name");
                            String email = ScannerUtils.inputText("Email");
                            String phone = ScannerUtils.inputText("Phone Number");
                            Member member = Member.builder()
                                    .name(name)
                                    .email(email)
                                    .phoneNumber(phone)
                                    .borrowedLimit(3)
                                    .build();
                            memberRepository.save(member);
                            break;
                        case "2":
                            String memberId = ScannerUtils.inputText("Member ID");
                            Member member1 = memberRepository.findMemberById(memberId);
                            String updatedName = ScannerUtils.inputText("Name");
                            String updatedEmail = ScannerUtils.inputText("Email");
                            String updatedPhone = ScannerUtils.inputText("Phone Number");
                            if(!updatedName.isEmpty()){
                                member1.setName(updatedName);
                            } else{
                                member1.setName(member1.getName());
                            }

                            if(!updatedEmail.isEmpty()){
                                member1.setEmail(updatedEmail);
                            } else{
                                member1.setEmail(member1.getEmail());
                            }

                            if(!updatedPhone.isEmpty()){
                                member1.setPhoneNumber(updatedPhone);
                            } else{
                                member1.setPhoneNumber(member1.getPhoneNumber());
                            }

                            memberRepository.update(member1);
                            break;
                        case "3":
                            String removeId = ScannerUtils.inputText("Member ID");
                            memberRepository.delete(memberRepository.findMemberById(removeId));
                            break;
                        case "4":
                            List<Member> result =  memberRepository.findAll();
                            result.forEach(m -> {
                                System.out.println(m.toString());
                            });
                            break;
                        default:
                            System.out.println("Invalid command");
                            break;
                    }
                    break;
                case "3":
                    System.out.println("-".repeat(30));
                    System.out.println("1. Borrow Book");
                    System.out.println("2. Return Book");
                    System.out.println("3. View Borrowing Records");
                    System.out.print("What do you want to do? ");
                    String borrowCommand = scanner.nextLine();
                    switch (borrowCommand) {
                        case "1":
                            String bookId = ScannerUtils.inputText("Book ID");
                            Book book = bookRepository.findById(bookId);
                            String memberId = ScannerUtils.inputText("Member ID");
                            Member member = memberRepository.findMemberById(memberId);
                            if(member.getBorrowedLimit() > 0){
                                member.setBorrowedLimit(member.getBorrowedLimit() - 1);
                            } else {
                                System.out.println("Reach Borrowing Limit");
                                break;
                            }
                            LocalDateTime borrowTime = LocalDateTime.now();
                            LocalDateTime dueDate = borrowTime.plusDays(7);
                            book.setTotalCopies(book.getTotalCopies() - 1);
                            if(book.getTotalCopies() == 0) {
                                book.setStatus(BookStatus.BORROWED);
                            }
                            BorrowingRecord borrowingRecord = BorrowingRecord.builder()
                                    .book(book)
                                    .member(member)
                                    .borrowDate(borrowTime)
                                    .dueDate(dueDate)
                                    .returnDate(null)
                                    .status(BorrowingStatus.BORROWED)
                                    .lateFees(null)
                                    .build();
                            borrowingRepository.borrowBook(borrowingRecord);
                            break;
                        case "2":
                            String returnedBookId = ScannerUtils.inputText("Book ID");
                            Book returnedBook = bookRepository.findById(returnedBookId);
                            LocalDateTime returnedDate = LocalDateTime.now();
                            BorrowingRecord borrowingRecord1 = borrowingRepository.getRecordByBookId(returnedBookId);
                            borrowingRecord1.setBook(borrowingRecord1.getBook());
                            borrowingRecord1.setMember(borrowingRecord1.getMember());
                            borrowingRecord1.setBorrowDate(borrowingRecord1.getBorrowDate());
                            borrowingRecord1.setDueDate(borrowingRecord1.getDueDate());
                            borrowingRecord1.setReturnDate(returnedDate);
                            borrowingRecord1.setStatus(BorrowingStatus.RETURNED);

                            int lateRate = 1000;
                            long overdueDays = ChronoUnit.DAYS.between(borrowingRecord1.getDueDate(), returnedDate);
                            int lateFees = 0;
                            if(overdueDays > 0){
                                lateFees = (int) (overdueDays * lateRate);
                            }
                            borrowingRecord1.setLateFees(lateFees);

                            borrowingRepository.returnBook(borrowingRecord1);

                            returnedBook.setTotalCopies(returnedBook.getTotalCopies() + 1);
                            borrowingRecord1.getMember().setBorrowedLimit(borrowingRecord1.getMember().getBorrowedLimit() + 1);
                            break;
                        case "3":
                            List<BorrowingRecord> borrowingRecords = borrowingRepository.viewAllRecords();
                            borrowingRecords.forEach(m -> {
                                System.out.println(m.toString());
                            });
                            break;
                        default:
                            System.out.println("Invalid command");
                            break;
                    }
                    break;
                case "4":
                    System.out.println("-".repeat(30));
                    System.out.println("1. View Borrowed Books");
                    System.out.println("2. View Borrowing Records By Member");
                    System.out.println("3. View Overdue Books");
                    System.out.print("What do you want to do? ");
                    String viewBookCommand = scanner.nextLine();
                    switch (viewBookCommand) {
                        case "1":
                            List<BorrowingRecord> borrowedRecords = borrowingRepository.viewBorrowedRecords();
                            borrowedRecords.forEach(m -> {
                                System.out.println(m.toString());
                            });
                            break;
                        case "2":
                            String memberId = ScannerUtils.inputText("Member ID");
                            Member member = memberRepository.findMemberById(memberId);
                            List<BorrowingRecord> memberBorrowing = memberRepository.viewMemberRecords(member);
                            memberBorrowing.forEach(m -> {
                                System.out.println(m.toString());
                            });
                            break;
                        case "3":
                            List<BorrowingRecord> borrowedRecords2 = borrowingRepository.viewBorrowedRecords();
                            borrowedRecords2.forEach(m -> {
                                if(ChronoUnit.DAYS.between(m.getDueDate(), LocalDateTime.now()) > 0){
                                    System.out.println(m);
                                }
                            });
                            break;
                        default:
                            System.out.println("Invalid command");
                            break;
                    }
                    break;
                case "5":
                    entityManagerFactory.close();
                    entityManager.close();
                    return;
                default:
                    System.out.println("Invalid command");
            }
        }
    }
}
