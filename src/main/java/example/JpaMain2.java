package example;

import example.domain.Child;
import example.domain.Parent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            System.out.println("=== 1. 부모-자식 생성 및 연관 ===");

            Parent parent = new Parent();
            parent.setName("parent");

            Child child1 = new Child();
            child1.setName("child1");

            Child child2 = new Child();
            child2.setName("child2");

            parent.addChild(child1);
            parent.addChild(child2);

            System.out.println("=== 2. 부모만 persist (Cascade 테스트) ===");
            em.persist(parent);

            em.flush();
            em.clear();

            System.out.println("=== 3. 자식 제거 후 orphanRemoval 테스트 ===");
            Parent foundParent = em.find(Parent.class, parent.getId());
            foundParent.getChildren().remove(0);

            em.flush();
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

}
