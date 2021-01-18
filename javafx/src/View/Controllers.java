package View;

import ClubTypes.DefaultFootballClub;
import ClubTypes.FootballClub;
import ClubTypes.SchoolFootballClub;
import ClubTypes.UniversityFootballClub;
import Interface.Accessories.Date;
import Interface.Accessories.FootballMatch;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Controllers {
    private static String searchDate = "";
    private static GridPane matchScoreBoardGP = new GridPane();

    private static List<FootballMatch> matchHistory = CLI.getPremierLeagueManager().getMatchHistory();
    private static List<FootballClub> footballClubs = CLI.getPremierLeagueManager().getTeams();

    public static Button button(String name, double width, double height, double x, double y) {
        Button btn = new Button(name);
        btn.setPrefSize(width, height);
        btn.setLayoutX(x);
        btn.setLayoutY(y);
        return btn;
    }

    public static Label lbl(String name, double width, Pos position) {
        Label lbl = new Label(name);
        lbl.setPrefWidth(width);
        lbl.setAlignment(position);
        return lbl;
    }

    public static ScrollPane scrollPane (GridPane content){
        ScrollPane sp = new ScrollPane();
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setContent(content);
        return sp;
    }

    public static TextField txtField(double width, double height, double x, double y) {
        TextField txtField = new TextField();
        txtField.setPrefSize(width,height);
        txtField.setLayoutX(x);
        txtField.setLayoutY(y);
        return txtField;
    }
//League manager - Normal FC.jpg
    public static Tab scoreTableTab(String tabName, ArrayList<FootballClub> arrayList, String imageName) throws FileNotFoundException {
//        Button searchBtn = button("Search", 63, 31,680,140);
//        searchBtn.setId("SearchBtnMatchTab");
//        TextField searchBar = textField(250,31,768,140);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(1075, 750);
        anchorPane.getChildren().addAll();

        TableView tableView = new TableView();
        tableView.setPrefHeight(430);
        tableView.setLayoutX(40);
        tableView.setLayoutY(200);

        TableColumn<FootballClub, String> clubName = new TableColumn<>("Club Name");
        clubName.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        clubName.setPrefWidth(450);
        tableView.getColumns().add(clubName);

        String[][] parameters = {{"Points", "points"}, {"Won", "winCount"}, {"Drawn", "drawCount"},
                {"Lost", "defeatCount"}, {"GS", "goalScoredCount"}, {"GA", "goalReceivedCount"}, {"GD", "goalDifference"}};

        for (String[] parameter : parameters) {
            TableColumn<FootballClub, Integer> column = new TableColumn<>(parameter[0]);
            column.setCellValueFactory(new PropertyValueFactory<>(parameter[1]));
            column.setPrefWidth(75);
            tableView.getColumns().add(column);
        }

        tableView.getItems().addAll(arrayList);

        ImageView bgImage = new ImageView(new Image(new FileInputStream("Images/" + imageName)));
        anchorPane.getChildren().addAll(bgImage, tableView);


        Tab tab = new Tab(tabName, anchorPane);


        return tab;
    }

    public static GridPane matchScoreCard(FootballMatch match){
        GridPane gp = new GridPane();
        gp.setPrefSize(842, 90);

        FootballClub fc1 = match.getFootballClub1();
        FootballClub fc2 = match.getFootballClub2();

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPrefWidth(305);

        ColumnConstraints col2 = new ColumnConstraints();
        col1.setPrefWidth(146);

        ColumnConstraints col3 = new ColumnConstraints();
        col1.setPrefWidth(305);

        gp.getColumnConstraints().addAll(col1, col2, col3);

        Label lblClubName1 = new Label(fc1.getClubName());
        Label lblClubName2 = lbl(fc2.getClubName(), 305, Pos.CENTER_RIGHT);
        lblClubName1.setId("lblClubName");
        lblClubName2.setId("lblClubName");

        Label lblVS = lbl("VS", 146, Pos.CENTER);
        lblVS.setId("lblVS");

        gp.add(lblClubName1, 0, 0);
        gp.add(lblVS, 1, 0);
        gp.add(lblClubName2, 2, 0);

        Label lblPointsFC1 = new Label(String.valueOf(fc1.getPoints()));
        Label lblPointsFC2 = lbl(String.valueOf(fc2.getPoints()), 305, Pos.CENTER_RIGHT);
        lblPointsFC1.setId("lblPointsFC");
        lblPointsFC2.setId("lblPointsFC");

        Label lblPointsTag1 = lbl("Points", 65, Pos.CENTER_RIGHT);
        Label lblPointsTag2 = lbl("Points ", 275, Pos.CENTER_RIGHT);
        lblPointsTag1.setId("lblPointsTag");
        lblPointsTag2.setId("lblPointsTag");

        gp.add(lblPointsFC1, 0, 1);
        gp.add(lblPointsTag1, 0, 1);
        gp.add(lbl("Goals : " + match.getFc1GoalCount(), 192, Pos.CENTER_RIGHT), 0, 1);

        gp.add(lbl(match.getDate().toString(), 146, Pos.CENTER), 1, 1);

        gp.add(lbl(match.getFc2GoalCount() + " : Goals", 150, Pos.CENTER_RIGHT), 2, 1);
        gp.add(lblPointsTag2, 2, 1);
        gp.add(lblPointsFC2, 2, 1);

        return gp;
    }

    public static GridPane matchHistoryGP() throws FileNotFoundException {
        //Grid pane for content each match card - add grid row for display each played match

        ColumnConstraints matchCardsCol1 = new ColumnConstraints();
        matchCardsCol1.setPrefWidth(155);
        ColumnConstraints matchCardsCol2 = new ColumnConstraints();
        matchCardsCol1.setPrefWidth(800);
        ColumnConstraints matchCardsCol3 = new ColumnConstraints();
        matchCardsCol1.setPrefWidth(155);
        matchScoreBoardGP.getColumnConstraints().addAll(matchCardsCol1, matchCardsCol2, matchCardsCol3);

        //loop through match list and create a match card for each match
        int rowNo = 0;
        for (FootballMatch match : CLI.getPremierLeagueManager().getMatchHistory()) {
            RowConstraints row = new RowConstraints();
            row.setPrefHeight(85);
            matchScoreBoardGP.add(matchScoreCard(match), 1, rowNo);
            RowConstraints row1 = new RowConstraints();
            row1.setPrefHeight(85);
            rowNo += 2;
        }

        //League match history tab
        GridPane tabContentGP = new GridPane();
        tabContentGP.getColumnConstraints().addAll(
                new ColumnConstraints(290,290,290),
                new ColumnConstraints(500,500,500),
                new ColumnConstraints(290,290,290)
        );

        tabContentGP.getRowConstraints().addAll(
                new RowConstraints(120,120,120),
                new RowConstraints(48,48,48),
                new RowConstraints(475,475,475)
        );

        ImageView bgImage = new ImageView(new Image(new FileInputStream("Images/League Manager - Match History.jpg")));
        tabContentGP.add(bgImage, 0, 0, 3, 4);
        Button btnAddMatch = button("Add match randomly", 298, 32, 0, 0);
        tabContentGP.add(btnAddMatch, 0,1);
        TextField searchBar = txtField(200, 33, 0, 0);
        searchBar.setPromptText("Search by date...");
        searchBar.setId("searchBar");
        tabContentGP.add(searchBar, 1,1);
        Button sortButton = button("Sort in ascending order of date played", 298, 32, 0, 0);
        tabContentGP.add(sortButton, 2,1);

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            searchDate = newValue;
            searchByDate();
        });

        //add match randomly - button action
        btnAddMatch.setOnAction(event -> {
            if (CLI.getPremierLeagueManager().getTeams().size()>1) {
                Random random = new Random();
                FootballClub fc1 = CLI.getPremierLeagueManager().getTeams().get(((random.nextInt(CLI.getPremierLeagueManager().getTeams().size()))));
                FootballClub fc2 = CLI.getPremierLeagueManager().getTeams().get(((random.nextInt(CLI.getPremierLeagueManager().getTeams().size()))));

                //Check clubs are same type.
                boolean flag = true;
                if (fc1 instanceof UniversityFootballClub && fc2 instanceof UniversityFootballClub)
                    flag = false;
                else if (fc1 instanceof SchoolFootballClub && fc2 instanceof SchoolFootballClub)
                    flag = false;
                else if (fc1 instanceof DefaultFootballClub && fc2 instanceof DefaultFootballClub)
                    flag = false;

                //doesn't let the same club add in to a match and two different types won't add.
                while (fc1 == fc2 || flag) {
                    fc1 = CLI.getPremierLeagueManager().getTeams().get(((random.nextInt(CLI.getPremierLeagueManager().getTeams().size()))));
                    fc2 = CLI.getPremierLeagueManager().getTeams().get(((random.nextInt(CLI.getPremierLeagueManager().getTeams().size()))));
                    if (fc1 instanceof UniversityFootballClub && fc2 instanceof UniversityFootballClub)
                        flag = false;
                    else if (fc1 instanceof SchoolFootballClub && fc2 instanceof SchoolFootballClub)
                        flag = false;
                }

                Date date = new Date(random.nextInt(30) + 1, random.nextInt(11) + 1, 2020);
                CLI.getPremierLeagueManager().addMatch(
                        fc1,
                        random.nextInt(5),
                        fc2,
                        random.nextInt(5),
                        date
                );
                try {
                    CLI.getPremierLeagueManager().save();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                searchByDate();
            }
        });

        //sort in ascending order - button sort ascending order
        sortButton.setOnAction(event -> {
            if (!CLI.getPremierLeagueManager().getMatchHistory().isEmpty()) {
                matchScoreBoardGP.getChildren().clear();
                List<FootballMatch> fcMatches = CLI.getPremierLeagueManager().getMatchHistory();
                Collections.sort(fcMatches);
                int rowNo2 = 0;
                for (FootballMatch match : fcMatches) {
                    RowConstraints row = new RowConstraints();
                    row.setPrefHeight(85);
                    matchScoreBoardGP.add(matchScoreCard(match), 1, rowNo2);
                    RowConstraints row1 = new RowConstraints();
                    row1.setPrefHeight(85);
                    rowNo2 += 2;
                }
                searchByDate();
            }
        });

        //Scroll pane
        ScrollPane scrollPane = scrollPane(matchScoreBoardGP);

        tabContentGP.add(scrollPane,0,2,3,1);

        return tabContentGP;
    }

    public static void searchByDate() {
        matchScoreBoardGP.getChildren().clear();
        int rowNo = 0;
        for (FootballMatch match : CLI.getPremierLeagueManager().getMatchHistory()) {
            if (match.getDate().toString().contains(searchDate)){
                RowConstraints row = new RowConstraints();
                row.setPrefHeight(85);
                matchScoreBoardGP.add(matchScoreCard(match), 1, rowNo);
                RowConstraints row1 = new RowConstraints();
                row1.setPrefHeight(85);
                rowNo += 2;
            }
        }
    }
}
