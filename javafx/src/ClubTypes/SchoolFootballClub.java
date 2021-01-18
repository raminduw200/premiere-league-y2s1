package ClubTypes;

public class SchoolFootballClub extends FootballClub{
	private String schoolName;
	private String coachName;

	public SchoolFootballClub(String schoolName, String clubName, String coachName, String location, int winCount, int drawCount, int defeatCount, int goalScoredCount, int goalReceivedCount){
		super(clubName, location, winCount, drawCount, defeatCount, goalScoredCount, goalReceivedCount);
		this.schoolName = schoolName;
		this.coachName = coachName;
	}

	//Setters
	public void setSchoolName(String schoolName){
		this.schoolName = schoolName;
	}
	
	public void setCoachName(String coachName){
		this.coachName = coachName;
	}

	//Getters
	public String getSchoolName(){
		return schoolName;
	}

	public String getCoachName(){
		return coachName;
	}

	//toString method
	@Override
	public String toString(){
		return "School Name       : " + schoolName + "\n" +
			"School Location  : " + super.location + "\n" +
			"School Club Name : " + super.clubName + "\n" +
			"Coach Name       : " + coachName + "\n" +
			super.toString();
	}

}