package TestCreateZalog;

import Operation.Perezalog;
import Operation.Zalog;
import Menu.ShortCutBar;
import org.junit.Assert;
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
        bar.ChoiceZalog();

      //openSpotify();
      //app = run();
    }



    @Test
    public void CreateZalog() throws FindFailed, URISyntaxException {
        System.out.println("test");
        Zalog Z = new Zalog();
        Z.Init();
        //Z.SetDiscountCardNumber();
        //Common Common = new Common();
        //System.out.println(Common.toEnglish("Андрієць Олена Василівна"));
        // Z.setDateReturn("20.06.2016");

        //Z.SelectFIO("Бала");
        Z.SelectFIO();
        Z.SelectAlgorithmKredit("Месяц");
        Z.setkolperiod(10);
        Z.AddMaino();
        Z.SelectGroupMaino("Драг.Металл");
        Z.SelectMaino("Запонки");
        Z.SelectMaterial("золото");
        Z.SelectProba("583","");
        Z.setAmount(1);
        Z.setWeight(1.2);


        Z.AddMaino();
        Z.SelectMaino("Брошка");
        Z.SelectMaterial("золото");
        Z.SelectProba("583","");
        Z.setAmount(2);
        Z.setWeight(1.3);
       // Assert.assertEquals(85.68,Z.getSummPercent(),0.00001);

        Z.saveZalog();
        Z.closeZalog();

        ShortCutBar bar;
        bar = new ShortCutBar();
        bar.ChoicePerezalog();

        Perezalog P = new Perezalog();

        P.Open(Z.getNumberZalog());
        //P.Open("ам 65846");
        P.Init();
        P.setDateReturn("23.08.2016");


      //  Z.test();
      //  Z.stop();

    /*

        Z.setkolperiod(32);
        Z.AddMaino();
        Z.SelectGroupMaino();
*/
       // MainMenu menu = new MainMenu();

        //menu.mSessia().click();
        //menu.subSessia(0).click();


        //Common.ChangeWorkDate("01.02.2016");


    //return;
    }


}
