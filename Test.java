import com.phidgets.*;
import com.phidgets.event.*;

public class Test {
	static Motor motor;
	static final int RFIDNum[] = {25232, 25229, 25239, 39411, 39660, 25222, 7, 8, 9, 39660, 20986};//the order number is on the board, the single number means we don't have the RFIDsensor now
	static int processNum = 1;
	static InterfaceKitPhidget ik;
	static RFIDPhidget rfid;
	static final String process = null;
	static final String Process1 = "0102389925";//turn light on  //change to 0102389925  revert to 01023879ca when testing complete
	static final String Process2 = "010238999f";//light flash slow  change to 010238999f revert to 0102389e47 when testing complete
	static final String Process3 = "010693427c";//light flash fast
	static final String Decision = "01023895e1";
	static final String EndDecision = "0106936049";
	static final String EndProgram = "01069341ed";
	static final String Yes = "01069339f7";
	static final String No = "0106935187";

	//process function
	private static void proFunc(String a) throws InterruptedException, PhidgetException {

		switch (a) {
			case "0102389925"://turn light on
				InterFaceKit.SetOut(0, true);
				break;
			case "null1"://turn light off
				InterFaceKit.SetOut(0, false);
				break;
			case "010238999f"://light flash slow
				InterFaceKit.SetOut(0, true);
				Thread.sleep(1000);
				InterFaceKit.SetOut(0, false);
				Thread.sleep(1000);
				break;
			case "010693427c"://light flash fast
				InterFaceKit.SetOut(0, true);
				Thread.sleep(250);
				InterFaceKit.SetOut(0, false);
				Thread.sleep(250);
				break;
			case ""://decision making to switch
				processNum += 6;
			case "null2"://controll motor
				motor.setMotor(0, ((int) Math.random() * 200));
				motor.setMotor(1, ((int) Math.random() * 200));
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

	//String end decision tag = "0102389925"
	//String process led short tag = "010238999f"
	public static void main(String args[]) throws Exception {
		int a = RFIDNum[0];
		readRFID rf1 = new readRFID(RFIDNum[0]);
//		RFIDPhidget rf1 = new RFIDPhidget();
//		RFIDPhidget rf2 = new RFIDPhidget();
//		RFIDPhidget rf3 = new RFIDPhidget();
//		RFIDPhidget rf4 = new RFIDPhidget();
//		RFIDPhidget rf5 = new RFIDPhidget();//set new RFID


		ik = new InterfaceKitPhidget();

//		rf1.addAttachListener(new AttachListener() {
//			public void attached(AttachEvent ae) {
//				try {
//					((RFIDPhidget) ae.getSource()).setAntennaOn(true);
//					((RFIDPhidget) ae.getSource()).setLEDOn(true);
//				} catch (PhidgetException ex) {
//				}
//				System.out.println("attachment of " + ae);
//			}
//		});
//		rf1.addDetachListener(new DetachListener() {
//			public void detached(DetachEvent ae) {
//				System.out.println("detachment of " + ae);
//			}
//		});
//		rf1.addErrorListener(new ErrorListener() {
//			public void error(ErrorEvent ee) {
//				System.out.println("error event for " + ee);
//			}
//		});
//		rf1.addTagGainListener(new TagGainListener() {
//			public void tagGained(TagGainEvent oe) {
//				System.out.println("Tag Gained: " + oe.getValue() + " (Proto:" + oe.getProtocol() + ")");
//			}
//		});
//		rf1.addTagLossListener(new TagLossListener() {
//			public void tagLost(TagLossEvent oe) {
//				System.out.println(oe);
//			}
//		});
//		rf1.addOutputChangeListener(new OutputChangeListener() {
//			public void outputChanged(OutputChangeEvent oe) {
//				System.out.println(oe);
//			}
//		});
//
//		rf1.open(RFIDNum[0]);
//		rf2.open(RFIDNum[1]);
//		rf3.open(RFIDNum[2]);
//		rf4.open(RFIDNum[3]);
//		rf5.open(RFIDNum[4]);

//		System.out.println("waiting for RFID attachment...");
//		rf1.waitForAttachment(1000);

		ik.addAttachListener(new AttachListener() {
			@Override
			public void attached(AttachEvent ae) {
				System.out.println("attachment of " + ae);
			}
		});
		ik.addDetachListener((DetachEvent ae) -> {
			System.out.println("detachment of " + ae);
		});
		ik.addErrorListener((ErrorEvent ee) -> {
			System.out.println("error event for " + ee);
		});
		ik.addInputChangeListener((InputChangeEvent oe) -> {
			System.out.println(oe);
		});
		ik.addOutputChangeListener((OutputChangeEvent oe) -> {
			System.out.println(oe);
		});
		ik.addSensorChangeListener((SensorChangeEvent se) -> {
			System.out.println(se);
		});
		ik.openAny();
		System.out.println("waiting for InterfaceKit attachment...");
		ik.waitForAttachment();


		//BEGIN LOOP
		while (true) {

//			boolean tagGained = rf1.getTagStatus();


//			while (rf1.getTagStatus() == true) {
//
			if (rf1.getTag().equals("0102389e47")) {
				ik.setOutputState(0, true);
				Thread.sleep(250);
				ik.setOutputState(0, false);
				Thread.sleep(250);
			}

//			}

			int sensorVal = ik.getSensorValue(5);

			if (sensorVal < 5) {
				ik.setOutputState(0, true);
				processNum = 1;
			}
			if (sensorVal < 5) {
				ik.setOutputState(0, true);
			} else ik.setOutputState(0, false);

			for(int i=0; i<= RFIDNum.length; ++i) {

			}


			System.out.println(rf1.getTag());

			processNum = RFIDNum[0];

			switch (processNum) {
				case 1:
					proFunc(rf1.getTag());//Use process function. Problem:have to put on a tag at the first
					break;
				default:
					System.out.println(rf1.getTag());
					break;
////						 case 2:
////							 proFunc(rf2.getTag());//Use process function. Problem:have to put on a tag at the first
////							 break;
////						 case 3:
////							 proFunc(rf3.getTag());//Use process function. Problem:have to put on a tag at the first
////							 break;
////						 case 4:
////							 proFunc(rf4.getTag());//Use process function. Problem:have to put on a tag at the first
////							 break;
////						 case 5:
////							 proFunc(rf5.getTag());//Use process function. Problem:have to put on a tag at the first
////							 break;
			}
		}
	}
}




//Use process function. Problem:have to put on a tag at the first




