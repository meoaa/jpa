package example;

import example.domain.Member;
import example.domain.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain3 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member member = new Member();
            member.setName("meoaa");

            Order order1 = new Order();
            order1.setItem("책");
            order1.setMember(member);

            member.addOrder(order1);

            em.persist(member);
            em.persist(order1);

            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
