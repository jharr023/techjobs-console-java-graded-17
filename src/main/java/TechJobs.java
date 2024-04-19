import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.HashSet;

/**
 * Created by LaunchCode
 */
public class TechJobs {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        // Initialize our field map with key/name pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Allow the user to search until they manually quit
        while (true) {

            String actionChoice = getUserSelection("View jobs by (type 'x' to quit):", actionChoices);

            if (actionChoice == null) {
                break;
            } else if (actionChoice.equals("list")) {

                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice.equals("all")) {
                    printJobs(JobData.findAll());
                } else {

                    ArrayList<String> results = JobData.findAll(columnChoice);

                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Print list of skills, employers, etc
                    for (String item : results) {
                        System.out.println(item);
                    }
                }

            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", columnChoices);

                // What is their search term?
                System.out.println("\nSearch term:");
                String searchTerm = in.nextLine();

                //added for task 2:
                ArrayList<HashMap<String, String>> searchResults = JobData.findByValue(searchTerm);
                printJobs(searchResults);

                if (searchField.equals("all")) {
                    printJobs(JobData.findByValue(searchTerm));
                } else {
                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                }
            }
        }
    }

    // ﻿Returns the key of the selected item from the choices Dictionary
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        int choiceIdx = -1;
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        int i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);

            // Print available choices
            for (int j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }

            if (in.hasNextInt()) {
                choiceIdx = in.nextInt();
                in.nextLine();
            } else {
                String line = in.nextLine();
                boolean shouldQuit = line.equals("x");
                if (shouldQuit) {
                    return null;
                }
            }

            // Validate user's input
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while (!validChoice);

        return choiceKeys[choiceIdx];
    }

    // Print a list of jobs
    // Flags to track if results have been printed

    //ver 3

       private static boolean noResultsPrinted = false;
    private static boolean searchResultsPrinted = false;


    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {
        if (someJobs.isEmpty()) {
            // Check if no results have been printed yet
            if (!noResultsPrinted) {
                System.out.println("Search term:");
                System.out.println();
                System.out.println("No Results");
                noResultsPrinted = true; // Update flag to indicate no results have been printed
            }
        } else {
            // Check if search results have been printed yet
            if (!searchResultsPrinted) {
                for (HashMap<String, String> job : someJobs) {
                    System.out.println();
                    System.out.println("*****");
                    System.out.println("position type: " + job.get("position type"));
                    System.out.println("name: " + job.get("name"));
                    System.out.println("employer: " + job.get("employer"));
                    System.out.println("location: " + job.get("location"));
                    System.out.println("core competency: " + job.get("core competency"));
                    System.out.println("*****");
                }
                searchResultsPrinted = true; // Update flag to indicate search results have been printed
            }
        }
        //System.out.println(); // Add a blank line after printing jobs
    }
}

    /* ver2 commented out to test ver3
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {
        if (someJobs.isEmpty() && !noResultsPrinted) {
            System.out.println("Search term:");
            //System.out.println();
            System.out.println("No Results");

            // Set the flag to true to indicate that the message has been printed
            noResultsPrinted = true;
        } else {
            // Print jobs
            for (HashMap<String, String> job : someJobs) {
                System.out.println("*****");
                System.out.println("position type: " + job.get("position type"));
                System.out.println("name: " + job.get("name"));
                System.out.println("employer: " + job.get("employer"));
                System.out.println("location: " + job.get("location"));
                System.out.println("core competency: " + job.get("core competency"));
                System.out.println("*****");
                System.out.println(); // Add a blank line between listings
            }
        }
    }
}*/

    //commented out to attempt test pass BASIC OG
     /* private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {
        if (someJobs.isEmpty()) {
            System.out.println("Search term:");
            //System.out.println("Example Search Term with No Results");
            System.out.println("No Results");

        }

        for (HashMap<String, String> job : someJobs) {
            System.out.println("*****");
            System.out.println("position type: " + job.get("position type"));
            System.out.println("name: " + job.get("name"));
            System.out.println("employer: " + job.get("employer"));
            System.out.println("location: " + job.get("location"));
            System.out.println("core competency: " + job.get("core competency"));
            System.out.println("*****");
            System.out.println(); // Add a blank line between listings
        }
    }
} */
