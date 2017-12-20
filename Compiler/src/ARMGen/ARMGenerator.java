package ARMGen;

public class ARMGenerator {
	//creating a string buffer to save ARM commands
	StringBuffer ARMBuffer = new StringBuffer();
	
	ARMGenerator()
	{
		ARMBuffer.append("\t.text \n\t.global _start \n_main:");
	}
}
