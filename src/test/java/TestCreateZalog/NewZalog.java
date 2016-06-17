package TestCreateZalog;

import Common.Common;
import Common.DataBase;
import Common.Driver;
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
import java.sql.SQLException;

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
        z.StartZalog();
        //z.SetDiscountCardNumber();

        // Common Common = new Common();
     //    System.out.println(Common.toEnglish("Андрієць Олена Василівна"));



       // z.SetDateReturn("20.06.2016");

         z.SetFIO("Бу");

  /*      DataBase Base = new DataBase();
        Base.Connect();
        String query = "select ID, \"Fam\", \"Imja\", \"Otc\" from \"PrFizLicList\"(null, 451) order by \"Fam\",\"Imja\",\"Otc\"";
        try {
            Base.Query(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
       /*

        z.SetKolPeriod(32);
        z.ClickAddMainoButton();
        z.SelectGroupMaino();
*/
       // MainMenu menu = new MainMenu();

        //menu.mSessia().click();
        //menu.subSessia(0).click();



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
