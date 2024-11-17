package Repository;

import entity.BorrowingRecord;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

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

    public BorrowingRecord getRecordByBookId(String bookId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BorrowingRecord> query = cb.createQuery(BorrowingRecord.class);
        Root<BorrowingRecord> root = query.from(BorrowingRecord.class);
        query.select(root).where(cb.equal(root.get("book"), bookId));
        return entityManager.createQuery(query).getSingleResult();
    }
}
