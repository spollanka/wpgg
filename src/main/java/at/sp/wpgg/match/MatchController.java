package at.sp.wpgg.match;

import at.sp.wpgg.match.Match;
import at.sp.wpgg.match.MatchService;
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
}