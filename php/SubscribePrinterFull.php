<?php

require 'Predis.php';

function f($redis, $channel, $msg) {
    if ($channel == "course_message")
            print "$msg \n";
}

$client = new Predis\Client();

$client->subscribe(array('course_message','test'), 'f');
print "\n";
		

?>