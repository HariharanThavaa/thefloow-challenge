# The Floow Challenge

To execute the file :

java -jar challenge.jar –source dump.xml

1. FileSplitterService -
 * Split the large file in to smaller chunks
 * Store Small Chunks in temp location (Chunk size can be configured in application.properties)
 * return the list of absolute path of chunk files .

2. WordCounterService -
 * Given a file/ part of a file
 * find unique word and it's number of occurrence.
 * Return the results in a Hash Map (Key = word, Value= count)

3. DataService
 * Given HashMap with words and counts
 * Save the Top 10 (configurable in application. properties) most and least common words in the file

4. Main - CommandLineRunner
 * Input validation
 * Trigger the execution
 * print response


Note:
Application is fine tuned to produce a report of 5.61 GB file in 280344 milliseconds or less than 5 minutes.
MongoDb default pointing to localhost:27017 can be configured in application.properties


