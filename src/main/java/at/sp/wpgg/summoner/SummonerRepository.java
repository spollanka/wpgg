package at.sp.wpgg.summoner;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SummonerRepository extends JpaRepository<Summoner, Long> {
    public Optional<Summoner> findSummonerByName(String name);
}
