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
public class Competition {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Country hostCountry;

    // Constructor without id attribute
    public Competition(String name, Country hostCountry, int year, Player winner) {
        super();
        this.name = name;
        this.hostCountry = hostCountry;
    }

    public String toString() {
        return name;
    }
}
