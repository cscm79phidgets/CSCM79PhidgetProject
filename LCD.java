/*
 * Copyright 2011 Phidgets Inc.  All rights reserved.
 */

import com.phidgets.PhidgetException;
import com.phidgets.TextLCDPhidget;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;
import com.phidgets.event.ErrorEvent;
import com.phidgets.event.ErrorListener;

public class LCD {
    TextLCDPhidget lcd;
    public LCD() throws Exception {
        lcd = new TextLCDPhidget();

        lcd.addAttachListener(new AttachListener() {

            public void attached(AttachEvent ae) {
                System.out.println("attachment of " + ae);
            }
        });

        lcd.addDetachListener(new DetachListener() {

            public void detached(DetachEvent ae) {
                System.out.println("detachment of " + ae);
            }
        });

        lcd.addErrorListener(new ErrorListener() {

            public void error(ErrorEvent ee) {
                System.out.println("error event for " + ee);
            }
        });

        lcd.openAny();
        System.out.println("Waiting for the TextLCD to be attached...");
        lcd.waitForAttachment();

    }
    public void SetString(String sentence) throws PhidgetException {
    	lcd.setDisplayString(0, sentence);
    }
}
