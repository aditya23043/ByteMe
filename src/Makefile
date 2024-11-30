target: something

something: Main.java
	clear; javac Main.java -d tmp && cd tmp && java Main && cd

login_test: Util.java Credentials.java LoginTest.java test/TestRunner_1.java
	clear; javac -cp .:junit-4.10.jar Util.java Credentials.java LoginTest.java test/TestRunner_1.java -d test && cd test && java -cp .:../junit-4.10.jar TestRunner_1 && cd ..

stock_test: Menu.java Customer.java OutOfStockTest.java test/TestRunner_2.java
	clear; javac -cp .:junit-4.10.jar Menu.java Customer.java OutOfStockTest.java test/TestRunner_2.java -d test && cd test && java -cp .:../junit-4.10.jar TestRunner_2 && cd ..
