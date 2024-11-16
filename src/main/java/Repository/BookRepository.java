package Repository;

import entity.Book;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BookRepository {
    private final EntityManager entityManager;

    public BookRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Book book) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(book);
            entityManager.getTransaction().commit();
            System.out.println("Book saved");
        } catch (Exception e) {
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Failed to save book");
        }
    }

    public Book findById(String id) {
        return entityManager.find(Book.class, id);
    }

    public List<Book> findAll() {
        return entityManager.createQuery("from Book", Book.class).getResultList();
    }

    public void update(Book book) {
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(book);
            entityManager.getTransaction().commit();
            System.out.println("Book updated");
        } catch (Exception e) {
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Failed to update book");
        }
    }

    public void delete(Book book) {
        try{
            entityManager.getTransaction().begin();
            entityManager.remove(book);
            entityManager.getTransaction().commit();
            System.out.println("Book deleted");
        } catch (Exception e) {
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Failed to delete book");
        }
    }
}
