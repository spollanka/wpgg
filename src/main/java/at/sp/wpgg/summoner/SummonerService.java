package at.sp.wpgg.summoner;

import at.sp.wpgg.RIOT_API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SummonerService {

    private final SummonerRepository summonerRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public SummonerService(SummonerRepository summonerRepository, RestTemplateBuilder restTemplateBuilder) {
        this.summonerRepository = summonerRepository;
        this.restTemplate = restTemplateBuilder.build();
    }
    public Summoner getSummonerByName(String summonerName, String region) {

        getSummonerFromRiotBySummonerName(summonerName, region);

        return new Summoner();
    }

    public Summoner getSummonerFromRiotBySummonerName(String summonerName, String region) {

        String url = RIOT_API.HTTPS + region + "." + RIOT_API.API_BASE + RIOT_API.SUMMONERBYNAME + summonerName;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", RIOT_API.API_KEY);
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<Object> response = this.restTemplate.exchange(url, HttpMethod.GET, request, Object.class, 1);

        if(response.getStatusCode() == HttpStatus.OK) {
            return new Summoner(response.getBody()., );
        } else if (response.getStatusCode() == HttpStatus.valueOf(404)){
            //return -1 if the summoner does not exist, -2 for undefined errors -> will be changed in the future
            return new Summoner(-1);
        } else return new Summoner(-2);
    }
}
