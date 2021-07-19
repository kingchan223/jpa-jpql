package main;

import jpql.Member;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
//            Member member = new Member();
//            member.setUsername("roro");
//            member.setAge(10);
//            em.persist(member);

            //파라미터 바인딩(위치 기준) -> 사용하지 않는 것 권장
            Member singleResult = em.createQuery("select m from Member m where m.username=?1", Member.class)
                    .setParameter(1, "username")
                    .getSingleResult();
            System.out.println("singleResult = " + singleResult);

            tx.commit();
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }
    }
}
