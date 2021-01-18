package View;

import ClubTypes.FootballClub;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GUI extends Application{

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage = new Stage();
        primaryStage.setTitle("Premier League - Football");

        //League table tabs
        Tab clubMatchTab = Controllers.scoreTableTab("Club Matches", (ArrayList<FootballClub>) CLI.getPremierLeagueManager().showLeagueTable().get("Club Teams"), "League manager - Normal FC.jpg");
        Tab u23MatchTab = Controllers.scoreTableTab("Under 23 Matches", (ArrayList<FootballClub>) CLI.getPremierLeagueManager().showLeagueTable().get("Under 23 Teams"), "League Manager - Under 23 FC.jpg");
        Tab u18MatchTab = Controllers.scoreTableTab("Under 18 Matches", (ArrayList<FootballClub>) CLI.getPremierLeagueManager().showLeagueTable().get("Under 18 Teams"), "League Manager - Under 18 FC.jpg");
        GridPane tabContentGP = Controllers.matchHistoryGP();

        //match history tab
        Tab matchHistory = new Tab("Match history", tabContentGP);

        TabPane tabPane = new TabPane(clubMatchTab, u23MatchTab, u18MatchTab, matchHistory);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        primaryStage.setMaxWidth(1075);
        primaryStage.setMaxHeight(750);
        Scene stageScene = new Scene(tabPane, 1075, 750);
        stageScene.getStylesheets().add("Interface/style.css");
        primaryStage.setScene(stageScene);
        primaryStage.show();
    }
}


