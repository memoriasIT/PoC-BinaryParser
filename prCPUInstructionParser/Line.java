package prCPUInstructionParser;

public class Line {
	private String Mneumonic;
	private int destination;
	private int origin;
	private int value;
	
	public Line(String orig) {
		String a = orig.replaceAll("\\s+","");
		parseOPCODE(a.substring(0, 2));
		switch (Mneumonic) {
		case "ADD":
			parseADDMode(a);
			break;
		case "JZ":
			origin = getTwosComplement(a.substring(2, 16));
			Mneumonic += " "+origin;
			break;
		}
	}
	
	private void parseADDMode(String a) {
		int ADDType = parseADD(a.substring(14, 16));
		switch (ADDType) {
		case 1: //OK
			destination = Integer.parseInt(a.substring(2, 6), 2);
			origin = Integer.parseInt(a.substring(6, 10), 2);
			Mneumonic += " R"+ origin +" --> R"+destination;
			break;
		case 2: //OK
			destination = Integer.parseInt(a.substring(2, 6), 2);
			origin = getTwosComplement(a.substring(6, 14));
			Mneumonic += " #"+ origin +" --> R"+destination +"\n";
			break;
		case 3: //OK
			destination = Integer.parseInt(a.substring(2, 6), 2);
			origin = Integer.parseInt(a.substring(6, 10), 2);
			Mneumonic += " MEM[R"+ origin +"] --> R"+destination;
			break;
		case 4: 
			destination = Integer.parseInt(a.substring(2, 6), 2);
			origin = getTwosComplement(a.substring(6, 14));
			if (origin < 0) {
				Mneumonic += " (PC"+origin + ") --> R"+destination;
			} else {
				Mneumonic += " (PC+"+origin + ") --> R"+destination;
			}
			break;
		default:
			Mneumonic += " NOT CORRECT";
			break;
		}
		
	}

	private static int getTwosComplement(String binaryInt) {
	    //Check if the number is negative.
	    //We know it's negative if it starts with a 1
	    if (binaryInt.charAt(0) == '1') {
	        //Call our invert digits method
	        String invertedInt = invertDigits(binaryInt);
	        //Change this to decimal format.
	        int decimalValue = Integer.parseInt(invertedInt, 2);
	        //Add 1 to the curernt decimal and multiply it by -1
	        //because we know it's a negative number
	        decimalValue = (decimalValue + 1) * -1;
	        //return the final result
	        return decimalValue;
	    } else {
	        //Else we know it's a positive number, so just convert
	        //the number to decimal base.
	        return Integer.parseInt(binaryInt, 2);
	    }
	}

	private static String invertDigits(String binaryInt) {
	    String result = binaryInt;
	    result = result.replace("0", " "); //temp replace 0s
	    result = result.replace("1", "0"); //replace 1s with 0s
	    result = result.replace(" ", "1"); //put the 1s back in
	    return result;
	}
	
	private int parseADD(String code) {
		int type = -1;
		switch(code) {
		case "00":
			type = 1; 
			break;
		case "01":
			type = 2; 
			break;	
		case "10":
			type = 3; 
			break;
		case "11":
			type = 4; 
			break;
		}
		return type;
	}

	public Line() {
		value = 0;
		Mneumonic = "NOP";
		origin = 0;
		destination = 0;
	}
	
	
	private void parseOPCODE(String code) {
		switch(code) {
		case "00":
			Mneumonic = "ADD";
			break;
		case "10":
			Mneumonic = "JZ";
			break;
		default:
			Mneumonic = "NOP";
			break;
		}
		
	}
	
	
	@Override
	public String toString() {
		return Mneumonic;
	}
	
}
