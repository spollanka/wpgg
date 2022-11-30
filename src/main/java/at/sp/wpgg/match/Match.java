package at.sp.wpgg.match;

import at.sp.wpgg.summoner.Summoner;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(
        name="match"
)

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Match {

    @Id
    @SequenceGenerator(
            name = "match_sequence",
            sequenceName = "match_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "match_sequence"
    )
    @Column(
            name="systemId"
    )
    private long systemId;

    @Column(
            name="matchId"
    )
    private String matchId;

    @ManyToMany(
            mappedBy = "matches"
    )
    @JsonBackReference
    private Set<Summoner> participants = new HashSet<>();
}
