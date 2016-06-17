package Common;

import org.sikuli.basics.Debug;
import org.sikuli.basics.Settings;
import org.sikuli.script.*;

import java.awt.event.KeyEvent;


public class MyRegion extends Region {

    private boolean isScreenUnion = false;

    public int type(String text) {
        try {
            return keyin(null, text, 0);
        } catch (FindFailed ex) {
            return 0;
        }
    }

    private <PFRML> int keyin(PFRML target, String text, int modifiers)
            throws FindFailed {
        if (target != null && 0 == click(target, 0)) {
            return 0;
        }
        Debug profiler = Debug.startTimer("Region.type");
        if (text != null && !"".equals(text)) {
            String showText = "";
            for (int i = 0; i < text.length(); i++) {
                showText += Key.toJavaKeyCodeText(text.charAt(i));
            }
            String modText = "";
            String modWindows = null;
            if ((modifiers & KeyModifier.WIN) != 0) {
                modifiers -= KeyModifier.WIN;
                modifiers |= KeyModifier.META;
               // log(lvl, "Key.WIN as modifier");
                modWindows = "Windows";
            }
            if (modifiers != 0) {
                modText = String.format("( %s ) ", KeyEvent.getKeyModifiersText(modifiers));
                if (modWindows != null) {
                    modText = modText.replace("Meta", modWindows);
                }
            }
            Debug.action("%s TYPE \"%s\"", modText, showText);
           // log(lvl, "%s TYPE \"%s\"", modText, showText);
            profiler.lap("before getting Robot");
            IRobot r = getRobotForRegion();
            int pause = 20 + (Settings.TypeDelay > 1 ? 1000 : (int) (Settings.TypeDelay * 1000));
            Settings.TypeDelay = 0.0;
            profiler.lap("before typing");
            r.typeStarts();
            for (int i = 0; i < text.length(); i++) {
                r.pressModifiers(modifiers);
                r.typeChar(text.charAt(i), IRobot.KeyMode.PRESS_RELEASE);
                r.releaseModifiers(modifiers);
                r.delay(pause);
            }
            r.typeEnds();
            profiler.lap("after typing, before waitForIdle");
            r.waitForIdle();
            profiler.end();
            return 1;
        }

        return 0;
    }

    protected MyRegion(boolean isScreenUnion) {
        super(isScreenUnion);
        this.isScreenUnion = isScreenUnion;
        //this.rows = 0;
    }

    private IRobot getRobotForRegion() {
        if (getScreen() == null || isScreenUnion) {
            return Screen.getPrimaryScreen().getRobot();
        }
        return getScreen().getRobot();
    }
}
