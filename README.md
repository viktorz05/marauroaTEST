## Identification of the open-source project.
We are testing the [Maraurora](https://arianne-project.org/engine/marauroa.html) project
## Size of the code base (lines of code).
26 kLoC
## Proposed evaluation platform (OS, language).
Java
## Build time to compile and link an executable from source code.
5 seconds using `ant`
## Test suite infrastructure (JUnit?, Jasmine?, Mocha?, …).
Maraurora uses `JUnit`
## Number of tests, lines of code, & execution time for the test suite.
Total test execution time was 7 seconds

## The reported line coverage (numerator and denominator)
3979/12624
## The reported mutation coverage (numerator and denominator)
Generated 6288 mutations Killed 1533 (24%)
## The output coverage reports that provide clear evidence of usage
TODO
## Do the test suites exhibit weaknesses? How can they be improved?
Database and network tests are weak
## Do the test suites exhibit strengths? How do you recognize them?
TODO
## Contrast the costs and benefits of mutation analysis and testing versus what you might expect from other techniques.
TODO
## What was easy and what was difficult about applying the tools to the projects you chose?
The easy part was compiling and testing the project by itself. The hard part was getting PIT to work on our machines. Since Marauora uses `ant` to build, we had to get the correct `pitest` jars from the GitHub repo. WHich meant we needed to compile `pittest` itself and move the jars to the `libs/` directory in Marauora. Then we had to downgrade some libraries used by Marauora after reading all the error logs. 
## What obstacles did you face in applying mutation analysis to a real world project, and how did you overcome them?
PIT expected a a newer version of JUnit while our project was on an older release. Some of the database tests did not pass because the schema was not initialized during the tests. This is what claude told me but we should rewrite it in our own words:
> PIT runs all 260 test classes in one minion JVM, and static singletons like TransactionPool and DAORegister carry state between them. Ant’s <junit> with forkmode="once" does the same, but PIT runs tests in a different order and may run them more than once, so the collisions only show up there.
## Did you have any other interesting insights or opinions on the experience?
Each of us can say whatever ig. <br>
IMO (Viktor) it was interesting running the tests on this project which is an online game server, so as a user I wouldn't suspect how many interactions between components make the multiplayer game run. The fact that the mutation coverage is low may be a sign that the tests haven't been updated too often or they didn't take this approach into consideration when they were writing them.
**We should add our raw opinion first and then make it sound "academic" later**