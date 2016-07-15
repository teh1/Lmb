package TestCreateZalog;

import Operation.Zalog;
import Menu.ShortCutBar;
import org.junit.Assert;
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
        // Z.SetDateReturn("20.06.2016");

        //Z.SelectFIO("Бала");
        Z.SelectFIO();
        Z.SelectAlgorithmKredit("Класичний");
        Z.SetKolPeriod(10);
        Z.AddMaino();
        Z.SelectGroupMaino("Драг.Металл");
        Z.SelectMaino("Запонки");
        Z.SelectMaterial("золото");
        Z.SelectProba("583","");
        Z.SetAmount(1);
        Z.SetWeight(1.2);


        Z.AddMaino();
        Z.SelectMaino("Брошка");
        Z.SelectMaterial("золото");
        Z.SelectProba("583","");
        Z.SetAmount(2);
        Z.SetWeight(1.3);
        Assert.assertEquals(104.00,Z.GetSummPercent(),0.00001);



//        Z.CloseZalog();




      //  Z.test();
      //  Z.stop();

    /*

        Z.SetKolPeriod(32);
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
