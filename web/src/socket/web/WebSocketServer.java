package socket.web;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: guangping
 * Date: 2014-11-20 16:50
 * To change this template use File | Settings | File Templates.
 */
@ServerEndpoint("/chatserver")
public class WebSocketServer {



    @OnOpen
    public void open(final Session session) {
        System.out.println("open......" + session);
        SocketServerUtils.instance().add(session);
    }

    @OnMessage
    public void onMessage(String message, final Session client)
            throws IOException, EncodeException {
        System.out.println("接收消息：" + client + ";===>message:" + message);
        client.getBasicRemote().sendText("返回值!"+client);
    }



/*    @OnMessage
    public String receiveMessage(String message) {
        System.out.println("receiveMessage......" + message);
        return "lance";
    }*/

    @OnClose
    public void close(final Session session) {
        SocketServerUtils.instance().remove(session);
    }

    /*   @OnClose
       public void close(CloseReason c) {
           System.out.println("close......" + c);
       }
   */
    @OnError
    public void error(Throwable t) {
        System.out.println("error......" + t);
    }


}
