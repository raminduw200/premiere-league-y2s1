package Interface;

import ClubTypes.*;
import Interface.Accessories.Date;
import Interface.Accessories.FootballMatch;
import View.GUI;
import javafx.application.Application;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PremierLeagueManager implements LeagueManager {

	private static List<FootballClub> teams = new ArrayList<>();
	private static List<FootballMatch> matchHistory = new ArrayList<>();

	@Override
	public void createFootballClub(FootballClub fc) {
		teams.add(fc);
	}

	@Override
	public void deleteFootballClub(FootballClub fc) {
		teams.remove(fc);
	}

	@Override
	public void showClubStat(FootballClub fc) {
		System.out.println(fc.toString());
	}

	@Override
	public HashMap showLeagueTable() {
		Collections.sort(teams);

		//breaking teams according to type of the club and returning hashmap of club types and clubs.
		HashMap<String, ArrayList<FootballClub>> clubTypes = new HashMap<>();
		clubTypes.put("Club Teams", new ArrayList<FootballClub>());
		clubTypes.put("Under 23 Teams", new ArrayList<FootballClub>());
		clubTypes.put("Under 18 Teams", new ArrayList<FootballClub>());
		for (FootballClub team : teams) {
			if (team instanceof UniversityFootballClub) {
				clubTypes.get("Under 23 Teams").add(team);
			} else if (team instanceof SchoolFootballClub) {
				clubTypes.get("Under 18 Teams").add(team);
			} else {
				clubTypes.get("Club Teams").add(team);
			}
		}
		return clubTypes;
	}

	@Override
	public void addMatch(FootballClub fc1, int fc1GoalCount, FootballClub fc2, int fc2GoalCount, Date date){
		/*
		Three points for win
		One point for draw
		Zero points for defeat
		More info :
		https://www.premierleague.com/premier-league-explained#:~:text=Find%20out%20about%20the%20format%20and%20history%20of%20the%20Premier%20League&text=Three%20points%20are%20awarded%20for,winning%20the%20Premier%20League%20title.
		*/
		if (fc1GoalCount>fc2GoalCount){
			//update first FootballClub scores
			fc1.setWinCount(fc1.getWinCount()+1);
			fc1.setGoalScoredCount(fc1.getGoalScoredCount() + fc1GoalCount);
			fc1.setGoalReceivedCount(fc1.getGoalReceivedCount() + fc2GoalCount);
			fc1.setPoints();

			//update second FootballClub scores
			fc2.setDefeatCount(fc2.getDefeatCount()+1);
			fc2.setGoalScoredCount(fc2.getGoalScoredCount() + fc2GoalCount);
			fc2.setGoalReceivedCount(fc2.getGoalReceivedCount() + fc1GoalCount);
		} else if (fc1GoalCount < fc2GoalCount){
			//update first FootballClub scores
			fc2.setWinCount(fc2.getWinCount()+1);
			fc2.setGoalScoredCount(fc2.getGoalScoredCount() + fc2GoalCount);
			fc2.setGoalReceivedCount(fc2.getGoalReceivedCount() + fc1GoalCount);
			fc2.setPoints();

			//update second FootballClub scores
			fc1.setDefeatCount(fc1.getDefeatCount()+1);
			fc1.setGoalScoredCount(fc1.getGoalScoredCount() + fc1GoalCount);
			fc1.setGoalReceivedCount(fc1.getGoalReceivedCount() + fc2GoalCount);

		} else {
			fc1.setDrawCount(fc1.getDrawCount()+1);
			fc2.setDrawCount(fc2.getDrawCount()+1);

			fc1.setPoints();
			fc2.setPoints();

			fc1.setGoalScoredCount(fc1.getGoalScoredCount() + fc1GoalCount);
			fc1.setGoalReceivedCount(fc1.getGoalReceivedCount() + fc2GoalCount);

			fc2.setGoalScoredCount(fc2.getGoalScoredCount() + fc2GoalCount);
			fc2.setGoalReceivedCount(fc2.getGoalReceivedCount() + fc1GoalCount);
		}
		fc1.setMatchCount();
		fc2.setMatchCount();
		fc1.setGoalDifference();
		fc2.setGoalDifference();

		matchHistory.add(new FootballMatch(fc1, fc1GoalCount, fc2, fc2GoalCount, date));
	}

	@Override
	public void save() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("storage.txt", false));
		oos.writeObject(teams);
		oos.writeObject(matchHistory);
		oos.flush();
		oos.close();
	}

	@Override
	public void read() throws ClassNotFoundException{
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("storage.txt"));
			teams = (ArrayList<FootballClub>) ois.readObject();
			matchHistory = (ArrayList<FootballMatch>) ois.readObject();
			ois.close();
		} catch (IOException  ignored){}
	}

	@Override
	public void launchGUI(String[] args){
		//creating thread to run the gui using Application.launch
		new Thread(() -> {
			try {
				Application.launch(GUI.class, args);
			} catch (IllegalStateException e){
				//doesn't let the program crash. If it has opened once it wont open again.
				System.out.println("\n\tRestart the application to open GUI again\n\tSelect one : ");
			}
		}).start();
	}

	public List<FootballClub> getTeams() {
		return teams;
	}

	public List<FootballMatch> getMatchHistory() {
		return matchHistory;
	}
}
