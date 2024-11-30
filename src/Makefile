target: something

something: Main.java
	clear; javac Main.java -d tmp && cd tmp && java Main && cd

test: Test.java TestRunner.java
	clear; javac -cp "lib/junit-4.13.2.jar;lib/system-rules-1.19.0.jar" Test.java TestRunner.java -d tmp && cd tmp && java TestRunner && cd
