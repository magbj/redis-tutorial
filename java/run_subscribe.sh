echo "Compiling ..."
javac SubscribePrinter.java -cp dependencies/jedis-2.7.3-SNAPSHOT.jar
echo "Executing ..."
java -cp .:dependencies/jedis-2.7.3-SNAPSHOT.jar SubscribePrinter 
