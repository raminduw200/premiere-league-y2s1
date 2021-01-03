package entities;

import java.io.Serializable;

public abstract class SportsClub implements Serializable {
	private static final long serialVersionUID = -1L;
	protected String clubName;
	protected String location;
	protected int winCount;
	protected int drawCount;
	protected int defeatCount;
	protected int matchCount;

	//Constructor defined
	public SportsClub(String clubName, String location, int winCount, int drawCount, int defeatCount){
		this.clubName = clubName;
		this.location = location;
		setWinCount(winCount);
		setDrawCount(drawCount);
		setDefeatCount(defeatCount);
		setMatchCount();
	}

	//Setters
	public void setClubName(String clubName){
		this.clubName = clubName;
	}

	public void setLocation(String location){
		this.location = location;
	}

	public void setWinCount(int winCount){
		if (winCount >= 0){
			this.winCount = winCount;
		} else {
			throw new IllegalArgumentException("invalid winCount");
		}
	}

	public void setDrawCount(int drawCount){
		if (drawCount >= 0){
			this.drawCount = drawCount;
		} else {
			throw new IllegalArgumentException("invalid drawCount");
		}
	}

	public void setDefeatCount(int defeatCount){
		if (defeatCount >= 0){
			this.defeatCount = defeatCount;
		} else {
			throw new IllegalArgumentException("invalid defeatCount");
		}
	}

	public void setMatchCount(){
		matchCount = this.winCount + this.defeatCount + this.drawCount;
	}

	//Getters
	public String getClubName(){
		return clubName;
	}

	public String getLocation(){
		return location;
	}

	public int getWinCount(){
		return winCount;
	}

	public int getDrawCount(){
		return drawCount;
	}

	public int getDefeatCount(){
		return defeatCount;
	}

	public int getMatchCount(){
		return matchCount;
	}


	//to String method
	@Override
	public String toString(){
		return "Wins	 : " + winCount + "\n" + 
			"Draws	 : " + drawCount + "\n" + 
			"Defeats  : " + defeatCount + "\n" + 
			"Number of matches played : " + matchCount + "\n";
	}

}
