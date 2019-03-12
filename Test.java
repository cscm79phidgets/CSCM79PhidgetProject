import com.phidgets.*;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;
import com.phidgets.event.ErrorEvent;
import com.phidgets.event.ErrorListener;
import com.phidgets.event.InputChangeEvent;
import com.phidgets.event.InputChangeListener;
import com.phidgets.event.OutputChangeEvent;
import com.phidgets.event.OutputChangeListener;
import com.phidgets.event.SensorChangeEvent;
import com.phidgets.event.SensorChangeListener;
import java.util.HashSet;
import java.util.Set;
import com.phidgets.event.*;

public class Test {
	static Motor motor;
	static final int RFIDNum[] = {63555,25265,63851,39411,5,25229,7,8,9,39660,20986};//the order number is on the board, the single number means we don't have the RFIDsensor now
	static int processNum =1;
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
                    
                switch(a) {
		case "0102389925"://turn light on
			InterFaceKit.SetOut(0, true);
			break;
		case "null1"://turn light off
			InterFaceKit.SetOut(0,false);	
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
	
	//String end decision tag = "0102389925"
	//String process led short tag = "010238999f"
	public static void main(String args[]) throws Exception {
		 ik = new InterfaceKitPhidget();
                 
//                 rfid = new RFIDPhidget();
                 RFIDPhidget rfid;
                 rfid = new RFIDPhidget();
                 		rfid.addAttachListener(new AttachListener() {
			public void attached(AttachEvent ae)
			{
				try
				{
					((RFIDPhidget)ae.getSource()).setAntennaOn(true);
					((RFIDPhidget)ae.getSource()).setLEDOn(true);
				}
				catch (PhidgetException ex) { }
				System.out.println("attachment of " + ae);
			}
		});
		rfid.addDetachListener(new DetachListener() {
			public void detached(DetachEvent ae) {
				System.out.println("detachment of " + ae);
			}
		});
		rfid.addErrorListener(new ErrorListener() {
			public void error(ErrorEvent ee) {
				System.out.println("error event for " + ee);
			}
		});
		rfid.addTagGainListener(new TagGainListener()
		{
			public void tagGained(TagGainEvent oe)
			{
				System.out.println("Tag Gained: " +oe.getValue() + " (Proto:"+ oe.getProtocol()+")");
			}
		});
		rfid.addTagLossListener(new TagLossListener()
		{
			public void tagLost(TagLossEvent oe)
			{
				System.out.println(oe);
			}
		});
		rfid.addOutputChangeListener(new OutputChangeListener()
		{
			public void outputChanged(OutputChangeEvent oe)
			{
				System.out.println(oe);
			}
		});

		rfid.openAny();
		System.out.println("waiting for RFID attachment...");
		rfid.waitForAttachment(1000);
		
//		while (true) {
//			boolean tagGained = rfid.getTagStatus();
//			if (tagGained = true) {
//				
//			}
//		}
                 
                 
//                readRFID rf1 = new readRFID(25265);
//		readRFID rf2 = new readRFID(39411);
//		readRFID rf3 = new readRFID(63555);
//                rfid.openAny();
                
//		readRFID rf4 = new readRFID();
//		readRFID rf5 = new readRFID();//set new RFID
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
                 while (true) {
                                                            
                    boolean tagGained = rfid.getTagStatus();


                    
//                    if (rfid.getTag().equals("0102389925")) {
//                        ik.setOutputState(0, true);
//                        Thread.sleep(1000);
//                        ik.setOutputState(0, false);
//                        Thread.sleep(1000);
//                    }
                    int sensorVal = ik.getSensorValue(5);
//                  if (rfid.getLastTag().equals("0102389925")) {
//                     ik.setOutputState(0, true);
//                 }
                    if (sensorVal < 5) {
                        ik.setOutputState(0, true);
                        processNum = 1;
                    }
//                    if (rfid.getTagStatus()== true) {
//                         String tag = rfid.getLastTag();
//                        if(rfid.getLastTag().equals(No)){
//                            System.out.println(tag);
//                        }
//                       
//
////                      
//                    }
                    
                    else {
                      ik.setOutputState(0, false);
                    }
                 
                 
//
//		 while (true) {
//                    int sensorVal = ik.getSensorValue(6);
//		    if (sensorVal <5) {
//                        ik.setOutputState(0, true);
//                    }
//                    else ik.setOutputState(0, false);
//		 
////                    while(sensorVal>5) {
////			processNum = 1;//reset program
////                    }
//                 }
                    
//                    for(int i=0; i<= RFIDNum.length; ++i) {
//                        
//                    }
                    
//                    System.out.println(rf1.getTag());
                    
//                    processNum = RFIDNum[0];
//                                      
//                    switch(processNum) {
//                    case 1:
//                    	proFunc(rfid.getLastTag());//Use process function. Problem:have to put on a tag at the first
//			break;
//                    default: 
//                        System.out.println(rfid.getLastTag());
//                        break;
////                    case 2:
////			proFunc(rf2.getTag());//Use process function. Problem:have to put on a tag at the first
////			break;
////                    case 3:
////			proFunc(rf3.getTag());//Use process function. Problem:have to put on a tag at the first
////			break;
////                    case 4:
////			proFunc(rf4.getTag());//Use process function. Problem:have to put on a tag at the first
////			break;
////                    case 5:
////			proFunc(rf5.getTag());//Use process function. Problem:have to put on a tag at the first
////			break;
//                    }
                 }
        }
}
                    
//Use process function. Problem:have to put on a tag at the first
	
        
			
	
