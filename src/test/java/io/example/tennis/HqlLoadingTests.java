package io.example.tennis;

import lombok.extern.log4j.Log4j2;
import org.hibernate.LazyInitializationException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class HqlLoadingTests {

    @Autowired
    EntityManager entityManager;

    @Autowired
    PlayerService playerService;

    @Test
    public void testReverseFieldIsNotFilled() {
        Set<Country> countries = new HashSet<>(
                entityManager.createQuery(
                                "from Country",
                                Country.class
                        )
                        .getResultList()
        );
        Set<City> cities = new HashSet<>(
                entityManager.createQuery(
                                "from City c where c.country in :countries",
                                City.class
                        )
                        .setParameter("countries", countries)
                        .getResultList()
        );
        countries.forEach(entityManager::detach);
        cities.forEach(entityManager::detach);
        for (Country country : countries) {
            Assertions.assertNotNull(country.getCities());
            Assertions.assertThrows(LazyInitializationException.class, () -> country.getCities().size());
        }
    }

    @Test
    public void testThereIsASingleInstanceOfEachObject() {
        Set<Player> players = new HashSet<>(
                entityManager.createQuery(
                                "from Player p join fetch p.country",
                                Player.class
                        )
                        .getResultList()
        );
        Set<Country> countries = new HashSet<>(
                entityManager.createQuery(
                                "from Country c join fetch c.cities where c in :countries",
                                Country.class
                        )
                        .setParameter("countries", players.stream().map(Player::getCountry).collect(Collectors.toSet()))
                        .getResultList()
        );
        players.forEach(entityManager::detach);
        countries.forEach(entityManager::detach);
        for (Player player : players) {
            Assertions.assertNotNull(player.getCountry().getCities());
            Assertions.assertDoesNotThrow(() -> player.getCountry().getCities().size());
        }
        for (Country country : countries) {
            Assertions.assertNotNull(country.getCities());
            Assertions.assertDoesNotThrow(() -> country.getCities().size());
        }
    }

    @Test
    public void testQueryGraphWorks() {
        Set<Player> players = new HashSet<>(playerService.getRepo().findAll());
        QueryGraph<Player> pgPlayer = new QueryGraph<>(players, Player.class, entityManager);
        pgPlayer
                .joinFetch("country", Country.class)
                .joinFetch("cities", City.class);
        players.forEach(entityManager::detach);
        for (Player player : players) {
            Assertions.assertNotNull(player.getCountry().getCities());
            Assertions.assertDoesNotThrow(() -> player.getCountry().getCities().size());
        }
    }
}
