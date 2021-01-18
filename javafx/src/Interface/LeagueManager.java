package Interface;

import ClubTypes.FootballClub;
import Interface.Accessories.Date;

import java.io.IOException;
import java.util.HashMap;

public interface LeagueManager{
	public void createFootballClub(FootballClub fc);

	public void deleteFootballClub(FootballClub fc);

	public void showClubStat(FootballClub fc);

	public HashMap showLeagueTable();

	public void addMatch(FootballClub fc1, int fc1GoalCount, FootballClub fc2, int fc2GoalCount, Date date);

	public void save() throws IOException;

	public void read() throws ClassNotFoundException, IOException;

	public void launchGUI(String[] args);
}
