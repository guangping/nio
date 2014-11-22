package socket.web;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: lance
 * Date: 2014-11-22 14:53
 * To change this template use File | Settings | File Templates.
 */
public class SocketServerUtils {
    private List<Session> list = new Vector<Session>();
    private Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    {
        Timer timer = new Timer(true);
        System.out.println("通知:"+timer);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    sendMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EncodeException e) {
                    e.printStackTrace();
                }
            }
        }, 1000 * 10);
    }

    private static class SocketServerUtilsClassLoader {
        static SocketServerUtils instance = new SocketServerUtils();
    }

    //通知所有的连接者
    private void sendMessage() throws IOException, EncodeException {
        System.out.println("当前时间:"+ format.format(new Date()));
        List<Session> list = SocketServerUtils.instance().getSessionList();
        for (final Session session : list) {
            session.getBasicRemote().sendText("当前时间:" + format.format(new Date()));
        }
    }

    private SocketServerUtils() {

    }

    public static SocketServerUtils instance() {
        return SocketServerUtilsClassLoader.instance;
    }

    public List<Session> getSessionList() {
        return list;
    }

    public void add(Session session) {
        list.add(session);
    }

    public void remove(Session session) {
        list.remove(session);
    }
}
