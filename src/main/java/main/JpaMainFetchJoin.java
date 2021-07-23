package main;

import jpql.Member;
import jpql.Team;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class JpaMainFetchJoin {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{

            String query = "select t from Team t join fetch t.members";

            List<Team> result = em.createQuery(query, Team.class).getResultList();

            for (Team taem : result) {
                System.out.println("member = " + taem.getName()+" | members="+taem.getMembers().size());
                for(Member member: taem.getMembers()){
                    System.out.println("member = " + member);
                }
            }

            tx.commit();
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }
    }
}
