package TestCreateZalog;

import Operation.Zalog;
import Menu.ShortCutBar;
import org.junit.Before;
import org.junit.Test;
import org.sikuli.script.FindFailed;

import java.net.URISyntaxException;

public class NewZalog  extends  Zalog {
   // Zalog z;

    public NewZalog() throws FindFailed, URISyntaxException {
    }

    @Before
    public void setUp() throws InterruptedException, FindFailed, URISyntaxException {
        System.out.println("lol");
        ShortCutBar bar;
        bar = new ShortCutBar();
        bar. ChoiceZalog();

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

      /*   z.SelectFIO("Бала");
        z.SelectAlgorithmKredit("Класичний");
        z.AddMaino();
        z.SelectGroupMaino("Драг.Металл");
        z.SelectMaino("Запонки");
        z.SelectMaterial("золото");
        z.SelectProba("583","");
        z.SetAmount(2);
        z.SetWeight(12.2);


        z.AddMaino();
        z.SelectMaino("Запонки");
        z.SelectMaterial("золото");
        z.SelectProba("583","");
        z.SetAmount(2);
        z.SetWeight(12.2);*/


      //  z.test();
      //  z.stop();




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
        z.AddMaino();
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
