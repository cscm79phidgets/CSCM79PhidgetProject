import com.phidgets.*;
import com.phidgets.event.*;

public class readRFID {
 //	public String theRFIDNumber;
	RFIDPhidget rfid;
	
	public readRFID(int theRFIDSerial) throws Exception {
		//RFIDPhidget rfid;
		rfid = new RFIDPhidget();
		//add attach listener
		rfid.addAttachListener(new AttachListener() {
			public void attached(AttachEvent ae)
			{
				try
				{
					((RFIDPhidget)ae.getSource()).setAntennaOn(true);
					((RFIDPhidget)ae.getSource()).setLEDOn(true);
				}
				catch (PhidgetException ex) { }
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
		
		rfid.open(theRFIDSerial);
		rfid.waitForAttachment();
		System.out.println("working"+theRFIDSerial);
	}
	public readRFID() throws Exception {
		//RFIDPhidget rfid;
		rfid = new RFIDPhidget();
		//add attach listener
		rfid.addAttachListener(new AttachListener() {
			public void attached(AttachEvent ae)
			{
				try
				{
					((RFIDPhidget)ae.getSource()).setAntennaOn(true);
					((RFIDPhidget)ae.getSource()).setLEDOn(true);
				}
				catch (PhidgetException ex) { }
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
		rfid.openAny();
		rfid.waitForAttachment();
		System.out.println("working");
		
	}
	
	public String getTag() throws Exception {
		String tagString = "";
		this.setOnOff(true);
		if(rfid.getTagStatus()) {
			try {
				tagString = rfid.getLastTag();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.setOnOff(false);
		return tagString;
	}
	
	public void setOnOff(boolean OnOff) throws PhidgetException, InterruptedException {
		rfid.setAntennaOn(OnOff);
		if(OnOff) {
			System.out.println("set on");
			Thread.sleep(500);
		}else {
			System.out.println("set off");
			Thread.sleep(500);
		}
	}
	
//	public static String getRFIDNumber() {
//		return theRFIDNumber;
//	}

}
