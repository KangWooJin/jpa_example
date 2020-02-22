package me.blog.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import me.blog.jpa.model.Member;
import me.blog.jpa.model.Team;
import me.blog.jpa.model.inheritance.Movie;
import me.blog.jpa.model.proxy.Game;

@DataJpaTest
class testSave {
    @Autowired
    TestEntityManager em;

    @Test
    void proxyTest() {
        Game game = new Game();
        game.setName("game");
        em.persist(game);

        em.flush();
        em.clear();
        ;

        Game refGame = em.getEntityManager().getReference(Game.class, game.getId());
        System.out.println(refGame.getClass().getName());
        System.out.println(refGame instanceof Game);
        System.out.println(refGame.getName());
        System.out.println(refGame.getClass().getName());
    }

    @Test
    void insertTest() {
        Movie movie = new Movie();

        em.persistAndFlush(movie);
        em.clear();

        Movie result = em.find(Movie.class, movie.getId());
        System.out.println(result);

    }

    @Test
    void teamInsertAndSelect() {
        Team team = new Team();
        em.persist(team);

        em.flush();
        em.clear();

        Team result = em.find(Team.class, team.getId());
        System.out.println(result.getMembers().getClass().getName());
    }

    @Test
    void cascadeTest() {
        Team team = new Team();
        Member member = new Member();
        member.setTeam(team);
        em.persist(member);

        em.flush();
        em.clear();

        Member m = em.find(Member.class, member.getId());
    }

    @Test
    void orphanRemovalTest() {
        Team team = new Team();

        Member member = new Member();
        member.setTeam(team);
        em.persist(member);
        em.persist(team);

        em.flush();
        em.clear();

        Team result = em.find(Team.class, team.getId()); // team의 id를 알기 위해 조회
        em.remove(result);

        em.flush();
        em.clear();

        Team teamRes = em.find(Team.class, team.getId());
        Member memberRes = em.find(Member.class, member.getId());

        assertThat(teamRes).isNull();
        assertThat(memberRes).isNull();
    }

    @Test
    void memberInsertAndSelect() {

        Team team = new Team();
        em.persist(team);

        Member member = new Member();
        member.setTeam(team);
        em.persist(member);

        em.flush(); // flush를 해줘야 insert query가 실행 된다.
        em.clear();

        Member result = em.find(Member.class, member.getId());

    }

    @Test
    void objectGraphSearch() {
//        Member member = em.find(Member.class, 1L);
//        Team team = member.getTeam(); // 객체 그래프 탐색
        String jqpl = "select m from Member m join m.team t where t.name = :teamName";

        List<Member> resultList = em.getEntityManager()
                                    .createQuery(jqpl, Member.class)
                                    .setParameter("teamName", "팀1")
                                    .getResultList();

    }

    @Test
    void 문제확인() {

        Team team = new Team();
        em.persistAndFlush(team);

        // 회원 1 저장
        Member member1 = new Member();
        member1.setTeam(team);
        team.getMembers().add(member1);
        em.persistAndFlush(member1);

        // 회원 2 저장
        Member member2 = new Member();
        member2.setTeam(team);
        team.getMembers().add(member2);
        em.persistAndFlush(member2);

        em.clear(); // 영속성 컨텍스트 초기

        Member selectedMember = em.find(Member.class, member1.getId());
        System.out.println("team : " + selectedMember.getTeam());

    }
}
