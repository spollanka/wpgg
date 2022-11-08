package at.sp.wpgg.match;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
            generator = "summoner_sequence"
    )
    @Column(
            name="systemId"
    )
    private long systemId;

    @ElementCollection
    @CollectionTable (
            name = "match_participants",
            joinColumns = @JoinColumn( name = "participant_id",
            foreignKey = @ForeignKey())
    )
    @Column(
            name = "participantPuuids"
    )
    private List<String> participantsPuuids;
}
