package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //실제 사용시에는 스프링의 PlatformTransactionManager 의 도움을 받아서
        //이러한 try catch 같은 상용어구들을 사용하지 않아도 된다.
        try {
            /*INSERT*/
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloA");
//            em.persist(member);

            /*UPDATE*/
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");

            /*DELETE*/
//            em.remove(findMember);

            /*JPQL*/
//            List<Member> result = em.createQuery("SELECT m FROM Member AS m", Member.class)
//                    .getResultList();
            List<Member> result = em.createQuery("SELECT m FROM Member AS m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
