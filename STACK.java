import java.util.*;
import javax.swing.*;

public class STACK {
    public static void main(String[] args) {
        // Phase 1 & 2: Read data and route into queues
        // #1
        LinkedList<CrewMemberInfo> crewList = DataReader.readCrewData("onepiece_combined.txt");

        Queue<CrewMemberInfo> eastBlueQueue = new LinkedList<>();
        Queue<CrewMemberInfo> southBlueQueue = new LinkedList<>();
        Queue<CrewMemberInfo> grandLineQueue = new LinkedList<>();

        boolean assignToEast = true;

        for (CrewMemberInfo member : crewList) {
            if (member.getMissionCount() > 3) {
                grandLineQueue.add(member);
            } else {
                if (assignToEast) {
                    eastBlueQueue.add(member);
                } else {
                    southBlueQueue.add(member);
                }
                assignToEast = !assignToEast;
            }
        }

        // Phase 3: Create stack to store completed pirates
        Stack<CrewMemberInfo> completeStack = new Stack<>();

        // Process in batches of 5 per route
        // #2 loop
        while (!eastBlueQueue.isEmpty() || !southBlueQueue.isEmpty() || !grandLineQueue.isEmpty()) {
            processBatch(eastBlueQueue, completeStack, "East Blue");
            processBatch(southBlueQueue, completeStack, "South Blue");
            processBatch(grandLineQueue, completeStack, "Grand Line");
        }

        // Display completed pirates from the stack (LIFO)
        System.out.println("\n===★ COMPLETED MISSION STACK ★===");
        while (!completeStack.isEmpty()) {
            CrewMemberInfo completed = completeStack.pop();
            System.out.println("Pirate Name : " + completed.getMemberName());
            System.out.println("Pirate Crew : " + completed.getPirateCrew());
            System.out.println("Missions Completed:");
            for (MissionInfo m : completed.getMissions()) {
                System.out.println(" - " + m);
            }
            System.out.println("Total Bounty Reward: " + completed.getTotalBounty() + " Berries");
            System.out.println("========================================");
        }

        // Call search function after displaying completed stack
        searchPirate(crewList);
    }

    // Helper method to process 5 pirates from a queue
    // #3 
    public static void processBatch(Queue<CrewMemberInfo> queue, Stack<CrewMemberInfo> stack, String routeName) {
        int count = 0;
        while (!queue.isEmpty() && count < 5) {
            CrewMemberInfo crew = queue.poll(); // Dequeue pirate
            System.out.println("-----------------------------------");
            System.out.println("Pirate dequeued from " + routeName + ": " + crew.getMemberName());
            System.out.println("-----------------------------------");
            stack.push(crew); // Push to completeStack after processing
            count++;
        }
    }

    // Search method (GUI)
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
