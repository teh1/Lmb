package Common;

import org.sikuli.basics.Debug;
import org.sikuli.script.Key;
import org.sikuli.script.RobotDesktop;
import org.sikuli.script.Screen;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MyRobotDesktop extends RobotDesktop {

    public MyRobotDesktop(Screen screen) throws AWTException {
        super(screen);
    }

    public MyRobotDesktop() throws AWTException {
    }

    private void doKeyPress(int keyCode) {
       // logRobot(stdAutoDelay, "KeyPress: WaitForIdle: %s - Delay: %d");
        setAutoDelay(stdAutoDelay);
        setAutoWaitForIdle(false);
        keyPress(keyCode);
        if (stdAutoDelay == 0) {
            delay(stdDelay);
        }
        //logRobot("KeyPress: extended delay: %d", stdMaxElapsed);
    }

    private void doKeyRelease(int keyCode) {
       // logRobot(stdAutoDelay, "KeyRelease: WaitForIdle: %s - Delay: %d");
        setAutoDelay(stdAutoDelay);
        setAutoWaitForIdle(false);
        keyRelease(keyCode);
        if (stdAutoDelay == 0) {
            delay(stdDelay);
        }
     //   logRobot("KeyRelease: extended delay: %d", stdMaxElapsed);
    }

    private void doType(KeyMode mode, int... keyCodes) {
        if (mode == KeyMode.PRESS_ONLY) {
            for (int i = 0; i < keyCodes.length; i++) {
                doKeyPress(keyCodes[i]);
            }
        } else if (mode == KeyMode.RELEASE_ONLY) {
            for (int i = 0; i < keyCodes.length; i++) {
                doKeyRelease(keyCodes[i]);
            }
        } else {
            for (int i = 0; i < keyCodes.length; i++) {
                doKeyPress(keyCodes[i]);
            }
            for (int i = 0; i < keyCodes.length; i++) {
                doKeyRelease(keyCodes[i]);
            }
        }
    }

    public void typeChar(char character, KeyMode mode) {
        Debug.log(4, "Robot: doType: %s ( %d )",
                KeyEvent.getKeyText(Key.toJavaKeyCode(character)[0]).toString(),
                Key.toJavaKeyCode(character)[0]);
        doType(mode, Key.toJavaKeyCode(character));
    }

}
