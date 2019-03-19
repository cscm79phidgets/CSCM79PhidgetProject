import com.phidgets.*;
import com.phidgets.event.*;

public class Main {
	static InterFaceKit ik;
	static Motor motor;
	static LCD lcd;
	static final int RFIDNum[] = {1,25222,25239,39411,25232,25229,7,8,9,0,0};//the order number is on the board, the single number means we don't have the RFIDsensor now
	static int processNum =1;
	static final String process1 = "01023879ca";//turn light on
	static final String process2 = "0102389e47";//turn light off
	static final String process3 = "010693427c";//light flash slow
	static final String process4 = "010238822d";//light flash fast
	static final String process5 = "010693438f";//
	static final String process6 = "01069351f0";
	static final String process7 = "01069355e3";
	static final String process11 = "0102388e47";
	static final String process12 = "0106934b43";
	static final String process13 = "0102388fe2";
	static final String decision = "01023895e1";
	static final String endDecision = "0106936049";
	static final String endProgram = "01069341ed";
	static final String yes = "01069339f7";
	static final String no = "0106935187";

	//process function
	private static void proFunc(String a) throws InterruptedException, PhidgetException {
		switch(a) {
			case process1://turn light on
				lcd.SetString("Turn Light On");
				ik.SetOut(0, true);
				break;
			case process2://turn light off
				lcd.SetString("Turn Light Off");
				ik.SetOut(0,false);
				break;
			case process3://light flash slow
				lcd.SetString("Light Flash Slow");
				for(int i=0;i<3;++i) {
					ik.SetOut(0, true);
					Thread.sleep(750);
					ik.SetOut(0, false);
					Thread.sleep(750);
				}
				break;
			case process4://light flash fast
				lcd.SetString("Light Flash Fast");
				for(int i=0;i<3;++i) {
					ik.SetOut(0, true);
					Thread.sleep(250);
					ik.SetOut(0, false);
					Thread.sleep(250);
				}
				break;
			case decision://decision making to switch
				lcd.SetString("Decision");
//			Thread.sleep(500);
//			while(ik.GetIKValue(0)<20) {
//			}
//			lcd.SetString("Hello World");
				Thread.sleep(500);
				if(ik.GetIKValue(0)<20) {//initial value is 999, it will turn to 0 when push it
					processNum += 2;
					lcd.SetString("Pressing(No)");
				}else {
					lcd.SetString("Pressing(Yes)");
				}
				System.out.println("step"+processNum);
				break;
			case process5://controll motor
				lcd.SetString("Clockwise Turning");
				motor.setMotor(0, 10);
				motor.setMotor(1, 10);
				Thread.sleep(500);
				motor.setMotor(0, 10);
				motor.setMotor(1, 170);
				Thread.sleep(500);
				motor.setMotor(0, 170);
				motor.setMotor(1, 170);
				Thread.sleep(500);
				motor.setMotor(0, 170);
				motor.setMotor(1, 10);
				Thread.sleep(500);
				motor.setMotor(0, 10);
				motor.setMotor(1, 10);
				Thread.sleep(500);
				motor.setMotor(0, 90);
				motor.setMotor(1, 90);
				break;
			case process6:
				lcd.SetString("AnticlockwiseTurning");
				motor.setMotor(0, 10);
				motor.setMotor(1, 10);
				Thread.sleep(500);
				motor.setMotor(0, 170);
				motor.setMotor(1, 10);
				Thread.sleep(500);
				motor.setMotor(0, 170);
				motor.setMotor(1, 170);
				Thread.sleep(500);
				motor.setMotor(0, 10);
				motor.setMotor(1, 170);
				Thread.sleep(500);
				motor.setMotor(0, 10);
				motor.setMotor(1, 10);
				Thread.sleep(500);
				motor.setMotor(0, 90);
				motor.setMotor(1, 90);
				break;
			case process11:
				lcd.SetString("LED Function");
				break;
			case process12:
				lcd.SetString("Servo Motor Function");
				break;
			case process13:
				lcd.SetString("Combine Function");
				break;
			default:
				System.out.println("Waiting");
				Thread.sleep(500);
				break;
		}


	}
	//Main-------------------------------------------------------------------------------------------------
	/*
	 * problem:
	 * 		1.if set multipul RFIDsensor, they have to be attached
	 */
	public static void main(String args[]) throws Exception {
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
		//set new RFID, have to set it off at first
		lcd = new LCD();
		ik = new InterFaceKit();//set new InterFace
		motor = new Motor();//set new Motor
		Thread.sleep(1000);
		int touchSensor = ik.GetIKValue(6);
		while(true) {
			switch(processNum) {
				case 1:
					System.out.println("1");
					proFunc(rf1.getTag());//Use process function. Problem:have to put on a tag at the first
					processNum++;
					break;
				case 2:
					System.out.println("2");
					proFunc(rf2.getTag());//Use process function. Problem:have to put on a tag at the first
					processNum++;
					break;
				case 3:
					System.out.println("3");
					proFunc(rf3.getTag());//Use process function. Problem:have to put on a tag at the first
					processNum+=5;
					break;
				case 4:
					System.out.println("4");
					proFunc(rf4.getTag());//Use process function. Problem:have to put on a tag at the first
					processNum++;
					break;
				case 5:
					System.out.println("5");
					proFunc(rf5.getTag());//Use process function. Problem:have to put on a tag at the first
					processNum++;
					break;
				default:
					processNum = 1;
					lcd.SetString("");
			}

//Use process function. Problem:have to put on a tag at the first
		}

	}
}