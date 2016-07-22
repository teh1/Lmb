package Operation;

import Common.Common;
import org.sikuli.script.*;

import Common.Common;
import Common.DataBase;
import Common.Driver;

import java.net.URISyntaxException;

import static Common.Common.WorkDate;



public class Perezalog extends Driver {

    private Region PerezalogRG;
    private String NumberPerezalog;

    public void Init(){
        try {

            Region HeadPerezalogRG, NumberPerezalogRG;
            Pattern PerezalogPT;

            PerezalogPT = new Pattern(path("Perezalog\\F_Perezalog"));

            HeadPerezalogRG =getDriver().wait((new Pattern(path("Perezalog\\F_HeadPerezalog"))).similar((float) 0.7),20);
            HeadPerezalogRG = HeadPerezalogRG.find(new Pattern(path("Perezalog\\T_TitlePerezalog")));
            HeadPerezalogRG.hover();
            HeadPerezalogRG.mouseDown(Button.LEFT);
            HeadPerezalogRG.mouseMove(-1*HeadPerezalogRG.getX()+17,-1*HeadPerezalogRG.getY()+90);
            HeadPerezalogRG.mouseUp(Button.LEFT);

            PerezalogRG = getDriver().wait(PerezalogPT.similar((float) 0.65),5);

            /*NumberPerezalogRG = PerezalogRG.find(new Pattern(path("Perezalog\\E_NumberZalog")));

            NumberPerezalogRG = NumberPerezalogRG.right(NumberPerezalogRG.getW() + 30);
            NumberPerezalogRG.click();
            NumberPerezalog = Common.getEditValue(NumberPerezalogRG);*/

        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void Open(String NumberZalog) throws FindFailed {
        if (NumberZalog == null || NumberZalog.isEmpty() ) return;
        Pattern InputNumberZalogPT;
        Region InputNumberZalogRG;

        InputNumberZalogPT = new Pattern(path("Perezalog\\F_InputNumberZalog"));
        InputNumberZalogRG = getDriver().wait(InputNumberZalogPT.similar((float) 0.7),20);

        String[] InputText = NumberZalog.split(" ");

        InputNumberZalogRG.type(Common.toEnglish(InputText[0]));
        InputNumberZalogRG.type(Key.TAB);
        InputNumberZalogRG.type(Common.toEnglish(InputText[1]));
        InputNumberZalogRG.type(Key.ENTER);
    }

    public void setDateReturn(String aDate){
        Region DatesRG, DateReturnRG;
        Common.setClipboardContents(aDate);
        try {
            DatesRG = PerezalogRG.find(new Pattern(path("Perezalog\\F_Dates")));
            DatesRG.setRaster(4,1);
            DateReturnRG = DatesRG.getCell(3,0);
            DateReturnRG.setRaster(1,2);
            DateReturnRG = DateReturnRG.getCell(0,1);
            DateReturnRG.click();
            Common.ClearEdit(DateReturnRG);
            Common.Paste(DateReturnRG);
            DateReturnRG.type(Key.ENTER);


        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }

        /* subSessiaRG.setRaster(2, 1);
            return subSessiaRG.getCell(num, 0);*/
    }
    public void getSumPersentForUse(){
        Region SumsRG, SumPersentUseRG;
        try {
            SumsRG = PerezalogRG.find(new Pattern(path("Perezalog\\F_Sums")));
            SumsRG.setRaster(4,1);
            SumPersentUseRG = SumsRG.getCell(3,0);


        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }

    }

   /* public void setDateReturn(String StrDateReturn)  {
        int n;
        if (StrDateReturn == null) StrDateReturn = "currentdate";

        //SetWorkDate();
        // Calendar calendar = new GregorianCalendar(2016,Calendar.FEBRUARY,28);
        Calendar DateReturn = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); //, Locale.JAPAN
        try {
            DateReturn.setTime(sdf.parse(StrDateReturn));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        n = (int)((DateReturn.getTimeInMillis() - WorkDate.getTimeInMillis())/(1000*60*60*24));
        System.out.println(n);

        // setkolperiod(n);

    }
    public void setkolperiod(int n){

    }
    public void saveZalog() {
        Region ButtonSaveRG;
        try {
            ButtonSaveRG = ZalogRG.find(new Pattern(path("Zalog\\B_Save")));
            ButtonSaveRG.click();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void closeZalog() {
        Region ButtonCloseRG;
        try {
            ButtonCloseRG = ZalogRG.find(new Pattern(path("Zalog\\B_Closed")));
            ButtonCloseRG.click();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }*/

}