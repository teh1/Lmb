package Operation;

import Common.Driver;

import org.sikuli.script.*;
import org.sikuli.script.Observer;

import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Zalog extends Driver{
    //private final float SEC = 0.5f;

    private Region ZalogRG;
    private Pattern ZalogPT;

    public Zalog() throws FindFailed, URISyntaxException {
        //username = new Pattern(path("ShortCutBar"));
        //password = new Pattern(DataProperties.path("loginPassword.png"));
        //loginButton = new Pattern(DataProperties.path("loginSubmiButton.png"));
        System.out.println("tesdsfdsfsdfsdfsdfsdfsdfsdfsdfsft");
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

    public void SetKolPeriod(int n){
        Region KolPeriod = null;
        int i = 0;

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
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void SetDateReturn(String DateReturn)  {
        int n;
        if (DateReturn == null) DateReturn = "currentdate";
        n = 1; //WorkDate - DateReturn;

        String str = "15.01.2016";

        Calendar calendar = new GregorianCalendar(2013,1,28);



        System.out.println(calendar.get(calendar.DATE));
        System.out.println(calendar.get(calendar.YEAR));
        System.out.println(calendar.get(calendar.MONTH));


        SimpleDateFormat sdf = new SimpleDateFormat("dd.MMM.yyyy"); //, Locale.JAPAN
        System.out.println(sdf.format(calendar.getTime()));

        //Calendar calendar1 = new GregorianCalendar(2016,4,30);
        //Calendar calendar2 = new GregorianCalendar(2016,5,1);
        /*calendar1.set(2016,2, 28);
        calendar2.set(2016,3, 1);
*/
  /*      long milliseconds1 = calendar1.getTimeInMillis();
        long milliseconds2 = calendar2.getTimeInMillis();
        long diff = milliseconds2 - milliseconds1;
        long diffSeconds = diff / 1000;
        long diffMinutes = diff / (60 * 1000);
        long diffHours = diff / (60 * 60 * 1000);
        long diffDays = diff / (24 * 60 * 60 * 1000);

        System.out.println("asdasdasd = " + diffDays);

*/
       /* Calendar calendar1 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        calendar1.set(2016,2, 28);
        calendar2.set(2016,3, 1);

        Calendar date = (Calendar) calendar1.clone();
        long daysBetween = 0;
        while (date.before(calendar2)) {
            date.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        System.out.println(" qwqw = "+daysBetween);*/

        final Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.setTimeInMillis(0);
        System.out.println(cal.getTimeZone());

        cal.set(Calendar.YEAR, 2016);
        cal.set(Calendar.MONTH, 2);
        cal.set(Calendar.DAY_OF_MONTH, 29);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        final Date startDate = cal.getTime();

        cal.set(Calendar.YEAR, 2016);
        cal.set(Calendar.MONTH, 3);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        final Date endDate = cal.getTime();

        System.out.println((endDate.getTime() - startDate.getTime())/(1000*60*60*24) /* % (1000l * 60l * 60l * 24l)*/);






        // SetKolPeriod(n);

    }

    public void ClickAddMainoButton() {
        try {
            ZalogRG.find(new Pattern(path("Zalog\\B_Add"))).click();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
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
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public void SelectGroupMaino() {
        Region Group = null;
        try {
            Group = getDriver().find(new Pattern(path("GroupMaino\\DragMetall")));
            Group.doubleClick();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void SelectMaino() {

    }
}
