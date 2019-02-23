# The Floow Challenge

### Monolith first approach

Starting with a monolith first approach, Identified below services in whiteboard discussion.

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
 * update or insert Data

4. ChallengeReportService
Description:
 * Report on statistically interesting words (in addition to the least and most common words)
 * By querying the datastore

5. CommandLineRunner
Description:
 * Input validation
 * Trigger the execution


* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)

note:
Large file used as a test data for Splitter service is located in :
challenge/src/test/resources/fixtures/enwiki-latest-abstract.xml

