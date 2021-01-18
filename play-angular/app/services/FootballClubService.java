package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import controllers.terminalController.JsonToFootballClub;
import entities.*;
import entities.Date;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class FootballClubService {
    private static FootballClubService service;
    private static List<FootballClub> teams = new ArrayList<>();
    private static List<FootballClub> defaultClubsTeams = new ArrayList<>();
    private static List<FootballClub> uniClubTeams = new ArrayList<>();
    private static List<FootballClub> schoolClubTeams = new ArrayList<>();
    private static List<FootballMatch> matchHistory = new ArrayList<>();

    public static FootballClubService getService() {
        if (service == null) {
            service = new FootballClubService();
        }
        return service;
    }

    public List<FootballClub> getAllDefaultFootballClubs() {
        try {
            readStorage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defaultClubsTeams;
    }

    public List<FootballClub> getAllUniFootballClubs() {
        try {
            readStorage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uniClubTeams;
    }

    public List<FootballClub> getAllSchoolFootballClubs() {
        try {
            readStorage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return schoolClubTeams;
    }

    public List<FootballMatch> getAllFootballMatches() {
        try {
            readStorage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchHistory;
    }

    public FootballMatch createRandomMatch(){
        Random random = new Random();
        FootballClub fc1 = null;
        FootballClub fc2 = null;

        while (fc1 == fc2) {
            FootballClub[] randomClubs = generateRandomClubs();
            fc1 = randomClubs[0];
            fc2 = randomClubs[1];
        }

        Date date = new Date(random.nextInt(30) + 1, random.nextInt(11) + 1, 2021);
        FootballMatch randomMatch = addMatch(
                fc1,
                random.nextInt(5),
                fc2,
                random.nextInt(5),
                date
        );
        save();
        return randomMatch;
    }

    private static FootballClub[] generateRandomClubs(){
        Random random = new Random();
        int clubSelection = random.nextInt(3);

        FootballClub fc1 = null;
        FootballClub fc2 = null;

        if (clubSelection == 0){
            fc1 = defaultClubsTeams.get(random.nextInt(defaultClubsTeams.size()));
            fc2 = defaultClubsTeams.get(random.nextInt(defaultClubsTeams.size()));
        } else if (clubSelection == 1){
            fc1 = uniClubTeams.get(random.nextInt(uniClubTeams.size()));
            fc2 = uniClubTeams.get(random.nextInt(uniClubTeams.size()));
        } else if (clubSelection == 2){
            fc1 = schoolClubTeams.get(random.nextInt(schoolClubTeams.size()));
            fc2 = schoolClubTeams.get(random.nextInt(schoolClubTeams.size()));
        }
        return new FootballClub[]{fc1, fc2};
    }

    private FootballMatch addMatch(FootballClub fc1, int fc1GoalCount, FootballClub fc2, int fc2GoalCount, Date date){
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

        FootballMatch randomMatch = new FootballMatch(fc1, fc1GoalCount, fc2, fc2GoalCount, date);
        matchHistory.add(randomMatch);
        return randomMatch;
    }

    public static void readStorage() throws IOException {
        Type FOOTBALL_CLUB_TOKEN = new TypeToken<ArrayList<FootballClub>>() {
        }.getType();
        Type MATCH_HISTORY_TOKEN = new TypeToken<ArrayList<FootballMatch>>() {
        }.getType();

        Gson gson = new GsonBuilder().registerTypeAdapter(FootballClub.class, new JsonToFootballClub()).create();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("storage.json")));
        teams.clear();
        defaultClubsTeams.clear();
        uniClubTeams.clear();
        schoolClubTeams.clear();
        teams = gson.fromJson(bufferedReader.readLine(), FOOTBALL_CLUB_TOKEN);
        for (FootballClub team : teams) {
            if (team instanceof UniversityFootballClub) {
                uniClubTeams.add(team);
            } else if (team instanceof SchoolFootballClub) {
                schoolClubTeams.add(team);
            } else {
                defaultClubsTeams.add(team);
            }
        }
        matchHistory.clear();
        matchHistory = gson.fromJson(bufferedReader.readLine(), MATCH_HISTORY_TOKEN);
    }

    private void save() {
        try (Writer writer = new FileWriter("storage.json", false)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(teams, writer);
            writer.write("\n");
            gson.toJson(matchHistory, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
