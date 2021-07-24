package main;

import jpql.Member;
import jpql.Team;

import javax.persistence.*;
import java.util.List;

public class JpaMainEntityUse {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setAge(20);
            member1.setTeam(team);
            em.persist(member1);

            String query = "select m from Member m where m.team.id = :teamId";//m.team은 TEAM_ID가 된다.

            List<Member> members = em.createQuery(query, Member.class)
                    .setParameter("teamId", 160L)
                    .getResultList();

            System.out.println("members = " + members);
            tx.commit();
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }
    }
}
