/**
 * @author Thomas Kay
 * @version 2/1/2023
 * 
 * The purpose of this file is to show Dr. Scott that team A/Red can successfully
 * push and pull files from gitlab in order to show we are capable in using git.
 */
public class Name {
	
	/** Stores the current name of the project */
	private String name;
	/** Stores the team working on the current project */
	private String team;
	
	/** Constructor for the name object */
	public Name {
		
		this.name = "CS364 Project";
		this.team = "A Team";
		
	}
	
	/**
	 * Returns the name of the project.
	 * 
	 * @return the current name.
	 */
	public String getName() {
		
		return this.name;
	}
	
	/**
	 * Returns the team of the project.
	 * 
	 * @return the current team.
	 */
	public String getTeam() {
		
		return this.team;
	}
	
	/**
	 * Displays the name and team of the project.
	 * 
	 * @return the string representation of the name object.
	 */
	public String toString() {
		
		String toReturn = "The name of this file is " + this.getName() +
						  " from the " + this.getTeam();
		return toReturn;
		
	}
	
	/**
	 * Main method to test if the file runs properly.
	 */
	public static void main(String[] args) {
		
		Name proj = new Name();
		System.out.println(proj);
		
	}
	
	
	
}