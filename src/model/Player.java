package src.model;
/**
 * Class that represents a player object.
 * 
 * @author Nolan Flinchum (100%), Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 3/27/2023
 */

public class Player {
    
    /** User's first name **/
    private String first;

    /** User's last name **/
    private String last;
    
    /** User's email **/
    private String email;
    
    /** User's username **/
    private String username;
    
    /** User's password **/
    private String password;
    
    /** User's number of wins **/
    private int wins;
    
    /** User's number of losses **/
    private int losses;
    
    /** User's number of draws **/
    private int draws;

     /**
     * Constructor for a player object that initializes the username and game stats.
     * This constructor doesn't initialize the player's name and email, because this could be
     * optional information to fill out in an account settings page possibly implemented in the
     * future.
     * 
     * @param userID String that holds the user's username
     */
    public Player(String username){
        this.username = username;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
    }

    /**
     * Constructor for a player object that initializes the username, password, and game stats.
     * This constructor doesn't initialize the player's name and email, because this could be
     * optional information to fill out in an account settings page possibly implemented in the
     * future.
     * 
     * @param userID String that holds the user's username
     * @param password String that holds thse user's password
     */
    public Player(String username, String password){
        this.username = username;
        this.password = password;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
    }

    /**
     * Setter for the user's first name.
     * 
     * @param first String that holds the user's firt name
     */
    public void setFirstName(String first){
        this.first = first;
    }

    /**
     * Setter for the user's last name.
     * 
     * @param last String that holds the user's last name
     */    
    public void setLastName(String last){
        this.last = last;
    }

    /**
     * Setter for the user's email address.
     * 
     * @param email String that holds the user's email address
     */
    public void setEmail(String email){
        this.email = email;   
    }
    

     /**
     * Get the user's username
     * @return username - String that holds the user's username
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Get the user's username
     * @return username - String that holds the user's username
     */
    public String getUsername(){
        return username;
    }

}
