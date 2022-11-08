package at.sp.wpgg.summoner;

import at.sp.wpgg.RiotApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class SummonerService {

    private final SummonerRepository summonerRepository;
    private final RestTemplate restTemplate;
    private final RiotApi riotApi;

    @Autowired
    public SummonerService(SummonerRepository summonerRepository, RestTemplateBuilder restTemplateBuilder) {
        this.summonerRepository = summonerRepository;
        this.restTemplate = restTemplateBuilder.build();
        this.riotApi = new RiotApi();
    }

    /**
     * Tries to get the summoner from the database. If the summoner is not yet present in the db the data will be retrieved directly from Riot and subsequently persisted.
     * @param summonerName Summoner Name
     * @param region Regioncode
     * @return Summonerobject
     */
    public Summoner getSummonerByName(String summonerName, String region) {

        Optional<Summoner> summoner = this.summonerRepository.findSummonerByName(summonerName);

        if (summoner.isPresent()) {
            return summoner.get();
        }

        Summoner summonerApi = riotApi.SummonerBySummonerName(summonerName, region);

        if (summonerApi.getSystemId() == -1) {
            // summoner does not exist and so does the code to handle it //
        } else if (summonerApi.getSystemId() == -1) {
            // error handling 2b implemented here //
        }

        return summonerApi;
    }
}
