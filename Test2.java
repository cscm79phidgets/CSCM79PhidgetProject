import com.phidgets.*;
import com.phidgets.event.*;

public class Test2 {
	static InterFaceKit ik;
	static Motor motor;
	static final int RFIDNum[] = {1,25222,25239,39411,25232,25229,7,8,9,0,0};//the order number is on the board, the single number means we don't have the RFIDsensor now
	static int processNum =1;
	static final String process = null;
	static final String Process1 = "01023879ca";//turn light on
	static final String Process2 = "0102389e47";//light flash slow
	static final String Process3 = "010693427c";//light flash fast
	static final String Decision = "01023895e1";
	static final String EndDecision = "0106936049";
	static final String EndProgram = "01069341ed";
	static final String Yes = "01069339f7";
	static final String No = "0106935187";

	//Main-------------------------------------------------------------------------------------------------
	/*
	 * problem:
	 * 		1.if set multipul RFIDsensor, they have to be attached
	 */
	public static void main(String args[]) throws Exception {
		//set new RFID
		readRFID rf1 = new readRFID(RFIDNum[1]);
		rf1.setOnOff(false);
		readRFID rf2 = new readRFID(RFIDNum[2]);
		rf2.setOnOff(false);
		readRFID rf3 = new readRFID(RFIDNum[3]);
		rf3.setOnOff(false);
		readRFID rf4 = new readRFID(RFIDNum[4]);
		rf4.setOnOff(false);
		readRFID rf5 = new readRFID(RFIDNum[5]);
		rf5.setOnOff(false);

		Thread.sleep(1000);
		while(true) {
			System.out.println(rf1.getTag());
			System.out.println(rf2.getTag());
			System.out.println(rf3.getTag());
			System.out.println(rf4.getTag());
			System.out.println(rf5.getTag());
		}

	}
}