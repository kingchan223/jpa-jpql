package main;

import jpql.Member;
import jpql.Team;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class JpaMainPathExpression {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
//            Member member1 = new Member();
//            member1.setUsername("유저1");
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("유저2");
//            em.persist(member2);
//
//            em.flush();
//            em.clear();

            // 컬렉션 값 연관경로: 하지만 이렇게 명시적 조인을 해서 별칭을 얻고 별칭으로 탐색이 가능해진다.

            List resultList = em.createQuery("select m.username, m.age from Member m").getResultList();


            tx.commit();
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }
    }
}
