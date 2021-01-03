package entities;

import java.io.Serializable;

public class FootballMatch implements Comparable<FootballMatch>, Serializable {
    private static final long serialVersionUID = 3534808515695297232L;
    private final FootballClub footballClub1;
    private final int fc1GoalCount;
    private final FootballClub footballClub2;
    private final int fc2GoalCount;
    private final Date date;

    public FootballMatch(FootballClub footballClub1, int fc1GoalCount, FootballClub footballClub2, int fc2GoalCount, Date date){
        this.footballClub1 = footballClub1;
        this.fc1GoalCount = fc1GoalCount;
        this.footballClub2 = footballClub2;
        this.fc2GoalCount = fc2GoalCount;
        this.date = date;
    }

    public FootballClub getFootballClub1() {
        return footballClub1;
    }

    public FootballClub getFootballClub2() {
        return footballClub2;
    }

    public int getFc1GoalCount() {
        return fc1GoalCount;
    }

    public int getFc2GoalCount() {
        return fc2GoalCount;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int compareTo(FootballMatch match) {
        return (date.getYear()*20 + date.getMonth()*10 + date.getDay()) - (match.getDate().getYear()*20 + match.getDate().getMonth()*10 + match.getDate().getDay());
    }

    @Override
    public String toString() {
        return "FootballMatch " + "\n" +
                " Football Club : " + footballClub1 + "\n" +
                " Football Club : " + footballClub2 + "\n" +
                " Date : " + date + "\n";
    }
}
