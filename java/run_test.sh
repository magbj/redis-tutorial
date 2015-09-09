echo "Compiling ..."
javac TestJedis.java -cp dependencies/jedis-2.7.3-SNAPSHOT.jar
echo "Executing ..."
java -cp .:dependencies/jedis-2.7.3-SNAPSHOT.jar TestJedis 
