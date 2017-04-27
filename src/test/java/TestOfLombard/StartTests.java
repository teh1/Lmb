package TestOfLombard;

import Operation.Vykup;
import Operation.Zalog;
import Operation.Perezalog;
import Menu.ShortCutBar;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import Common.Common;
import Common.DataBase;



import org.sikuli.script.FindFailed;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StartTests extends  Zalog {
   // Zalog z;
   private List<Map<String, String>> listOfTests = new ArrayList<Map<String, String>>();
   //List<HashMap<String, Object>> records = new ArrayList<HashMap<String, Object>>();

    public StartTests() throws FindFailed, URISyntaxException {
    }

    @Before
    public void setUp() throws InterruptedException, FindFailed, URISyntaxException {
        System.out.println("Before test");

        Map<String,String> test = new HashMap<String, String>();
        //Map<String, Object> t;
        DataBase b = new DataBase();
        b.Connect();
        List<Map<String, String>> rs;
        rs = b.QueryMD(" select \"NumberTest\", \"Run\", \"FIO\", \"DateZalogOffset\", \"Algorithm\", \"Discount\", \"ZalogPeriod\", \"GroupMaino\", \"IsPerezalog\",\n" +
                "       \"DatePerezalogOffset\", \"PerezalogPeriod\", \"IsVykup\", \"DateVykupOffset\"\n" +
                "       from \"TestsList\"\n" +
                "       where \"Run\" = 1");
        b.Closed();
        Iterator<Map<String, String>> iter = rs.iterator();
        Map<String, String> t;
        while (iter.hasNext()) {

            t = iter.next();

            test.put("NumberTest",t.get("NumberTest")); //номер теста
            test.put("Run",t.get("Run"));               //1-запускать тест, 0 - не запускать тест
            test.put("FIO",t.get("FIO"));               //ФИО клиента, Путота - рандомный выбор из базы
            test.put("DateZalogOffset",t.get("DateZalogOffset")); //дата залога, указывается смещении в днях относиельно текущей даты
            test.put("Algorithm",t.get("Algorithm"));             //Алгоритм залога
            test.put("DiscountCardNumber",t.get("Discount"));     //Диск.карточка
            test.put("CountPeriodZalog",t.get("ZalogPeriod"));    //количество дней(периодов)залога
            test.put("GroupMaino",t.get("GroupMaino"));
            test.put("СreatePerezalog",t.get("IsPerezalog"));     //0 - перезалог не создается
            test.put("DatePerezalogOffset",t.get("DatePerezalogOffset")); //дата перезалога, указывается смещении в днях относиельно текущей даты >=0
            test.put("CountPeriodPerezalog",t.get("PerezalogPeriod"));
            test.put("СreateVykup",t.get("IsVykup"));             //0-выкуп не создается, 1 - выкуп создается
            test.put("DateVykupOffset",t.get("DateVykupOffset"));

            if (test.get("Run").equals("0")) continue;
            listOfTests.add(new HashMap<String, String>(test)); //Добавление в список тестов
        }
    }

    @Test
    public void CreateZalog() throws FindFailed, URISyntaxException {
        System.out.println("test");
        Common Common = new Common();
        ShortCutBar bar;
        bar = new ShortCutBar();

        Calendar tmpDate;
        tmpDate = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        //Запуск всех тестов в списке
        //Запуск в цикле
       for (Map<String, String> t : listOfTests) {
           if (t.get("Run").equals("0")) continue;

           tmpDate = Calendar.getInstance();
           tmpDate.add(Calendar.DATE, Integer.valueOf(t.get("DateZalogOffset"))); //вычисляем дату залога
           Common.ChangeWorkDate(dateFormat.format(tmpDate.getTime()));

            bar.ChoiceZalog();

            Zalog Z = new Zalog();
            Z.Init();

            Z.SelectFIO();
            Z.SelectAlgorithmKredit(t.get("Algorithm"));
            Z.SetDiscountCardNumber(t.get("DiscountCardNumber"));
            Z.setCountPeriod(Integer.valueOf(t.get("CountPeriodZalog")));
            Z.AddMaino();
            Z.SelectGroupMaino(t.get("GroupMaino"));
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
            //Assert.assertEquals(85.68,Z.getSummPercent(),0.00001);
            Z.saveZalog();
            Z.closeZalog();

            Z.saveIDZalogToBaseFactResult(Integer.parseInt(t.get("NumberTest")));


           //Z.getSummPercentDB(); проценты из базы

           //region Продление
           if (!t.get("СreatePerezalog").equals("0")){
               tmpDate = Calendar.getInstance();
               tmpDate.add(Calendar.DATE, Integer.valueOf(t.get("DatePerezalogOffset"))); //вычисляем дату продлениея
               Common.ChangeWorkDate(dateFormat.format(tmpDate.getTime()));

               bar.ChoicePerezalog();

               Perezalog P = new Perezalog();

               P.Open(Z.getNumberZalog());
               //P.Open("ам 65846");
               P.Init();
               //P.setDateReturn("23.11.2016");
               P.setCountPeriods(Integer.valueOf(t.get("CountPeriodPerezalog")));
               P.savePerezalog();
               P.saveIDPerezalogToBaseFactResult(Integer.parseInt(t.get("NumberTest")));
               P.closePerezalog();

               System.out.println("qqqq="+P.getSumPersentForUseDB());

           }
           //endregion
           //region Выкуп

          if(!t.get("СreateVykup").equals("0")) {
              tmpDate = Calendar.getInstance();
              tmpDate.add(Calendar.DATE, Integer.valueOf(t.get("DateVykupOffset"))); //вычисляем дату выкупа
              Common.ChangeWorkDate(dateFormat.format(tmpDate.getTime()));
              bar.ChoiceVykup();

              Vykup V = new Vykup();
              V.Open(Z.getNumberZalog());
              V.Init();
              V.saveVykup();
              V.closeVykup();
              V.saveIDVykupToBaseFactResult(Integer.parseInt(t.get("NumberTest")));

           }
           //endregion Выкуп
        }

/*
        Common.ChangeWorkDate("01.03.2016");

        bar.ChoicePerezalog();

        Perezalog P = new Perezalog();

        P.Open(Z.getNumberZalog());
        //P.Open("ам 65846");
        P.Init();
        //P.setDateReturn("23.11.2016");
        P.setCountPeriods(11);
        P.savePerezalog();
        P.closePerezalog();
    */
       // MainMenu menu = new MainMenu();
        //menu.mSessia().click();
        //menu.subSessia(0).click();
    }

    @After
    public void after(){
        Calendar tmpDate;
        Common Common = new Common();

        tmpDate = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        Common.ChangeWorkDate(dateFormat.format(tmpDate.getTime()));

    }
}
