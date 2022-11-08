package at.sp.wpgg.summoner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static at.sp.wpgg.Properties.*;

@RestController
@RequestMapping(path = "api/" + API_VERSION + "/summoner")
public class SummonerController {

    private final SummonerService summonerService;

    @Autowired
    private SummonerController(SummonerService summonerService) {
        this.summonerService = summonerService;
    }

    @GetMapping(path = "{region}/{summonerName}")
    public Summoner GetSummonerByName (@PathVariable("region") String region,
                                       @PathVariable("summonerName") String summonerName) {
        return summonerService.getSummonerByName(summonerName, region); }

}
