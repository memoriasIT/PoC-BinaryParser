package prCPUInstructionParser;

import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {
		String orig = "0405";

		String value = new BigInteger("0405", 16).toString(2);
		String binAddr = String.format("%16s", value).replace(" ", "0");
		
		System.out.println(orig + " : "+ binAddr);
		Line linea1 = new Line(binAddr);
		System.out.println(linea1);
		
	}

}
