package View;

import ClubTypes.DefaultFootballClub;
import ClubTypes.FootballClub;
import ClubTypes.SchoolFootballClub;
import ClubTypes.UniversityFootballClub;
import Interface.Accessories.Date;
import Interface.PremierLeagueManager;
import javafx.application.Platform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/*
Name 	: 	Ramindu Walgama
UOW ID 	:	w1790183
ID 		: 	2019730
*/

public class CLI{
	static Scanner sc = new Scanner(System.in);
	private static PremierLeagueManager leagueManager = new PremierLeagueManager();

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Platform.setImplicitExit(false);

		leagueManager.read();

		//When the CLI runs, open the GUI in background.
		new Thread() {
			@Override
			public void run(){
				super.run();
				GUI.main();
			}
		}.start();

		System.out.print(displayMenu());
		String input = sc.nextLine();

		do{
			switch(input){
				case "1":
					promptForCreateFC();
					break;
				case "2":
					promptForDeleteFC();
					break;
				case "3":
					showClub();
					break;
				case "4":
					promptLeagueTable();
					break;
				case "5":
					promptForAddMatch();
					break;
				case "6":
					leagueManager.save();
					System.out.println("Saved successfully");
					break;
				case "7":
					System.out.println("Opening GUI....");
					Platform.runLater(GUI::stageShow);
					break;
				default:
					System.out.println("Please select from the given options.");
			}

			System.out.print(displayMenu());
			input = sc.nextLine();

		} while (!input.equals("-1"));
	}

	private static String displayMenu(){
		return 
                "\t\t\t\t 1  ------- Create a new football club and add it in the premier league \n" +
                "\t\t\t\t 2  ------- Delete an existing club from the premier league \n" +
                "\t\t\t\t 3  ------- Display statistics for a selected club \n" +
                "\t\t\t\t 4  ------- Display the Premier League Table \n" +
                "\t\t\t\t 5  ------- Add a played match \n" +
                "\t\t\t\t 6  ------- Save \n" +
                "\t\t\t\t 7  ------- Launch GUI \n" +
                "\t\t\t\t -1  ------ Exit \n" +
                "\n\tSelect one : ";
	}

	//taking inputs for create a new football clubs and parsing to PremierLeagueManager Class to add them to a array list
	private static void promptForCreateFC(){
		String fcSelection;
		String clubName;
		String location;
		int[] clubScores;

        System.out.print(
                "Select one option form below,\n" +
                        "1 -------- Default Football Club \n" +
                        "2 -------- University Football Club \n" +
                        "3 -------- School Football Club \n" +
                        "4 -------- Back to main menu \n" +
                        "Select : "
        );

        fcSelection = sc.nextLine();

		while (!fcSelection.equals("4")) {

			//input for Normal clubs
			if ("1".equals(fcSelection)) {
				System.out.println("Enter club name : ");
				clubName = sc.nextLine();
				//check whether the club exists. If exists taking another input for clubName
				while (clubExists(clubName) != -1) {
					System.out.println("Club already Exists.\nEnter club name : ");
					clubName = sc.nextLine();
				}
				System.out.println("Enter club location : ");
				location = sc.nextLine();
				clubScores = promptFCInputInfo();
				//parsing as a array to create new FootballClub object and make the program more efficient
				leagueManager.createFootballClub(new DefaultFootballClub(clubName, location, clubScores[0], clubScores[1], clubScores[2], clubScores[3], clubScores[4]));
				System.out.println("Successfully added to the Football Premier League\n");
				System.out.print(
						"Select one option form below,\n" +
								"1 -------- Default Football Club \n" +
								"2 -------- University Football Club \n" +
								"3 -------- School Football Club \n" +
								"4 -------- Back to main menu \n" +
								"Select : "
				);
				fcSelection = sc.nextLine();

				//input for University Football Clubs
			} else if ("2".equals(fcSelection)) {
				System.out.println("Enter university name : ");
				String uniName = sc.nextLine();
				System.out.println("Enter university football club name : ");
				clubName = sc.nextLine();
				//check whether the club exists. If exists taking another input for clubName
				while (clubExists(clubName) != -1) {
					System.out.println("Club already Exists.\nEnter club name : ");
					clubName = sc.nextLine();
				}
				System.out.println("Enter university location : ");
				location = sc.nextLine();
				System.out.println("Enter the name of the team Captain : ");
				String teamCaptain = sc.nextLine();
				clubScores = promptFCInputInfo();
				//parsing as a array to create new FootballClub object and make the program more efficient
				leagueManager.createFootballClub(new UniversityFootballClub(uniName, clubName, location, teamCaptain, clubScores[0], clubScores[1], clubScores[2], clubScores[3], clubScores[4]));
				System.out.println("Successfully added to the Football Premier League\n");
				System.out.print(
						"Select one option form below,\n" +
								"1 -------- Default Football Club \n" +
								"2 -------- University Football Club \n" +
								"3 -------- School Football Club \n" +
								"4 -------- Back to main menu \n" +
								"Select : "
				);
				fcSelection = sc.nextLine();

				//input for School Football Clubs
			} else if ("3".equals(fcSelection)) {
				System.out.println("Enter school name : ");
				String schoolName = sc.nextLine();
				System.out.println("Enter school football club name : ");
				clubName = sc.nextLine();
				//check whether the club exists. If exists taking another input for clubName
				while (clubExists(clubName) != -1) {
					System.out.println("Club already Exists.\nEnter club name : ");
					clubName = sc.nextLine();
				}
				System.out.println("Enter school location : ");
				location = sc.nextLine();
				System.out.println("Enter the name of the coach : ");
				String coachName = sc.nextLine();
				clubScores = promptFCInputInfo();
				//parsing as a array to create new FootballClub object and make the program more efficient
				leagueManager.createFootballClub(new SchoolFootballClub(schoolName, clubName, location, coachName, clubScores[0], clubScores[1], clubScores[2], clubScores[3], clubScores[4]));
				System.out.println("Successfully added to the Football Premier League\n");
				System.out.print(
						"Select one option form below,\n" +
								"1 -------- Default Football Club \n" +
								"2 -------- University Football Club \n" +
								"3 -------- School Football Club \n" +
								"4 -------- Back to main menu \n" +
								"Select : "
				);
				fcSelection = sc.nextLine();
			//If user input is not option from above list, take another input for selection option
			} else {
				System.out.println("Please select option from given list.");
				fcSelection = sc.nextLine();
			}
		}
	}

	//gather score details to create new football club
	private static int[] promptFCInputInfo(){

		System.out.println("Enter number of wins : ");
		int winCount = trueInt();
		System.out.println("Enter number of draws: ");
		int drawCount = trueInt();
		System.out.println("Enter number of defeats : ");
		int defeatCount = trueInt();
		System.out.println("Enter number of goals scored : ");
		int goalScoredCount = trueInt();
		System.out.println("Enter number of goals received : ");
		int goalReceivedCount = trueInt();
		sc.nextLine();
		//returning as array to easy access
		return new int[] {winCount, drawCount, defeatCount, goalScoredCount, goalReceivedCount};
	}

	//taking name as an input for delete a football club and parsing to PremierLeagueManager Class to delete it from the array list
	private static void promptForDeleteFC(){
		System.out.print("Enter name of the club : ");
		String deleteFC = sc.nextLine();

		//if club doesn't exists prompts error
		if (clubExists(deleteFC) == -1) {
			System.out.println(deleteFC + " club does not exists. Please recheck the name of the club....");
		} else {
			leagueManager.showClubStat(leagueManager.getTeams().get(clubExists(deleteFC)));
			leagueManager.getTeams().remove(clubExists(deleteFC));
			System.out.println(deleteFC + " successfully removed from the League.\n");
		}
	}

	//input name which needs to show the details of
	private static void showClub(){
		System.out.println("Enter the club name : ");
		String clubName = sc.nextLine();

		if (clubExists(clubName) !=-1) {
			leagueManager.showClubStat(leagueManager.getTeams().get(clubExists(clubName)));
		} else {
			System.out.println(clubName + " doesn't exists. Please check the club name");
		}
	}

	//display the premiere league table
	private static void promptLeagueTable(){
		if (leagueManager.getTeams().isEmpty()) {
			System.out.println("League table is not available. Please check teams...");
		} else {
			System.out.println(
							"      " +
							String.format("%-32s", "Club") +
							String.format("%-10s", "Points") +
							String.format("%-10s", "Played") +
							String.format("%-10s", "Won") +
							String.format("%-10s", "Drawn") +
							String.format("%-12s", "Lost") +
							String.format("%-10s", "GS") +
							String.format("%-9s", "GA") +
							"GD"
			);

			//read from the hashmap to display club types separately -> Normal, School , University
			HashMap<String, ArrayList<FootballClub>> clubTypes = leagueManager.showLeagueTable();

			for (String i : clubTypes.keySet()) {
				int count = 1;
				System.out.println(i);
				for (FootballClub team : clubTypes.get(i)) {
					System.out.println(
									String.format("%02d", count) +
									String.format("%-4s", ".") +
									String.format("%-25s", team.getClubName()) +
									String.format("%10s", team.getPoints()) +
									String.format("%10s", team.getMatchCount()) +
									String.format("%10s", team.getWinCount()) +
									String.format("%10s", team.getDrawCount()) +
									String.format("%10s", team.getDefeatCount()) +
									String.format("%10s", team.getGoalScoredCount()) +
									String.format("%10s", team.getGoalReceivedCount()) +
									String.format("%10s", team.getGoalDifference())
					);
					count += 1;
				}
				System.out.println();
			}

			System.out.println("GS - Goals Scored		GA - Goals Against		GD - Goal Difference\n");
		}
	}

	//Add a match with 2 teams and date
	private static void promptForAddMatch(){
		Date date;

		System.out.println("Enter name of the club : ");
		String fc1Name = sc.nextLine();

		System.out.println("VS : (Club name)");
		String fc2Name = sc.nextLine();

		//check both clubs are exists and clubs aren't same
		if ((clubExists(fc1Name) != -1 || clubExists(fc2Name) != -1) && clubExists(fc1Name) != clubExists(fc2Name) ) {
			FootballClub fc1 = leagueManager.getTeams().get(clubExists(fc1Name));
			FootballClub fc2 = leagueManager.getTeams().get(clubExists(fc2Name));
			boolean flag = true;

			//identify whether two clubs are same type
			// 		|- > blocking user to add a match which is compete with different types of football clubs
			if (fc1 instanceof UniversityFootballClub && fc2 instanceof UniversityFootballClub)
				flag = false;
			else if (fc1 instanceof SchoolFootballClub && fc2 instanceof SchoolFootballClub)
				flag = false;
			else if (fc1 instanceof DefaultFootballClub && fc2 instanceof DefaultFootballClub)
				flag = false;

			if (!flag) {
				System.out.println("Enter goals scored by " + fc1Name + " : ");
				int fc1GoalCount = trueInt();
				sc.nextLine();

				System.out.println("Enter goals scored by " + fc2Name + ": ");
				int fc2GoalCount = trueInt();
				sc.nextLine();

				System.out.println("Enter the date played (DD/MM/YYYY) : ");
				String dateInput = sc.nextLine();

				while (true){
					try{
						//spliting date into array by "/" and validate
						String[] dateArray = dateInput.split("/");
						date = new Date(parseInt(dateArray[0]), parseInt(dateArray[1]),parseInt(dateArray[2]));
						break;
					} catch (IllegalArgumentException e){
						System.out.println("Enter VALID date (DD<32/MM<13/1000<YYYY) : ");
						dateInput = sc.nextLine();
					} catch (Exception e) {
						System.out.println("Enter date in given FORMAT (DD/MM/YYYY) : ");
						dateInput = sc.nextLine();
					}
				}
				leagueManager.addMatch(fc1, fc1GoalCount, fc2, fc2GoalCount, date);
				System.out.println("\nLeague table updated.\n");
			} else {
				System.out.println("Please check the club types. Different club types can't compete.\n" +
						"Ex : under 18 club vs under 23 clubs");
			}
		} else {
			System.out.println("Clubs doesn't exists or club names are same. Please check the club names.");
		}
	}

	//method to find whether a given club exists and return the index of the club exists in the array list
	private static int clubExists(String clubName){
		int index = 0;
		ArrayList<FootballClub> teams = (ArrayList<FootballClub>) leagueManager.getTeams();
		if (!teams.isEmpty()) {
			for (FootballClub team : teams) {
				if (team.getClubName().toLowerCase().trim().equals(clubName.toLowerCase().trim()))
					return index;
				index++;
			}
		}
		return -1;
	}

	//input while its an integer
	private static int trueInt(){
		while(!sc.hasNextInt()){
                	System.out.println("Invalid Data type");
                	System.out.println("Enter again : ");
                	sc.next();
            	}
            	return sc.nextInt();
	}

	public static PremierLeagueManager getPremierLeagueManager(){
		return leagueManager;
	}
}
