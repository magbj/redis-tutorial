
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import java.util.List;
import java.util.Set;
import java.util.Iterator;

class Subscriber extends JedisPubSub {
    public void onMessage(String channel, String message) {
    	
    }

    public void onSubscribe(String channel, int subscribedChannels) {
    }

    public void onUnsubscribe(String channel, int subscribedChannels) {
    }

    public void onPSubscribe(String pattern, int subscribedChannels) {
    }

    public void onPUnsubscribe(String pattern, int subscribedChannels) {
    }

    public void onPMessage(String pattern, String channel,
        String message) {
    	
    }
}

public class SubscribePrinter {
	
	
	public static void main(String[] args) {

		//Connecting to Redis on localhost
		Jedis jedis = new Jedis("localhost");
		
		Subscriber l = new Subscriber();

		jedis.subscribe(l, "course_message");
	    
	    
	}

}
