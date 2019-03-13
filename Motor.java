import com.phidgets.*;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;
import com.phidgets.event.ErrorEvent;
import com.phidgets.event.ErrorListener;
import com.phidgets.event.ServoPositionChangeEvent;
import com.phidgets.event.ServoPositionChangeListener;

public class Motor {
	static ServoPhidget servo;
	public Motor() {
		try {
			servo = new ServoPhidget();
		} catch (PhidgetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		servo.addAttachListener(new AttachListener() {
			public void attached(AttachEvent ae) {
				System.out.println("attachment of " + ae);
			}
		});
		servo.addDetachListener(new DetachListener() {
			public void detached(DetachEvent ae) {
				System.out.println("detachment of " + ae);
			}
		});
		servo.addErrorListener(new ErrorListener() {
			public void error(ErrorEvent ee) {
				System.out.println("error event for " + ee);
			}
		});
		servo.addServoPositionChangeListener(new ServoPositionChangeListener()
		{
			public void servoPositionChanged(ServoPositionChangeEvent oe)
			{
				System.out.println(oe);
			}
		});
		try {
			servo.openAny();
		} catch (PhidgetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("waiting for Servo attachment...");
		try {
			servo.waitForAttachment();
		} catch (PhidgetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Motor(int ServoNum) {
		try {
			servo = new ServoPhidget();
		} catch (PhidgetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		servo.addAttachListener(new AttachListener() {
			public void attached(AttachEvent ae) {
				System.out.println("attachment of " + ae);
			}
		});
		servo.addDetachListener(new DetachListener() {
			public void detached(DetachEvent ae) {
				System.out.println("detachment of " + ae);
			}
		});
		servo.addErrorListener(new ErrorListener() {
			public void error(ErrorEvent ee) {
				System.out.println("error event for " + ee);
			}
		});
		servo.addServoPositionChangeListener(new ServoPositionChangeListener()
		{
			public void servoPositionChanged(ServoPositionChangeEvent oe)
			{
				System.out.println(oe);
			}
		});
		try {
			servo.open(ServoNum);
		} catch (PhidgetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("waiting for Servo attachment...");
		try {
			servo.waitForAttachment();
		} catch (PhidgetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setMotor(int motorNum, int position) throws InterruptedException, PhidgetException {
		servo.setPosition(motorNum, position);
		Thread.sleep(10);
	}
}
