package Common;

import Menu.MainMenu;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

import java.net.URISyntaxException;

public class Common extends Driver {

    public void Login(){
        Region fLogin = null;
        try {
            fLogin = getDriver().wait((new Pattern(path("Login\\f_Login"))).similar((float) 0.8),4);
            fLogin.find(new Pattern(path("Login\\b_Enter"))).click();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    public void ChangeWorkDate(String newDate) {
        Region WorkDate;
        MainMenu menu = new MainMenu();
        menu.mSessia().click();
        menu.subSessia(0).click(); //меню Завершить сессию, 0-й пункт меню
        try {
            //подтверждение завершения сессии
            getDriver().wait((new Pattern(path("Common\\b_OK"))).similar((float) 0.8),5).click();

            menu.mSessia().click();
            menu.subSessia(1).click(); //меню Ввойти, 1-й пункт меню
            Login();
            //нажимаем пробел (кнопка Ок) для окно если версия программы отличается от Базы
            getDriver().type(Key.SPACE);
            //окно выбора рабочей даты
            WorkDate = getDriver().wait((new Pattern(path("Login\\f_WorkDate"))).similar((float) 0.8),5);
            //перевод курсора в начало ввода даты
            WorkDate.type(Key.HOME);
            //вставляем дату
            WorkDate.paste(newDate);
            WorkDate.type(Key.ENTER);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
