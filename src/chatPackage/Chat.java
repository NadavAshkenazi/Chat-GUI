package chatPackage;

import javax.sound.sampled.Line;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Chat {
    private List<User> users;
    private List<ChatLine> lines;
    private List<UserDialog> observers;
    public Chat(){
        users =  new ArrayList<>();
        lines =  new ArrayList<>();
        observers = new ArrayList<>();
    }
    public void addUser(User user){
        users.add(new User(user.getID(), user.getName()));
    }
    public void addLine(ChatLine line){
        lines.add(new ChatLine(line));
        for (UserDialog dialog : observers){
            dialog.update();
        }
    }
    public void addObserver(UserDialog dialog){
        observers.add(dialog);
    }
    public Iterator<ChatLine> getChatLines() {
        ArrayList<ChatLine>newLines = new ArrayList<ChatLine>(this.lines);
        return newLines.iterator();
    }
}

