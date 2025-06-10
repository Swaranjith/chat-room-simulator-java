import java.util.*;

public class ChatRoom {
    private final List<ChatMessage> messages = new ArrayList<>();
    private final Set<String> activeUsers = new HashSet<>();
    private final Set<String> usersMessagedSinceBot = new HashSet<>();

    public ChatRoom(Collection<String> usernames) {
        activeUsers.addAll(usernames);
    }

    public synchronized void postMessage(ChatMessage msg) {
        messages.add(msg);
        System.out.println(msg);

        if (!msg.getSender().equals("ChatBot")) {
            usersMessagedSinceBot.add(msg.getSender());
            // Notify waiting bot thread in case it’s waiting
            notifyAll();
        }
    }

    public synchronized List<ChatMessage> getMessages() {
        return new ArrayList<>(messages);
    }

    public synchronized void waitForAllUsersToMessage() throws InterruptedException {
        while (!usersMessagedSinceBot.containsAll(activeUsers)) {
            wait();  // wait until users send messages
        }
    }

    public synchronized void resetUsersMessaged() {
        usersMessagedSinceBot.clear();
    }

    public synchronized List<String> getActiveUsers() {
    return new ArrayList<>(activeUsers);
}

}
