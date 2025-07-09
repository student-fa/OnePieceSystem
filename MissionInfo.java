public class MissionInfo
{
    private String missionId;
    private String missionType;
    private int dangerLevel;
    private String missionDate;
    private String expectedDuration;
    private int bountyReward;
    
    // Constructor
    public MissionInfo(String missionId,String missionType, int dangetLevel,String missionDate,String expectedDuration,int bountyReward)
    {
        this.missionId = missionId;
        this.missionType = missionType;
        this.dangerLevel = dangerLevel;
        this.missionDate = missionDate;
        this.expectedDuration = expectedDuration;
        this.bountyReward = bountyReward;
    }
    
    // Getter method
    public String getMissionId() {
        return missionId;
    }
    public String getMissionType() {
        return missionType;
    }
    public int getDangerLevel() {
        return dangerLevel;
    }
    public String getMissionDate() {
        return missionDate;
    }
    public String getExpectedDuration() {
        return expectedDuration;
    }
    public int getBountyReward() {
        return bountyReward;
    }

    // toString for displaying mission details
    public String toString() {
        return "Mission ID: " + missionId + ", Type: " + missionType + ", Danger: " + dangerLevel +
               ", Date: " + missionDate + ", Duration: " + expectedDuration + ", Bounty: " + bountyReward + " Berries";
    }
}