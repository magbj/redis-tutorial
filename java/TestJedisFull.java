
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import java.util.List;
import java.util.Set;
import java.util.Iterator;

public class TestJedisFull {
	
	
	public static void main(String[] args) {

		//Connecting to Redis on localhost
		Jedis jedis = new Jedis("localhost");
		
		// Step 1 - Test Connection
		System.out.println("Testing connection: "+jedis.ping());
		
		// Step 2 - Storing a String
		//set the data in redis string
	    jedis.set("school", "SEAS");
	    // Get the stored data and print it
	    System.out.println("Stored string in redis:: "+ jedis.get("school"));
	    
	    // Step 3 - Complex data type: List
	    //store data in redis list
	    jedis.del("courses"); // delete for previous runs
	    jedis.lpush("courses", "Introduction to Java");
	    jedis.lpush("courses", "Introduction to PHP");
	    jedis.lpush("courses", "Introduction to Python");
	    // Get the stored data and print it
	    List<String> list = jedis.lrange("courses", 0 , -1);
	    for(int i=0; i<list.size(); i++) {
	      System.out.println("Member of Courses list: "+list.get(i));
	    }
	    
	    // Step 4 - List existing keys
	    Set<String> keys = jedis.keys("*");
	    Iterator<String> iter = keys.iterator();
	    while (iter.hasNext()) {
	      System.out.println("Key (list of all): "+iter.next());
	    }
	    
	    // Step 5 - Using pipelines
	    Pipeline p = jedis.pipelined();
	    p.lpush("courses", "Optimizing Java");
	    Response<List<String>> pipeList = p.lrange("courses", 0 , -1);
	    p.sync(); 

	    list = pipeList.get();
	    for(int i=0; i<list.size(); i++) {
	      System.out.println("Member of Courses list (pipeline): "+list.get(i));
	    }
	    
	    // Step 6 - Using transactions
	    jedis.watch ("test_value"); // watch for any changes to this key
	    String testValue=jedis.get("test_value"); // get key value
	    if ("foo".equals(testValue)) // figure out the new value 
	    	testValue="bar"; 
	    else 
	    	testValue="foo";
	    Transaction t = jedis.multi(); // No commands will actually be submitted as part of this block
	    t.set("test_value", testValue); // command to queue up
	    t.exec(); // execute all the commands. it will fail and discard changes if any of the watched keys have been changed by other processes
	     
	    // Step 7 - Pub/Sub
	    jedis.publish("course_message","testing");
	    
	}

}
