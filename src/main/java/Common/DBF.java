package Common;
//Сторонии библиотке для чтения дбф
import net.iryndin.jdbf.core.DbfRecord;
import net.iryndin.jdbf.reader.DbfReader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BalvasA on 16.02.2017.
 */

//https://github.com/iryndin/jdbf - работа с дбф
public class DBF {

    public List<Map<String, String>> listOfTests = new ArrayList<Map<String, String>>();
    public void ReadTestsFromDbf(){
        InputStream dbf;
        Map<String,String> test = new HashMap<String, String>();
        Map<String, Object> t;
        //Map<String, Object> t = new  LinkedHashMap<String, Object>();

        Charset stringCharset = Charset.forName("Cp1251");

        //dbf = new FileInputStream("d:\\Base\\Apostolovo\\TestLombard.dbf");
        //https://github.com/iryndin/jdbf - работа с дбф
        dbf = getClass().getClassLoader().getResourceAsStream("TestsDBF/TestsLombard.dbf");
        DbfRecord rec;
        try (DbfReader reader = new DbfReader(dbf)) {
            //DbfMetadata meta = reader.getMetadata();
            //System.out.println("Read DBF Metadata: " + meta);

            //region Описание тестов/Параметры тестов, операций залога
            while ((rec = reader.read()) != null) {
                rec.setStringCharset(stringCharset);
                t = rec.toMap();

                test.put("NumberTest",String.valueOf(t.get("NUMBERTEST"))); //номер теста
                test.put("Run",String.valueOf(t.get("RUN")));        //1-запускать тест, 0 - не запускать тест
                test.put("FIO",String.valueOf(t.get("FIO")));        //ФИО клиента, Путота - рандомный выбор из базы
                test.put("DateZalogOffset",String.valueOf(t.get("DATEZALOG"))); //дата залога, указывается смещении в днях относиельно текущей даты
                test.put("Algorithm",String.valueOf(t.get("ALGORITHM"))); //Алгоритм залога
                test.put("DiscountCardNumber",String.valueOf(t.get("DISCOUNT"))); //Диск.карточка
                test.put("CountPeriodZalog",String.valueOf(t.get("Z_PERIOD")));         //количество дней(периодов)залога
                test.put("GroupMaino",String.valueOf(t.get("GROUPMAINO")));
                test.put("СreatePerezalog",String.valueOf(t.get("PEREZALOG"))); //0 - перезалог не создается
                test.put("DatePerezalogOffset",String.valueOf(t.get("DATEPEREZ"))); //дата перезалога, указывается смещении в днях относиельно текущей даты >=0
                test.put("CountPeriodPerezalog",String.valueOf(t.get("P_PERIOD")));
                test.put("СreateVykup",String.valueOf(t.get("VYKUP"))); //0-выкуп не создается, 1 - выкуп создается
                test.put("DateVykupOffset",String.valueOf(t.get("DATEVYKUP")));
                //test.put("Comment",String.valueOf(t.get("COMMENT")));
                if (test.get("Run").equals("0")) continue;
                listOfTests.add(new HashMap<String, String>(test)); //Добавление в список тестов
                //System.out.println("Record #" + rec.getRecordNumber() + ": " + rec.toMap());
            }
            //endregion Описание тестов/Параметры тестов, операций залога
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
