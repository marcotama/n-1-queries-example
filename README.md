This basic repo is to show the N+1 query problem and how HQL trades one issue for another.

If you run the application, you will an output like this:

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.6)

2021-12-17 12:02:47.516  INFO 13096 --- [           main] i.e.tennis.TennisPlayerApplication       : Starting TennisPlayerApplication using Java 11.0.9.1 on 6LW9R73 with PID 13096 (C:\Users\mtamassia\Projects\n+1-queries-example\target\classes started by mtamassia in C:\Users\mtamassia\Projects\n+1-queries-example)
2021-12-17 12:02:47.518  INFO 13096 --- [           main] i.e.tennis.TennisPlayerApplication       : No active profile set, falling back to default profiles: default
2021-12-17 12:02:47.989  INFO 13096 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2021-12-17 12:02:48.027  INFO 13096 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 32 ms. Found 1 JPA repository interfaces.
2021-12-17 12:02:48.343  INFO 13096 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2021-12-17 12:02:48.348  INFO 13096 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2021-12-17 12:02:48.348  INFO 13096 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.54]
2021-12-17 12:02:48.425  INFO 13096 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2021-12-17 12:02:48.426  INFO 13096 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 860 ms
2021-12-17 12:02:48.448  INFO 13096 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2021-12-17 12:02:48.548  INFO 13096 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2021-12-17 12:02:48.552  INFO 13096 --- [           main] o.s.b.a.h2.H2ConsoleAutoConfiguration    : H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:testdb'
2021-12-17 12:02:48.646  INFO 13096 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2021-12-17 12:02:48.675  INFO 13096 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 5.4.32.Final
2021-12-17 12:02:48.748  INFO 13096 --- [           main] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.1.2.Final}
2021-12-17 12:02:48.816  INFO 13096 --- [           main] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.H2Dialect
Hibernate: drop table if exists brand CASCADE 
Hibernate: drop table if exists city CASCADE 
Hibernate: drop table if exists competition CASCADE 
Hibernate: drop table if exists competition_instance CASCADE 
Hibernate: drop table if exists country CASCADE 
Hibernate: drop table if exists player CASCADE 
Hibernate: drop table if exists sponsor CASCADE 
Hibernate: drop sequence if exists hibernate_sequence
Hibernate: create sequence hibernate_sequence start with 1 increment by 1
Hibernate: create table brand (id integer not null, name varchar(255), primary key (id))
Hibernate: create table city (id integer not null, name varchar(255), country_id integer, primary key (id))
Hibernate: create table competition (id integer not null, name varchar(255), host_country_id integer, primary key (id))
Hibernate: create table competition_instance (id integer not null, year integer, competition_id integer, winner_id integer, primary key (id))
Hibernate: create table country (id integer not null, name varchar(255), primary key (id))
Hibernate: create table player (id integer not null, birth_date timestamp, name varchar(255), country_id integer, primary key (id))
Hibernate: create table sponsor (player_id integer not null, brand_id integer not null, primary key (player_id, brand_id))
Hibernate: alter table city add constraint FKrpd7j1p7yxr784adkx4pyepba foreign key (country_id) references country
Hibernate: alter table competition add constraint FKla5bm2uamw3isi4wxsbr1udoc foreign key (host_country_id) references country
Hibernate: alter table competition_instance add constraint FKptejf6vw5rw4bod93va4yhuh7 foreign key (competition_id) references competition
Hibernate: alter table competition_instance add constraint FKpr4qkav6tp9ifmryrntp6bqsd foreign key (winner_id) references player
Hibernate: alter table player add constraint FKb21w76q5ho5gx5270qg5docnt foreign key (country_id) references country
Hibernate: alter table sponsor add constraint FKoddxeuwejcf8d2iuq3po95ccm foreign key (brand_id) references brand
Hibernate: alter table sponsor add constraint FK50nfog36ktua1dilq0w69rtw7 foreign key (player_id) references player
2021-12-17 12:02:49.284  INFO 13096 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
2021-12-17 12:02:49.292  INFO 13096 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2021-12-17 12:02:49.511  WARN 13096 --- [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2021-12-17 12:02:49.777  INFO 13096 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2021-12-17 12:02:49.786  INFO 13096 --- [           main] i.e.tennis.TennisPlayerApplication       : Started TennisPlayerApplication in 2.603 seconds (JVM running for 3.352)
2021-12-17 12:02:49.787  INFO 13096 --- [           main] ication$$EnhancerBySpringCGLIB$$29450931 : Get all via repo
Hibernate: select player0_.id as id1_5_, player0_.birth_date as birth_da2_5_, player0_.country_id as country_4_5_, player0_.name as name3_5_ from player player0_
Hibernate: select victories0_.winner_id as winner_i4_3_0_, victories0_.id as id1_3_0_, victories0_.id as id1_3_1_, victories0_.competition_id as competit3_3_1_, victories0_.winner_id as winner_i4_3_1_, victories0_.year as year2_3_1_ from competition_instance victories0_ where victories0_.winner_id=?
Hibernate: select country0_.id as id1_4_0_, country0_.name as name2_4_0_ from country country0_ where country0_.id=?
Hibernate: select cities0_.country_id as country_3_1_0_, cities0_.id as id1_1_0_, cities0_.id as id1_1_1_, cities0_.country_id as country_3_1_1_, cities0_.name as name2_1_1_ from city cities0_ where cities0_.country_id=?
Hibernate: select victories0_.winner_id as winner_i4_3_0_, victories0_.id as id1_3_0_, victories0_.id as id1_3_1_, victories0_.competition_id as competit3_3_1_, victories0_.winner_id as winner_i4_3_1_, victories0_.year as year2_3_1_ from competition_instance victories0_ where victories0_.winner_id=?
Hibernate: select country0_.id as id1_4_0_, country0_.name as name2_4_0_ from country country0_ where country0_.id=?
Hibernate: select cities0_.country_id as country_3_1_0_, cities0_.id as id1_1_0_, cities0_.id as id1_1_1_, cities0_.country_id as country_3_1_1_, cities0_.name as name2_1_1_ from city cities0_ where cities0_.country_id=?
Hibernate: select victories0_.winner_id as winner_i4_3_0_, victories0_.id as id1_3_0_, victories0_.id as id1_3_1_, victories0_.competition_id as competit3_3_1_, victories0_.winner_id as winner_i4_3_1_, victories0_.year as year2_3_1_ from competition_instance victories0_ where victories0_.winner_id=?
Hibernate: select country0_.id as id1_4_0_, country0_.name as name2_4_0_ from country country0_ where country0_.id=?
Hibernate: select cities0_.country_id as country_3_1_0_, cities0_.id as id1_1_0_, cities0_.id as id1_1_1_, cities0_.country_id as country_3_1_1_, cities0_.name as name2_1_1_ from city cities0_ where cities0_.country_id=?
Hibernate: select victories0_.winner_id as winner_i4_3_0_, victories0_.id as id1_3_0_, victories0_.id as id1_3_1_, victories0_.competition_id as competit3_3_1_, victories0_.winner_id as winner_i4_3_1_, victories0_.year as year2_3_1_ from competition_instance victories0_ where victories0_.winner_id=?
Hibernate: select country0_.id as id1_4_0_, country0_.name as name2_4_0_ from country country0_ where country0_.id=?
Hibernate: select cities0_.country_id as country_3_1_0_, cities0_.id as id1_1_0_, cities0_.id as id1_1_1_, cities0_.country_id as country_3_1_1_, cities0_.name as name2_1_1_ from city cities0_ where cities0_.country_id=?
2021-12-17 12:02:49.934  INFO 13096 --- [           main] ication$$EnhancerBySpringCGLIB$$29450931 : [Djokovic, Monfils, Isner, Nadal]
2021-12-17 12:02:49.935  INFO 13096 --- [           main] ication$$EnhancerBySpringCGLIB$$29450931 : Get all via HQL
Hibernate: select player0_.id as id1_5_0_, brand2_.id as id1_0_1_, victories3_.id as id1_3_2_, competitio4_.id as id1_2_3_, country5_.id as id1_4_4_, cities6_.id as id1_1_5_, player0_.birth_date as birth_da2_5_0_, player0_.country_id as country_4_5_0_, player0_.name as name3_5_0_, brand2_.name as name2_0_1_, sponsoredb1_.player_id as player_i1_6_0__, sponsoredb1_.brand_id as brand_id2_6_0__, victories3_.competition_id as competit3_3_2_, victories3_.winner_id as winner_i4_3_2_, victories3_.year as year2_3_2_, victories3_.winner_id as winner_i4_3_1__, victories3_.id as id1_3_1__, competitio4_.host_country_id as host_cou3_2_3_, competitio4_.name as name2_2_3_, country5_.name as name2_4_4_, cities6_.country_id as country_3_1_5_, cities6_.name as name2_1_5_, cities6_.country_id as country_3_1_2__, cities6_.id as id1_1_2__ from player player0_ left outer join sponsor sponsoredb1_ on player0_.id=sponsoredb1_.player_id left outer join brand brand2_ on sponsoredb1_.brand_id=brand2_.id left outer join competition_instance victories3_ on player0_.id=victories3_.winner_id left outer join competition competitio4_ on victories3_.competition_id=competitio4_.id left outer join country country5_ on player0_.country_id=country5_.id left outer join city cities6_ on country5_.id=cities6_.country_id
2021-12-17 12:02:49.959  INFO 13096 --- [           main] ication$$EnhancerBySpringCGLIB$$29450931 : [Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Monfils, Monfils, Monfils, Monfils, Monfils, Monfils, Isner, Isner, Isner, Nadal, Nadal]
```

As you can see:

* Following the log line containing "Get all via repo", Hibernate will issue 13 queries, which is more than necessary to get all the data required if one were a bit smart. This is a toy example with only 4 players, but we get essentially 3 queries per player + 1 initial query, which means this increases linearly with the number of players.

* Following the log line containing "Get all via HQL", Hibernate will issue a single query, which is great but because of all the joins, the result set contains 35 rows (as one can see from the following line: `[Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Djokovic, Monfils, Monfils, Monfils, Monfils, Monfils, Monfils, Isner, Isner, Isner, Nadal, Nadal]`). This increases *geometrically* with the size of any table.

Ideally, we would issue:
* one query to get all the players
* one query to get all necessary countries
* one query to get all necessary cities
* one query to get all necessary competition instances
* one query to get all necessary competitions
* one query to get all sponsorships
* one query to get all necessary brands
for a total of 7 queries and 45 rows returned. Yes, this is less than 35, but this will increase *linearly* with the size of any table, plus every row will contain a lot less data.