package fr.gagoi;

public class Launcher {

	public static void main(String[] args) {
		new Thread(new fr.gagoi.basic_racer.Game()).start();
		//new Thread(new fr.gagoi.snow.Game()).start();
	}

}
