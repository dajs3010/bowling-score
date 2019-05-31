To compile the project you should run this command in the root project directory:

mvn package

To run the project you must execute:
java -jar target/bowling-score-1.0-SNAPSHOT.jar <file path argument>
E.g:
java -jar target/bowling-score-1.0-SNAPSHOT.jar ./scores/score.txt

Or execute:
mvn spring-boot:run -Dspring-boot.run.arguments=<file path argument>
E.g:
mvn spring-boot:run -Dspring-boot.run.arguments=./scores/score.txt

Notes:

  Score files are in scores folder, it contains a perfect score file, worst score file and the sample score file (with jeff and john match), you could add more files if you want.

  To generate a coverage report you could run (before run mvn package):
    mvn org.pitest:pitest-maven:mutationCoverage
  This will output an html report to target/pit-reports/YYYYMMDDHHMI

  Tested with java 1.8.0_212 version
