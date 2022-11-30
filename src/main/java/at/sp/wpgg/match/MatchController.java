package at.sp.wpgg.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static at.sp.wpgg.Properties.*;

@RestController
@RequestMapping(path = "api/" + API_VERSION + "/match")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    private MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping(path = "{region}/{matchId}" )
    public Match GetMatchById (@PathVariable("region") String region,
                               @PathVariable("matchId") String matchId) {

        Match m = matchService.getMatchByMatchId(matchId, region);
        System.out.println(m.getMatchId() + ": " + m.getParticipants());
        return m;

        //return matchService.getMatchByMatchId(matchId, region);
    }
}