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
public class City {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    public City(String name, Country country) {
        super();
        this.name = name;
        this.country = country;
    }

    public String toString() {
        return name;
    }
}
