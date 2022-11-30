package at.sp.wpgg.riotmatchtimelineobject;
import java.util.ArrayList;

public class Event{
    public Object realTimestamp;
    public int timestamp;
    public String type;
    public int creatorId;
    public String wardType;
    public String levelUpType;
    public int participantId;
    public int skillSlot;
    public int itemId;
    public int level;
    public ArrayList<String> assistingParticipantIds;
    public int bounty;
    public int killStreakLength;
    public int killerId;
    public Position position;
    public int shutdownBounty;
    public ArrayList<VictimDamageReceived> victimDamageReceived;
    public int victimId;
    public String killType;
    public ArrayList<VictimDamageDealt> victimDamageDealt;
    public String laneType;
    public int teamId;
    public int afterId;
    public int beforeId;
    public int goldGain;
    public int multiKillLength;
    public String buildingType;
    public String towerType;
    public int killerTeamId;
    public String monsterType;
    public String monsterSubType;
    public int actualStartTime;
    public long gameId;
    public int winningTeam;
}
