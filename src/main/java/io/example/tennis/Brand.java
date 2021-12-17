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
import javax.persistence.ManyToMany;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Brand {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @ManyToMany(mappedBy="sponsoredBy", fetch = FetchType.LAZY)
    private Set<Player> sponsorOf;

    // Constructor without id attribute
    public Brand(String name, Set<Player> sponsorOf) {
        super();
        this.name = name;
        this.sponsorOf = sponsorOf;
    }

    public String toString() {
        return name;
    }
}
