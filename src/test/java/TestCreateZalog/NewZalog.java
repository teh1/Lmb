package TestCreateZalog;

import Common.Common;
import Menu.MainMenu;
import Menu.ShortCutBar;
import Operation.Zalog;
import org.junit.Before;
import org.junit.Test;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

import java.net.URISyntaxException;

public class NewZalog  extends  Zalog {
   // Zalog z;

    public NewZalog() throws FindFailed, URISyntaxException {
    }

    @Before
    public void setUp() throws InterruptedException, FindFailed, URISyntaxException {
        System.out.println("lol");
        //ShortCutBar bar = new ShortCutBar();
        //bar.Zalog();

//		openSpotify();
      //          app = run();

    }
    //


    @Test
    public void CreateZalog() throws FindFailed, URISyntaxException {
        System.out.println("test");
        Zalog z = new Zalog();
        z.SetDateReturn("01.01.2000");
       /*z.StartZalog();

        z.SetKolPeriod(32);
        z.ClickAddMainoButton();
        z.SelectGroupMaino();
*/
       // MainMenu menu = new MainMenu();

        //menu.mSessia().click();
        //menu.subSessia(0).click();


       // Common Common = new Common();
        //Common.ChangeWorkDate("01.02.2016");

        //StartZalog();



        // SelectFizLic("Akulova");
       // StartAddProduct();
       // SelectGroup();
       // SelectMetall();
       // SelelectProba("500","r");
    //return;
    }


}
