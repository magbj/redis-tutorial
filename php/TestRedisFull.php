<?php

require 'Predis.php';

$client = new Predis\Client();

		// Step 1 - Test Connection
		print "Testing connection: ".$client->ping()."\n";
		
		
		
		// Step 2 - Storing a String
		//set the data in redis string
	    $client->set("school", "SEAS");
	    // Get the stored data and print it
	    print "Stored string in redis:: ".$client->get("school")."\n";
	    
	    
	    
	    // Step 3 - Complex data type: List
	    //store data in redis list
	    $client->del("courses"); // delete for previous runs
	    $client->lpush("courses", "Introduction to Java");
	    $client->lpush("courses", "Introduction to PHP");
	    $client->lpush("courses", "Introduction to Python");
	    // Get the stored data and print it
	    $list = $client->lrange("courses", 0 , -1);
	    foreach ($list as $element) {
	      print "Member of Courses list: ".$element."\n";
	    } 
	    
	    // Step 4 - List existing keys
	    $keys = $client->keys("*");
	    foreach ($keys as $element) {
	      print "Key (list of all): ".$element."\n";
	    }
	    
	   
	    
	    // Step 5 - Using pipelines
	    $responses = $client->pipeline(function ($pipe) {
		    $pipe->lpush("courses", "Optimizing Java");
	    	$pipe->lrange("courses", 0 , -1); 
		});

	    $list = $responses[1];
	    foreach ($list as $element) {
	      print "Member of Courses list (pipeline): ".$element."\n";
	    }
	   
	   
	    
	    // Step 6 - Using transactions
	    // Set the key
  		$key = 'test_value';

		  // predis transaction options
		$options = array(		      
		  'cas'   => true,    // Turn on Check-And-Set
	      'watch' => $key,    // The key that will be watched
	      'retry' => 3,       // Number of retries
		);
		  
	    $responses = $client->transaction($options, function ($tx) use ($key) {
		    // get our value
            $value = $tx->get($key);
            
            if ("foo" == $value) // figure out the new value 
		    	$value; 
		    else 
		    	$value;
		    	
		    $tx->multi();
		    
		    $tx->set($key, $value);
		    	
		});
	    
	     
	    /*
	    // Step 7 - Pub/Sub
	    jedis.publish("course_message","testing");
		*/

?>