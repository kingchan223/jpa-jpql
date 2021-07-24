package main;

import jpql.Member;
import jpql.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMainNamedQuery {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setAge(30);
            em.persist(member1);

            //모든 멤버의 나이를 20으로 바꾸기
            int resultCount = em.createQuery("update Member m  set m.age = 20")
                    .executeUpdate();
            em.find(Member.class, member1.getId());
            System.out.println("resultCount = " + resultCount);

            /*이때 member1의 age는 몇일까?*/
            System.out.println("member1.age = " + member1.getAge());

            tx.commit();
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }
    }
}
