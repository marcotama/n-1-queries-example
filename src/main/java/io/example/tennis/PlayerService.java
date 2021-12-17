package io.example.tennis;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Getter
public class PlayerService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    private PlayerRepository repo;

    @Transactional
    public List<Player> getAllPlayersViaRepo() {
        List<Player> players = repo.findAll();
        for (Player player : players) {
            player.getSponsoredBy();
            for (CompetitionInstance victory : player.getVictories()) {
                victory.getCompetition();
            }
            for (City city : player.getCountry().getCities()) {
                city.getName();
            }
        }
        return players;
    }

    public List<Player> getAllPlayersViaHql() {
        String hql = "from Player p " +
                "left join fetch p.sponsoredBy b " +
                "left join fetch p.victories ci " +
                "left join fetch ci.competition " +
                "left join fetch p.country c " +
                "left join fetch c.cities ";
        Session session = entityManager.unwrap(Session.class);
        Query<Player> query = session.createQuery(hql, Player.class);
        return query.getResultList();
    }
}
