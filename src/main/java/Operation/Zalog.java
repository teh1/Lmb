package Operation;

import Common.Common;
import Common.DataBase;
import Common.Driver;
import static Common.Common.WorkDate;

import org.sikuli.script.*;
import org.sikuli.script.Observer;

import java.net.URISyntaxException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Zalog extends Driver{
    //private final float SEC = 0.5f;

    private Region ZalogRG;
    private Pattern ZalogPT;

    public void SetDiscountCardNumber(){
        Region CardNumberRG = null;
        try {
            CardNumberRG = ZalogRG.find(new Pattern(path("Zalog\\E_DiscountCardNumber")));
            CardNumberRG.click();
            Common d = new Common();
            //CardNumberRG.type(d.toEnglish("Андрієць О"));
            CardNumberRG.type(",euftyrj");

        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        ZalogRG.highlight(2);
    }
    public void SetDiscountCardNumber(String CardNumber){
        Region CardNumberRG;
        try {
            CardNumberRG = ZalogRG.find(new Pattern(path("Zalog\\E_DiscountCardNumber")));
            CardNumberRG.click();
            CardNumberRG.type(CardNumber);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        ZalogRG.highlight(2);
    }

    public Zalog() throws FindFailed, URISyntaxException {
        //username = new Pattern(path("ShortCutBar"));

        ZalogPT = new Pattern(path("Zalog\\f_Zalog"));
       /* errorMessage = new Pattern(DataProperties.path("loginFailedLoginMessage.png"));
        */
    }

/*
    def changed(event):
    print "something changed in ", event.region
    for ch in event.getChanges():
            ch.highlight() # highlight all changes
                                           wait(1)
    for ch in event.getChanges():
            ch.highlight() # turn off the highlights

    r = selectRegion("select a region to observe")
    # any change in r larger than 50 pixels would trigger the changed function
    r.onChange(50, changed)
            r.observe(background=True)

    wait(30) # another way to observe for 30 seconds
    r.stopObserver()*/


    void changed(Observer event)  {
       // System.out.println(event.);

       /* for (Match ch : event.getChanges()) {
            ch.highlight();
        }
        wait(1);*/


    }


    public void StartZalog() {
        try {


            ZalogRG = getDriver().wait(ZalogPT.similar((float) 0.7),20);
            ZalogRG.highlight(2);

            //changed();

         //   ZalogForm.onChange(changed);
            //ZalogForm.observe;
            //wait(30);
            ZalogRG.stopObserver();

            //System.out.println(ZalogForm.onChange());
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void SelectFIO() {
        Region fioRG;
        try {
            fioRG = ZalogRG.find(new Pattern(path("Zalog\\E_FIO")));

            fioRG = fioRG.right(100);
            fioRG.click();

            DataBase Base = new DataBase();
            Base.Connect();

            List<String[]> rs;// = new ArrayList<>();

            System.out.println("select first 1 ID, \"Fam\"||\" \"||\"Imja\"||\" \"||\"Otc\" from \"PrFizLicList\"(null, 451) order by \"Fam\",\"Imja\",\"Otc\"");
            rs = Base.Query("select first 1 ID, \"Fam\"||\' \'||\"Imja\"||\' \'||\"Otc\" from \"PrFizLicList\"(null, 451) order by \"Fam\",\"Imja\",\"Otc\"");
            Base.Closed();
            //Common с = new Common();
            fioRG.type(Common.toEnglish(rs.get(0)[1]));
            fioRG.type(Key.ENTER);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void SelectFIO(String like) {
        Region fioRG;
        try {
            fioRG = ZalogRG.find(new Pattern(path("Zalog\\E_FIO")));

            fioRG = fioRG.right(100);
            fioRG.click();

            DataBase Base = new DataBase();
            Base.Connect();

            List<String[]> rs;// = new ArrayList<>();

            rs = Base.Query("select first 1 ID, \"Fam\"||\' \'||\"Imja\"||\' \'||\"Otc\" from \"PrFizLicList\"(null, 451) where \"Fam\" like '"+like+"%' order by \"Fam\",\"Imja\",\"Otc\"");
            Base.Closed();
            fioRG.type(Common.toEnglish(rs.get(0)[1]));
            fioRG.type(Key.ENTER);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void SetKolPeriod(int n){
        Region KolPeriod;
        int i;

        try {
            KolPeriod = ZalogRG.find(new Pattern(path("Zalog\\E_KolPeriod")));
            i = KolPeriod.getW()/2+1;

            KolPeriod.setX(KolPeriod.getX() + i);
            KolPeriod.setW(i);
            //переприсваиваем новый регион, без этого клик срабатывает по LastMatch - старые, не изменный регион.
            KolPeriod = new Region(KolPeriod);

            KolPeriod.click(KolPeriod);

            boolean on;
            //выкл/вкл NumLock так как коммбинация SHIFT +HOME не работает с включенным NumLock
            on = (Key.isLockOn('\ue03b'));
            if(on)
                KolPeriod.type(Key.NUM_LOCK);

            KolPeriod.type(Key.HOME, Key.SHIFT);
            KolPeriod.type(Key.BACKSPACE);
            KolPeriod.type(String.valueOf(n));

            if(on)
                KolPeriod.type(Key.NUM_LOCK);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void SetDateReturn(String StrDateReturn)  {
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

    public void ClickAddMainoButton() {
        try {
            ZalogRG.find(new Pattern(path("Zalog\\B_Add"))).click();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void SelectGroupMaino(String str) {
        Region Group = null;
        try {

            if (str.equals("Техника"))
                Group = getDriver().find(new Pattern(path("GroupMaino\\Technics")));
            if (str == null || str.equals("Драг.Металл"))
                Group = getDriver().find(new Pattern(path("GroupMaino\\DragMetall")));
            Group.click();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void SelectGroupMaino() {
        Region Group = null;
        try {
            Group = getDriver().find(new Pattern(path("GroupMaino\\DragMetall")));
            Group.doubleClick();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void SelectMaino() {

    }
}
