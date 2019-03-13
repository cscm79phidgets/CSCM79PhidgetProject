import com.phidgets.*;
import com.phidgets.event.*;

public class Test {
	static InterFaceKit ik;
	static Motor motor;
	static final int RFIDNum[] = {63555,25265,63851,39411,5,25229,7,8,9,39660,20986};//the order number is on the board, the single number means we don't have the RFIDsensor now
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
	
	//process function
	private static void proFunc(String a) throws InterruptedException, PhidgetException {
		switch(a) {
		case "01023879ca"://turn light on
			ik.SetOut(0, true);
			break;
		case "null1"://turn light off
			ik.SetOut(0,false);	
			break;
		case "0102389e47"://light flash slow
			ik.SetOut(0, true);
			Thread.sleep(1000);
			ik.SetOut(0, false);
			Thread.sleep(1000);
			break;
		case "010693427c"://light flash fast
			ik.SetOut(0, true);
			Thread.sleep(250);
			ik.SetOut(0, false);
			Thread.sleep(250);
			break;
		case ""://decision making to switch 
			processNum +=6;
		case "null2"://controll motor
			motor.setMotor(0, ((int)Math.random()*200));
			motor.setMotor(1, ((int)Math.random()*200));
			break;
		default:
			System.out.println("Waiting");
			Thread.sleep(1000);
			break;
		}

			
	}
//Main-------------------------------------------------------------------------------------------------
	/*
	 * problem:
	 * 		1.if set multipul RFIDsensor, they have to be attached
	 */
	public static void main(String args[]) throws Exception {
                int a = RFIDNum[0];
		readRFID rf1 = new readRFID(RFIDNum[0]);                
		readRFID rf2 = new readRFID(RFIDNum[1]);
		readRFID rf3 = new readRFID(RFIDNum[2]);
		readRFID rf4 = new readRFID(RFIDNum[3]);
//		readRFID rf5 = new readRFID();//set new RFID
		
		ik = new InterFaceKit();//set new InterFace
		motor = new Motor();//set new Motor
		Thread.sleep(1000);
		int touchSensor = ik.GetIKValue(6);
		while(true) {
			while(touchSensor>500) {
				touchSensor = ik.GetIKValue(6);//Push touch sensor to start
				processNum = 1;//reset program
			}
			touchSensor = ik.GetIKValue(6);
			switch(processNum) {
			case 1:
				proFunc(rf1.getTag());//Use process function. Problem:have to put on a tag at the first
				break;
			case 2:
				proFunc(rf2.getTag());//Use process function. Problem:have to put on a tag at the first
				break;
			case 3:
				proFunc(rf3.getTag());//Use process function. Problem:have to put on a tag at the first
				break;
			case 4:
				proFunc(rf4.getTag());//Use process function. Problem:have to put on a tag at the first
				break;
//			case 5:
//				proFunc(rf5.getTag());//Use process function. Problem:have to put on a tag at the first
//				break;
			}
//Use process function. Problem:have to put on a tag at the first
		}
			
	}
}
