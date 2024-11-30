target: something

something: Main.java
	clear; javac Main.java -d tmp && cd tmp && java Main && cd

login_test: Util.java Credentials.java LoginTest.java TestRunner.java
	clear; javac -cp .:junit-4.10.jar Util.java Credentials.java LoginTest.java TestRunner.java && java TestRunner
