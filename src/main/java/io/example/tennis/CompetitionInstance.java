package io.example.tennis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class CompetitionInstance {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Competition competition;

    @ManyToOne(fetch = FetchType.LAZY)
    private Player winner;

    @Column
    private int year;

    // Constructor without id attribute
    public CompetitionInstance(int year, Player winner) {
        super();
        this.year = year;
        this.winner = winner;
    }

    public String toString() {
        return String.format("%s %d", competition.getName(), year);
    }
}
