package application;

import java.util.Scanner;

public class User {

	private String username;
	private int wins;
	private int losses;
	//TODO: Attach an IP address to the user
	
	/**
	 * Full user constructor.
	 * @param uName
	 * @param wins
	 * @param losses
	 */
	public User(String uName, int wins, int losses) {
		this.username = uName;
		this.wins = wins;
		this.losses = losses;
	}
	
	/**
	 * Used for when the wins and losses of a user aren't defined.
	 * @param uName
	 */
	public User(String uName) {
		username = uName;
		wins = 0;
		losses = 0;
	}

	/**
	 * Checks entire database to see if username exists
	 * @param data - database
	 * @param username - username to compare against database
	 */
	public static boolean userExists(StorageManager data, String username){
		Scanner scanner = data.getScanner();

		while(scanner.hasNext()){
			String curr = scanner.next();
			String[] userInfo = curr.split(",");
			if(userInfo[0].equals(username)){
				scanner.close();
				return true;
			}
		}
		scanner.close();
		return false;
	}

	/**
	 * Returns this User's username.
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Sets this user's username.
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 *  Gets wins.
	 * @return wins
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * Modifies wins and ensures that value entered is >= 0.
	 * @param wins
	 */
	public void setWins(int wins) {
		this.wins = wins >= 0 ? wins : 0;
	}

	/**
	 * Gets losses.
	 * @return losses
	 */
	public int getLosses() {
		return losses;
	}

	/**
	 * modifies losses with values >= 0. If less than 0 then losses is set to 0.
	 * @param losses
	 */
	public void setLosses(int losses) {
		losses = losses >= 0 ? losses : 0;
	}
}