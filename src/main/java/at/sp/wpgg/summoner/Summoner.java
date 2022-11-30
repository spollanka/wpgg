package at.sp.wpgg.summoner;

import at.sp.wpgg.match.Match;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(
        name = "summoner"
)

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Summoner {

    @Id
    @SequenceGenerator(
            name = "summoner_sequence",
            sequenceName = "summoner_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "summoner_sequence"
    )
    @Column(
            name = "systemId"
    )
    private long systemId;

    @Column(
            name = "accountId"
    )
    private String accountId;

    @Column(
            name = "profileIconId"
    )
    private int profileIconId;

    @Column(
            name = "revisionDate"
    )
    private long revisionDate;

    @Column(
            name = "name"
    )
    private String name;

    @Column(
            name = "id",
            length = 63
    )
    private String id;

    @Column(
            name = "puuid",
            length = 78
    )
    private String puuid;

    @Column(
            name = "start_timestamp"
    )
    private Timestamp startTimestamp;



    //foreign keys

    //Matches


    //@ManyToOne
    //@JoinColumn(
    //        name = "customer_id"
    //)
    //@JsonBackReference
    //private Customer customer;


    //Mapping for foreign keys
    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "summoner_matches"
            //,joinColumns = {
            //        @JoinColumn(
            //                name = "summoner"
            //        )
            //},
            //inverseJoinColumns = {
            //        @JoinColumn(
            //                name = "match"
            //        )
            //}
    )
    private Set<Match> matches = new HashSet<>();

    public void addMatch(Match match) {
        this.matches.add(match);
        match.getParticipants().add(this);
    }



    //defaults
    //@PrePersist
    //void preInsert() {
    //    if(endTimestamp == null) {
    //        endTimestamp = Timestamp.valueOf("2999-12-31 23:59:59");
    //    }
    //}

    public Summoner (long systemId) {
        this.systemId = systemId;
    }

}