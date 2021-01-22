package chatPackage;

/**
 * a Class that represents a chat user
 */
class User {
    // Abs. Function
    // 	immutable class that represents a Chat user.
    //  holds ID for identification
    //  holds name for presenting

    // Rep. Invariant:
    //  ID >= 0; - needs checking only under constructor because class is immutable;

    private final int ID;
    private final String name;

    void checkRep(){
        assert (ID >= 0);
    }

    /**
     * creates a new User
     * @param ID - user idewntification number within the chat Sytem
     * @param name - username to be printed
     * @ requires - ID >= 0
     */
    public User(int ID, String name) {
        this.ID = ID;
        this.name = name;
        checkRep();
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
