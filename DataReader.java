import java.util.*;
import java.io.*;

public class DataReader
{
   public static LinkedList<CrewMemberInfo> readCrewData(String onepiece_combined)
   {
       LinkedList<CrewMemberInfo> crewList = new LinkedList<>();
       
       try{
           BufferedReader in = new BufferedReader(new FileReader(onepiece_combined));
           String indata;
           
           while ((indata = in.readLine()) != null)
           {
               StringTokenizer st = new StringTokenizer(indata, ";");
               
               // Read by line
               String memberId = st.nextToken();
                String memberName = st.nextToken();
                String pirateCrew = st.nextToken();
                String missionId = st.nextToken();
                String missionType = st.nextToken();
                int dangerLevel = Integer.parseInt(st.nextToken());
                String missionDate = st.nextToken();
                String expectedDuration = st.nextToken();
                int bountyReward = Integer.parseInt(st.nextToken());
                
               // Create missionInfo object
               MissionInfo mission = new MissionInfo(missionId, missionType, dangerLevel, missionDate, expectedDuration, bountyReward);
               
               // Check if the data already exists
               CrewMemberInfo existingCrew = null;
               for (int i = 0; i< crewList.size(); i++)
               {
                 if (crewList.get(i).getMemberId().equalsIgnoreCase(memberId))
                 {
                     existingCrew = crewList.get(i);
                     break;
                 }
               }
               if (existingCrew == null) {
                    CrewMemberInfo newCrew = new CrewMemberInfo(memberId, memberName, pirateCrew);
                    newCrew.addMission(mission);
                    crewList.add(newCrew);
                } else {
                    existingCrew.addMission(mission);
                } 
           }
           in.close();
        }
        catch (IOException e)
        {
          System.out.println("Error reading file : " +e.getMessage());  
        }
        return crewList;
    }
}