package at.sp.wpgg;

import at.sp.wpgg.summoner.Summoner;

public class RIOT_API {
    private static final String API_BASE = "api.riotgames.com";
    private static final String API_KEY = "RGAPI-8eb1c5bd-c659-4ca7-82c2-30666432f172";
    private static final String HTTPS = "https://";
    private static final String SUMMONER_BY_NAME = "/lol/summoner/v4/summoners/by-name/";

    public Summoner SummonerBySummonerName(String summonerName, String region) {

        String url = HTTPS + region + "." + API_BASE + SUMMONER_BY_NAME + summonerName;
    }
}
