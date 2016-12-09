package Operation;

import Common.Common;
import Common.Driver;
import org.sikuli.script.*;

public class Vykup extends Driver {
    private Region VykupRG;
    private String NumberPerezalog;

    public void Init() {
        try {

            Region HeadVykupRG, NumberPerezalogRG;
            Pattern VykupPT;

            HeadVykupRG = getDriver().wait((new Pattern(path("Vykup\\F_HeadVykup"))).similar((float) 0.65), 20);
            HeadVykupRG = HeadVykupRG.find(new Pattern(path("Vykup\\T_TitleVykup")));
            HeadVykupRG.hover();
            HeadVykupRG.mouseDown(Button.LEFT);
            HeadVykupRG.mouseMove(-1 * HeadVykupRG.getX() + 17, -1 * HeadVykupRG.getY() + 90);
            HeadVykupRG.mouseUp(Button.LEFT);

            VykupPT = new Pattern(path("Vykup\\F_Vykup"));
            VykupRG = getDriver().wait(VykupPT.similar((float) 0.60), 5);


        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void Open(String NumberZalog) throws FindFailed {
        if (NumberZalog == null || NumberZalog.isEmpty()) return;
        Pattern InputNumberZalogPT;
        Region InputNumberZalogRG;

        InputNumberZalogPT = new Pattern(path("Vykup\\F_InputNumberZalog"));
        InputNumberZalogRG = getDriver().wait(InputNumberZalogPT.similar((float) 0.7), 20);

        String[] InputText = NumberZalog.split(" ");

        InputNumberZalogRG.type(Common.toEnglish(InputText[0]));
        InputNumberZalogRG.type(Key.TAB);
        InputNumberZalogRG.type(Common.toEnglish(InputText[1]));
        InputNumberZalogRG.type(Key.ENTER);
    }

    public void saveVykup() {
        Region ButtonSaveRG;
        try {
            ButtonSaveRG = VykupRG.find(new Pattern(path("Vykup\\B_Save")));
            ButtonSaveRG.click();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void closeVykup() {
        Region ButtonCloseRG;
        try {
            ButtonCloseRG = VykupRG.find(new Pattern(path("Vykup\\B_Closed")));
            ButtonCloseRG.click();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }
}