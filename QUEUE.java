import java.util.*;
import javax.swing.*;
import java.io.*;

public class QUEUE 
{
    public static void main(String[] args) 
    {
        // Read data from file
        LinkedList<CrewMemberInfo> crewList = DataReader.readCrewData("onepiece_combined.txt");
        
        // Create 3 queue to store crew members based on their mission count
        Queue<CrewMemberInfo> eastBlueQueue = new LinkedList<>();
        Queue<CrewMemberInfo> southBlueQueue = new LinkedList<>();
        Queue<CrewMemberInfo> grandLineQueue = new LinkedList<>();
        
        // Used to alternate between East and South Blue routes
        boolean assignToEast = true;
        
        // Go through each crew member and assign them to a queue
        for (CrewMemberInfo member : crewList)
        {
            if (member.getMissionCount() > 3) 
            {
                grandLineQueue.add(member);
            } 
            else 
            {
                if (assignToEast) 
                {
                    eastBlueQueue.add(member);
                } else {
                    southBlueQueue.add(member);
                }
                assignToEast = !assignToEast;
            }
        }

        // Display the contents of each queue using a helper method
        DisplayQueue("East Blue Route", eastBlueQueue);
        DisplayQueue("South Blue Route", southBlueQueue);
        DisplayQueue("Grand Line Route", grandLineQueue);

        // Call search function after displaying queues
        searchPirate(crewList);
    }
    
    public static void DisplayQueue(String routeName, Queue<CrewMemberInfo> queue) 
    {
        System.out.println("=== " + routeName + " ===");

        for (CrewMemberInfo member : queue) 
        {
            System.out.println("Pirate Name : " + member.getMemberName());
            System.out.println("Pirate Crew : " + member.getPirateCrew());
            System.out.println("Missions Assigned : ");

            for (MissionInfo m : member.getMissions()) 
            {
                System.out.println(" - " + m);
            }
            
            System.out.println("Total Bounty : " + member.getTotalBounty() + " Berries");
            System.out.println("-------------------------------");
        }
    }

    // Search method
    public static void searchPirate(LinkedList<CrewMemberInfo> crewList) {
        String name = JOptionPane.showInputDialog("Enter Pirate Name to Search:");
        boolean found = false;

        for (CrewMemberInfo c : crewList) {
            if (c.getMemberName().equalsIgnoreCase(name.trim())) {
                StringBuilder sb = new StringBuilder();
                sb.append("Pirate Name: ").append(c.getMemberName())
                  .append("\nPirate Crew: ").append(c.getPirateCrew())
                  .append("\nMissions: ");

                for (MissionInfo m : c.getMissions()) {
                    sb.append("\n- ").append(m.toString());
                }

                sb.append("\nTotal Bounty Reward: ").append(c.getTotalBounty()).append(" Berries");
                JOptionPane.showMessageDialog(null, sb.toString());
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "Pirate not found.");
        }
    }
}
