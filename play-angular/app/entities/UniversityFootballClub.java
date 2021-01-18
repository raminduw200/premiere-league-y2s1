package entities;

public class UniversityFootballClub extends FootballClub {
	private String uniName;
	private String teamCaptain;

	//Constructor defined
	public UniversityFootballClub(String uniName, String clubName, String location, String teamCaptain, int winCount, int drawCount, int defeatCount, int goalScoredCount, int goalReceivedCount){
		super(clubName, location, winCount, drawCount, defeatCount, goalScoredCount, goalReceivedCount);
		this.uniName = uniName;
		this.teamCaptain = teamCaptain;
	}

	//Setters
	public void setUniName(String uniName){
		this.uniName = uniName;
	}

	public void setTeamPlayerCount(String teamCaptain){
		this.teamCaptain = teamCaptain;
	}

	//Getters
	public String getUniName(){
		return uniName;
	}

	public String getTeamPlayerCount(){
		return teamCaptain;
	}



	//toString method
	@Override
	public String toString(){
		return "University Name         : " + uniName + "\n" +
			"University Location    : " + super.location + "\n" +
			"Number of Team Players : " + teamCaptain + "\n" +
			super.toString();
	}

}
