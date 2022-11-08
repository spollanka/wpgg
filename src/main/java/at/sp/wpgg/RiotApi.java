package at.sp.wpgg;

import at.sp.wpgg.summoner.Summoner;
import lombok.NoArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

public class RiotApi implements Serializable {

    // Api and Api-Utils //
    private final String API_BASE = "api.riotgames.com";
    private final String API_KEY = "RGAPI-b55f908b-e520-4f83-b1a4-d936894bc8dc";
    private final String HTTPS = "https://";
    private enum SummonerRegion {
        BRASIL_1("br1"),
        EUROPE_NORTH_EAST_1("eun1"),
        EUROPE_WEST_1("euw1"),
        JAPAN_1("jp1"),
        KOREA("kr"),
        LATIN_AMERICA_1("la1"),
        LATIN_AMERICA_2("la2"),
        NORTH_AMERICA_1("na1"),
        OCEANIA_1("oc1"),
        RUSSIA("ru"),
        TURKEY("tr1");

        public final String label;

        SummonerRegion(String label) {
            this.label = label;
        }
    }

    // Api calls //

    // gets a summoner by their summoner name + region //
    private final String SUMMONER_BY_NAME = "/lol/summoner/v4/summoners/by-name/";

    private final RestTemplate restTemplate;

    public RiotApi() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        this.restTemplate = restTemplateBuilder.build();
    }

    public Summoner SummonerBySummonerName(String summonerName, String region) {

        String url = HTTPS + region + "." + API_BASE + SUMMONER_BY_NAME + summonerName;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", API_KEY);
        HttpEntity request = new HttpEntity(headers);

        // This whole section will be improved in the future, for now it works sufficiently
        try {
            ResponseEntity<Summoner> response = this.restTemplate.exchange(url, HttpMethod.GET, request, Summoner.class, 1);
            return response.getBody();
        }
        catch (Exception e){
            return new Summoner(-1);
        }
    }
}
