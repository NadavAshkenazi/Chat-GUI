package chatPackage;

public class ChatLine {
    private User user;
    private String line;

    public ChatLine(User user, String line){
        this.user = user;
        this.line = line;
    }
    public ChatLine(ChatLine line){
        this.user = line.user;
        this.line = line.line;
    }
    @Override
    public String toString(){
        return user.getName() + ":" + " " + line + "\n";
    }
    public int identify(){
        return user.getID();
    }
}
