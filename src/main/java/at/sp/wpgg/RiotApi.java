package at.sp.wpgg;

import at.sp.wpgg.riotmatchobject.RiotMatchObject;
import at.sp.wpgg.summoner.Summoner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.sql.Timestamp;

import static at.sp.wpgg.Properties.RIOT_API_KEY;

public class RiotApi implements Serializable {

    // Api and Api-Utils //
    private final String API_BASE = "api.riotgames.com";
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
    // gets a match by its id + region //
    private final String MATCH_BY_ID = "/lol/match/v5/matches/";

    private final RestTemplate restTemplate;

    public RiotApi() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        this.restTemplate = restTemplateBuilder.build();
    }

    public Summoner GetSummonerBySummonerName(String summonerName, String region) {

        String url = HTTPS + region + "." + API_BASE + SUMMONER_BY_NAME + summonerName;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", RIOT_API_KEY);
        HttpEntity request = new HttpEntity(headers);

        // This whole section will be improved in the future, for now it works sufficiently
        try {
            ResponseEntity<Summoner> response = this.restTemplate.exchange(url, HttpMethod.GET, request, Summoner.class, 1);
            Summoner s = response.getBody();
            s.setStartTimestamp(new Timestamp(System.currentTimeMillis()));
            return s;
        }
        catch (Exception e){
            return new Summoner(-1);
        }
    }

    public RiotMatchObject GetMatchObjectByMatchId(String matchId, String region) {

        String url = HTTPS + region + "." + API_BASE + MATCH_BY_ID + matchId + "/timeline";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", RIOT_API_KEY);
        HttpEntity request = new HttpEntity(headers);

        // This whole section will be improved in the future, for now it does not even work sufficiently
        try {
            ResponseEntity<RiotMatchObject> response = this.restTemplate.exchange(url, HttpMethod.GET, request, RiotMatchObject.class, 1);
            RiotMatchObject o = response.getBody();
            return o;
        }
        catch (Exception e){
            return new RiotMatchObject();
        }
    }
}
