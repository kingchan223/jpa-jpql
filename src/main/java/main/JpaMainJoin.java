package main;

import jpql.Member;
import jpql.MemberType;
import jpql.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMainJoin {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{

//            Team team = new Team();
//            team.setName("Mancity");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("ho");
//            member.setAge(25);
//            member.setTeam(team);
//            em.persist(member);

//            String query = "select m, t from Member m left join m.team t on t.name= :teamName";
//            List resultList = em.createQuery(query).setParameter("teamName", "Mancity").getResultList();
//
//
//            String query2 = "select m, t from Member m left join Team t on m.username = t.name";
//            List<Member> resultList2 = em.createQuery(query2, Member.class).getResultList();

//            String query = "select m from Member m where m.team = ANY (select t from Team t)";
//            List<Member> resultList = em.createQuery(query, Member.class).getResultList();
//            for (Member member : resultList) {
//                System.out.println("member = " + member.getUsername());
//            }
            // enum은 패키지명 포함
//            String query = "select m from Member m where m.type = :userType";
//            List<Member> resultList = em.createQuery(query, Member.class)
//                    .setParameter("userType", MemberType.GOLD)
//                    .getResultList();

//            String query = "select "+
//                                "case when m.age <= 10 then '학생요금' "+
//                                "     when m.age >= 10 then '경로요금' "+
//                                "     else '일반요금' end "+
//                    "from Member m";
//
//            List<String> resultList = em.createQuery(query, String.class).getResultList();

            Member member = new Member();
            member.setUsername("관리자");
            member.setAge(25);
            em.persist(member);

//            String query = "select nullif(m.username, '관리자') from Member m";
//            List<String> resultList = em.createQuery(query, String.class)
//                    .getResultList();
//
//            for (String username : resultList) {
//                System.out.println("username : " + username);
//            }

            // group_concat: 데이터르 한 줄로
            String query = "select group_concat(m.username) from Member m";
            List<String> resultList = em.createQuery(query, String.class)
                    .getResultList();
            for (String s : resultList) {
                System.out.println("s = " + s);
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
