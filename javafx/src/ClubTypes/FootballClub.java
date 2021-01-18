package ClubTypes;

import java.io.Serializable;
import java.util.Objects;

public abstract class FootballClub extends SportsClub implements Comparable<FootballClub>, Serializable {
	protected int goalScoredCount;
	protected int goalReceivedCount;
	protected int points;
	protected int goalDifference;


	//Constructor defined
	public FootballClub(String clubName, String location, int winCount, int drawCount, int defeatCount, int goalScoredCount, int goalReceivedCount){
		super(clubName, location, winCount, drawCount, defeatCount);
		setGoalScoredCount(goalScoredCount);
		setGoalReceivedCount(goalReceivedCount);
		setPoints();
		setGoalDifference();
	}

	//Setters
	public void setGoalScoredCount(int goalScoredCount){
		if (goalScoredCount >= 0){
			this.goalScoredCount = goalScoredCount;
		} else {
			throw new IllegalArgumentException("invalid goalScoredCount");
		}
	}

	public void setGoalReceivedCount(int goalReceivedCount){
		if (goalReceivedCount >= 0){
			this.goalReceivedCount = goalReceivedCount;
		} else {
			throw new IllegalArgumentException("invalid goalReceivedCount");
		}
	}

	public void setPoints(){
		this.points = winCount*3 + drawCount;
	}

	public void setGoalDifference() {
		this.goalDifference = goalScoredCount-goalReceivedCount;
	}

	//Getters
	public int getGoalScoredCount(){
		return goalScoredCount;
	}

	public int getGoalReceivedCount(){
		return goalReceivedCount;
	}

	public int getPoints(){
		return points;
	}

	public int getGoalDifference() {
		return goalDifference;
	}


	@Override
	public int compareTo(FootballClub fc) {
		int temp = 0;

        if(this.getPoints() == fc.getPoints()){
            if (this.goalDifference>0 && fc.getGoalDifference()>0) {
				temp = fc.getGoalDifference() - this.goalDifference;
			} else if(this.goalDifference<0 && fc.getGoalDifference()>0) {
				temp = 1;
			} else if(this.goalDifference>0 && fc.getGoalDifference()<0) {
				temp = -1;
			} else if(this.goalDifference<0 && fc.getGoalDifference()<0) {
				temp = -this.goalDifference + fc.getGoalDifference();
			} else if(this.goalDifference == fc.getGoalDifference()) {
            	temp = fc.getClubName().compareTo(clubName);
            }
        } else {
            temp = fc.getPoints()-this.points;
        }
        return temp;
	}

	@Override
	public boolean equals(Object footballClub) {
		if (this == footballClub) return true;
		if (footballClub == null || getClass() != footballClub.getClass()) return false;
		FootballClub that = (FootballClub) footballClub;
		return goalScoredCount == that.goalScoredCount &&
				goalReceivedCount == that.goalReceivedCount &&
				points == that.points &&
				goalDifference == that.goalDifference;
	}

	@Override
	public int hashCode() {
		return Objects.hash(goalScoredCount, goalReceivedCount, points, goalDifference);
	}

	//toString method
	@Override
	public String toString(){
		return "Football club : " + super.clubName + "\n" +
			"Location : " + super.location + "\n" +
			super.toString() +
			"Number of goals Scored   : " + goalScoredCount + "\n" + 
			"Number of goals Received : " + goalReceivedCount + "\n" + 
			"Points  : " + points + "\n";
	}

}
