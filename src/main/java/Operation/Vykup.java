package Operation;

import Common.Common;
import Common.Driver;
import Common.DataBase;
import org.sikuli.script.*;

import java.util.List;

public class Vykup extends Driver {
    private Region VykupRG;
    private String NumberPerezalog;
    private int IDVykup;

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

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DataBase b = new DataBase();
        b.Connect();
        List<String[]> rs = b.Query("select first 1 \"TalmbVykup\".id from \"TalmbVykup\" order by \"TalmbVykup\".id desc");
        b.Closed();
        if (rs.size() >= 1) {
            IDVykup = Integer.parseInt(rs.get(0)[0]);
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

    public void saveIDVykupToBaseFactResult(int NumberOfTest) {
        DataBase b = new DataBase();
        b.Connect();
        b.QueryUpdate("update \"TestsList\" set \"ID_Vykup\" = "+IDVykup+" where \"NumberTest\" = "+NumberOfTest);
        b.Closed();
    }
}