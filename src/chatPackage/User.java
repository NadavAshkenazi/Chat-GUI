package chatPackage;

/**
 * a Class that represents a chat user
 */
class User {
    private int ID;
    private String name;

    /**
     * creates a new User
     * @param ID - user idewntification number within the chat Sytem
     * @param name - username to be printed
     */
    public User(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    /**
     * @return id of the user
     */
    public int getID(){
        return ID;
    }

    /**
     * @return name of the user
     */
    public String getName(){
        return name;
    }
}
