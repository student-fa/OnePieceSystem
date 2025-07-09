import java.util.*;
import java.util.LinkedList;

public class CrewMemberInfo
{
    private String memberId;
    private String memberName;
    private String pirateCrew;
    private LinkedList<MissionInfo> missions;
    
    // Constructor
    public CrewMemberInfo(String memberId, String memberName, String pirateCrew)
    {
        this.memberId = memberId;
        this.memberName = memberName;
        this.pirateCrew = pirateCrew;
        this.missions = new LinkedList<>();
        
    }
    
    // Getter method
    public String getMemberId()
    {
        return memberId;
    }
    public String getMemberName()
    {
        return memberName;
    }
    public String getPirateCrew()
    {
        return pirateCrew;
    }
    public LinkedList<MissionInfo> getMissions()
    {
        return missions;
    }
    
    // Add mission
    public void addMission(MissionInfo mission)
    {
      missions.add(mission);  
    }
    
    // Count the mission that the crew has
    public int getMissionCount() 
    {
    return missions.size();
    }
    
    // Total all of the bounty from the mission
    public int getTotalBounty() 
    {
        int total = 0;
        for (MissionInfo m : missions) 
        {
            total += m.getBountyReward(); // add bounty every mission
        }
        return total;
    }

    
    // toString
    public String toString()
    {
        String result = "Crew Member ID: " + memberId+ ", Name: " + memberName +", Crew: " + pirateCrew + "\nMissions:\n";
        
        if (missions.isEmpty())
        {
            result = " No mission assigned..\n";
        }else{
            for (MissionInfo mission : missions)
            {
                result =""+ mission.toString()+"\n";
            }
        }
        return result;
    }
}