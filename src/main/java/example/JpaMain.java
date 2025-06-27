package example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            System.out.println("=== 1. persist ===");
            Member member = new Member("meoaa");
            em.persist(member);

            System.out.println("=== 2. flush ===");
            em.flush();

            System.out.println("=== 3. clear ===");
            em.clear();

            System.out.println("=== 4. find ===");
            Member findMember = em.find(Member.class, member.getId());

            Member findMember2 = em.find(Member.class, member.getId());

            System.out.println("findMember.getName() = " + findMember.getName());
            System.out.println("findMember2.getName() = " + findMember2.getName());

            System.out.println("=== 5. detach ===");
            em.detach(findMember);

            findMember.setName("changed");
            
            System.out.println("============before commit===========");
            tx.commit();
            System.out.println("============after commit===========");
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
