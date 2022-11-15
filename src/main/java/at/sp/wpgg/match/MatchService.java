package at.sp.wpgg.match;

import at.sp.wpgg.RiotApi;
import at.sp.wpgg.match.MatchRepository;
import at.sp.wpgg.riotmatchobject.RiotMatchObject;
import at.sp.wpgg.summoner.SummonerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final RestTemplate restTemplate;
    private final RiotApi riotApi;

    @Autowired
    public MatchService(MatchRepository matchRepository, RestTemplateBuilder restTemplateBuilder) {
        this.matchRepository = matchRepository;
        this.restTemplate = restTemplateBuilder.build();
        this.riotApi = new RiotApi();
    }


    public Match GetMatchByMatchId(String matchId, String region) {

        RiotMatchObject matchObject = this.riotApi.GetMatchObjectByMatchId(matchId, region);

        System.out.println(matchObject.metadata.dataVersion);

        return new Match();
    }

}
