# The Floow Challenge

execute the file :

java –Xmx8192m -jar challenge.jar –source dump.xml

1. FileSplitterService -
Description ::
 * Split the large file in to smaller chunks
 * Store Small Chunks in temp location (To Save Memory Space)
 * return the list of absolute path of chunk files .

2. UniqueWordCounterService -
Description ::
 * Given a file/ part of a file
 * find unique word and it's number of occurrence.
 * Return the results in a Hash Map (Key = word, Value= count)

3. DataUpsertService
Description:
 * Given HashMap with words and counts
 * Save the Top 10 (configurable in application. properties) most and least common words in the file

4. CommandLineRunner
Description:
 * Input validation
 * Trigger the execution
 * display the output

Note:
Application is fine tuned to produce a report of 5.61 GB file in 9 minutes.

