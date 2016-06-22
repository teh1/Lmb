package Common;

import Menu.MainMenu;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

import java.io.CharConversionException;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Common extends Driver {

    public static Calendar WorkDate = new GregorianCalendar(2016,5,1);

    public static String toEnglish(String RusStr){
        String str = "";
        char c;
        for(int i=0; i<RusStr.length(); i++) {
            c = RusStr.charAt(i);
            switch (c) {
                case 'й': str = str + 'q'; break;
                case 'ц': str = str + 'w'; break;
                case 'у': str = str + 'e'; break;
                case 'к': str = str + 'r'; break;
                case 'е': str = str + 't'; break;
                case 'н': str = str + 'y'; break;
                case 'г': str = str + 'u'; break;
                case 'ш': str = str + 'i'; break;
                case 'щ': str = str + 'o'; break;
                case 'з': str = str + 'p'; break;
                case 'х': str = str + '['; break;
                case 'ї':
                case 'ъ': str = str + ']'; break;
                case 'ф': str = str + 'a'; break;
                case 'і': str = str + 's'; break;
                case 'ы': str = str + 's'; break;
                case 'в': str = str + 'd'; break;
                case 'а': str = str + 'f'; break;
                case 'п': str = str + 'g'; break;
                case 'р': str = str + 'h'; break;
                case 'о': str = str + 'j'; break;
                case 'л': str = str + 'k'; break;
                case 'д': str = str + 'l'; break;
                case 'ж': str = str + ';'; break;
                case 'є':
                case 'э': str = str + '\''; break;
                case 'я': str = str + 'z'; break;
                case 'ч': str = str + 'x'; break;
                case 'с': str = str + 'c'; break;
                case 'м': str = str + 'v'; break;
                case 'и': str = str + 'b'; break;
                case 'т': str = str + 'n'; break;
                case 'ь': str = str + 'm'; break;
                case 'б': str = str + ','; break;
                case 'ю': str = str + '.'; break;
                case '.': str = str + '/'; break;

                case 'Й': str = str + 'Q'; break;
                case 'Ц': str = str + 'W'; break;
                case 'У': str = str + 'E'; break;
                case 'К': str = str + 'R'; break;
                case 'Е': str = str + 'T'; break;
                case 'Н': str = str + 'Y'; break;
                case 'Г': str = str + 'U'; break;
                case 'Ш': str = str + 'I'; break;
                case 'Щ': str = str + 'O'; break;
                case 'З': str = str + 'P'; break;
                case 'Х': str = str + '['; break;
                case 'Ъ': str = str + ']'; break;
                case 'Ф': str = str + 'A'; break;
                case 'І': str = str + 'S'; break;
                case 'I': str = str + 'S'; break; // анг. I
                case 'Ы': str = str + 'S'; break;
                case 'В': str = str + 'D'; break;
                case 'А': str = str + 'F'; break;
                case 'П': str = str + 'G'; break;
                case 'Р': str = str + 'H'; break;
                case 'О': str = str + 'J'; break;
                case 'Л': str = str + 'K'; break;
                case 'Д': str = str + 'L'; break;
                case 'Ж': str = str + ';'; break;
                case 'Є':
                case 'Э': str = str + '\''; break;
                case 'Я': str = str + 'Z'; break;
                case 'Ч': str = str + 'X'; break;
                case 'С': str = str + 'C'; break;
                case 'М': str = str + 'V'; break;
                case 'И': str = str + 'B'; break;
                case 'Т': str = str + 'N'; break;
                case 'Ь': str = str + 'M'; break;
                case 'Б': str = str + '<'; break;
                case 'Ю': str = str + '.'; break;
              //  case '.': str = str + '/'; break;

                default: str = str + c; break;
            }
        }
        return str;
    }

    public void Login(){
        Region fLogin = null;
        try {
            fLogin = getDriver().wait((new Pattern(path("Login\\f_Login"))).similar((float) 0.8),4);
            fLogin.find(new Pattern(path("Login\\b_Enter"))).click();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
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
        }
    }

    public static void ClearEdit(Region Edit){
        boolean on;
        //выкл/вкл NumLock так как коммбинация SHIFT +HOME не работает с включенным NumLock
        on = (Key.isLockOn('\ue03b'));
        if(on)
            Edit.type(Key.NUM_LOCK);

        Edit.type(Key.HOME, Key.SHIFT);
        Edit.type(Key.BACKSPACE);

        if(on)
            Edit.type(Key.NUM_LOCK);
    }
}
