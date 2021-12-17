package io.example.tennis;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Player{
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Sponsor",
            joinColumns = {
                    @JoinColumn(name = "player_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "brand_id", referencedColumnName = "id")
            }
    )
    private Set<Brand> sponsoredBy;

    @OneToMany(mappedBy="winner", fetch = FetchType.LAZY)
    private Set<CompetitionInstance> victories;

    @Column
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;

    public Player(String name, Country country, Date birthDate) {
        super();
        this.name = name;
        this.country = country;
        this.birthDate = birthDate;
    }

    public String toString() {
        return name;
    }
}
