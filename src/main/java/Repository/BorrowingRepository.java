package Repository;

import entity.BorrowingRecord;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BorrowingRepository {
    private final EntityManager entityManager;

    public BorrowingRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void borrowBook(BorrowingRecord borrowingRecord) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(borrowingRecord);
            entityManager.getTransaction().commit();
            System.out.println("Successfully Borrowed " + borrowingRecord.getBook().getTitle());
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    public void returnBook(BorrowingRecord borrowingRecord) {
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(borrowingRecord);
            entityManager.getTransaction().commit();
            System.out.println("Successfully Returned " + borrowingRecord.getBook().getTitle());
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    public List<BorrowingRecord> viewAllRecords() {
        return entityManager.createQuery("from BorrowingRecord", BorrowingRecord.class).getResultList();
    }
}
