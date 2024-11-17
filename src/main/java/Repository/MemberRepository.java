package Repository;

import entity.BorrowingRecord;
import entity.Member;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MemberRepository {
    private final EntityManager entityManager;

    public MemberRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Member member) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(member);
            entityManager.getTransaction().commit();
            System.out.println("Member saved successfully");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    public List<Member> findAll() {
        return entityManager.createQuery("select m from Member m", Member.class).getResultList();
    }

    public void update(Member member) {
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(member);
            entityManager.getTransaction().commit();
            System.out.println("Member updated successfully");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    public void delete(Member member) {
        try{
            entityManager.getTransaction().begin();
            entityManager.remove(member);
            entityManager.getTransaction().commit();
            System.out.println("Member deleted successfully");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    public List<BorrowingRecord> viewMemberRecords(Member member) {
        return member.getBorrowingRecords();
    }
}
