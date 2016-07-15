package Operation;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static Common.Common.WorkDate;

public class Perezalog {

    public void Init(){

    }
   /* public void SetDateReturn(String StrDateReturn)  {
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

        // SetKolPeriod(n);

    }
    public void SetKolPeriod(int n){

    }
    public void SaveZalog() {
        Region ButtonSaveRG;
        try {
            ButtonSaveRG = ZalogRG.find(new Pattern(path("Zalog\\B_Save")));
            ButtonSaveRG.click();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void CloseZalog() {
        Region ButtonCloseRG;
        try {
            ButtonCloseRG = ZalogRG.find(new Pattern(path("Zalog\\B_Closed")));
            ButtonCloseRG.click();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }*/

}
