package homework4;

/**
 * a Class that represents a line of chat connected to a User
 */
class ChatLine {
    private User user;
    private String line;

    /**
     * creates a Chatline
     * @param user - the User who wrote the line
     * @param line - the text of the line
     */
    public ChatLine(User user, String line){
        this.user = user;
        this.line = line;
    }

    /**
     * creates a Chatline
     * @param line - a ChatLine to copy
     */
    public ChatLine(ChatLine line){
        this.user = line.user;
        this.line = line.line;
    }

    /**
     * @return a String in the requested form of the chet
     */
    @Override
    public String toString(){
        return user.getName() + ":" + " " + line + "\n";
    }

    /**
     * @return ID of the User who wrote the line
     */
    public int identify(){
        return user.getID();
    }
}
