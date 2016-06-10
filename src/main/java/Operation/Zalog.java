package Operation;

import Common.Driver;

import org.sikuli.script.*;

import java.awt.event.KeyEvent;
import java.net.URISyntaxException;

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

            KolPeriod = new Region(KolPeriod);

            KolPeriod.click(KolPeriod);

            boolean on;
            //on = Key.isModifier(Key.NUM_LOCK);
            on = Key.isLockOn('\ue03b');
            System.out.println("sds = " + on);
            System.out.println("sds111111111 = " + '\ue03b');
            KolPeriod.type(Key.NUM_LOCK);

            on = Key.isModifier(Key.NUM_LOCK);
            System.out.println("sds = " + on);


            KolPeriod.type(Key.HOME, Key.SHIFT);
            KolPeriod.type(Key.BACKSPACE);
            KolPeriod.type(String.valueOf(n));
            KolPeriod.type(Key.NUM_LOCK);


            /*KolPeriod.keyDown(Key.SHIFT);

            KolPeriod.keyDown(Key.HOME);

            KolPeriod.keyUp(Key.HOME);



            KolPeriod.type(Key.BACKSPACE);
            KolPeriod.keyUp();

            */
            //(Key.SHIFT+Key.HOME);
            //KolPeriod.type(Key.BACKSPACE);
           // KolPeriod.type(String.valueOf(n));
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }



}
