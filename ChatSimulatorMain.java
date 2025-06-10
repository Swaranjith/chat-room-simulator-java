import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatSimulatorMain {
    public static void main(String[] args) {
        List<String> users = Arrays.asList("Alice", "Bob", "Charlie");
        ChatRoom chatRoom = new ChatRoom(users);

        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (String user : users) {
            executor.execute(new UserSession(user, chatRoom));
        }

        executor.execute(new BotUser("ChatBot", chatRoom));

        executor.shutdown();
    }
}
