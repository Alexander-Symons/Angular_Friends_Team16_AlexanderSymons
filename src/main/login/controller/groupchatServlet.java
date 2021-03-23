package controller;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@ServerEndpoint("/groupschat")
public class groupchatServlet {
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
//    private static final Map<String ,Session> sessions = Collections.synchronizedMap(new HashMap<String, Session>());


    @OnOpen
    public void onOpen(Session session){
        System.out.println(session.getQueryString());
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(String message){
        sendMessageToAll(message);
    }

    @OnClose
    public void onClose(Session session){
        System.out.println("Chat " +session.getId()+" has ended");
        sessions.remove(session);
    }

    private void sendMessageToAll(String message){
        for(Session s : sessions){
            try {
                s.getBasicRemote().sendText(message);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
