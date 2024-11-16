import Repository.BookRepository;
import entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utils.ScannerUtils;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        BookRepository bookRepository = new BookRepository(entityManager);
        while (true) {
            System.out.println("-".repeat(30));
            System.out.println("Welcome to Library Management System");
            System.out.println("-".repeat(30));
            System.out.println("1. Add Book");
            System.out.println("2. Show Book");
            System.out.println("3. Search Book by ID");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Exit");
            System.out.print("Please enter a command: ");
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    String title = ScannerUtils.inputText("Title");
                    String author = ScannerUtils.inputText("Author");
                    String publisher = ScannerUtils.inputText("Publisher");
                    Date publishedDate = Date.valueOf(ScannerUtils.inputText("Published Date"));
                    Book book = Book.builder()
                            .title(title)
                            .author(author)
                            .publisher(publisher)
                            .publishedDate(publishedDate)
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
                    String id = ScannerUtils.inputText("ID");
                    System.out.println(bookRepository.findById(id).toString());
                    break;
                case "4":
                    String bookId = ScannerUtils.inputText("Book ID");
                    Book book1 = bookRepository.findById(bookId);
                    String updatedTitle = ScannerUtils.inputText("Title");
                    String updatedAuthor = ScannerUtils.inputText("Author");
                    String updatedPublisher = ScannerUtils.inputText("Publisher");
                    String updatedPublishedDate = ScannerUtils.inputText("Published Date");
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
                    bookRepository.update(book1);
                    break;
                case "5":
                    String deleteId = ScannerUtils.inputText("Book ID");
                    bookRepository.delete(bookRepository.findById(deleteId));
                    break;
                case "6":
                    scanner.close();
                    entityManagerFactory.close();
                    entityManager.close();
                    return;
                default:
                    System.out.println("Invalid command");
            }
        }
    }
}
