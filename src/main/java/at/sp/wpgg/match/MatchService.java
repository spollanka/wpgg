package at.sp.wpgg.match;

import at.sp.wpgg.RiotApi;
import at.sp.wpgg.riotmatchtimelineobject.RiotMatchTimelineObject;
import at.sp.wpgg.summoner.SummonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    private final SummonerService summonerService;
    private final RestTemplate restTemplate;
    private final RiotApi riotApi;

    @Autowired
    public MatchService(MatchRepository matchRepository, SummonerService summonerService, RestTemplateBuilder restTemplateBuilder) {
        this.matchRepository = matchRepository;
        this.summonerService = summonerService;
        this.restTemplate = restTemplateBuilder.build();
        this.riotApi = new RiotApi();
    }


    public Match getMatchByMatchId(String matchId, String region) {

        RiotMatchTimelineObject matchObject = this.riotApi.GetMatchObjectByMatchId(matchId, region);


        Match match = new Match();
        String summonerRegion = matchId.split("_")[0];

        match.setMatchId(matchObject.metadata.matchId);

        for (String puuid : matchObject.metadata.participants) {
            this.summonerService.getSummonerByPuuid(puuid, summonerRegion).addMatch(match);
        }
        this.matchRepository.save(match);

        return match;
    }

}
