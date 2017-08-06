import org.jetbrains.annotations.Contract;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.UnsupportedEncodingException;


public class OneCBot {
    Robot robot;

    public OneCBot() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private int getRawCodeLetter(char ch) {
//      final char[] engLetters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ';', ',', '.', '`', '[', ']', ''' };
        final String engLetters = "abcdefghijklmnopqrstuvwxyz;,.`[]'";
//      final char[] rusLetters = { 'ф', 'и', 'с', 'в', 'у', 'а', 'п', 'р', 'ш', 'о', 'л', 'д', 'ь', 'т', 'щ', 'з', 'й', 'к', 'ы', 'е', 'г', 'м', 'ц', 'ч', 'н', 'я', 'ж', 'б', 'ю', 'ё', 'х', 'ъ', 'э' };
        final String rusLetters = "фисвуапршолдьтщзйкыегмцчняжбюёхъэ";
        final int[] codeLetters = {  65,  66,  67,  68,  69,  70,  71,  72,  73,  74,  75,  76,  77,  78,  79,  80,  81,  82,  83,  84,  85,  86,  87,  88,  89,  90,  59,  44,  46, 192,  91,  93, 222 };

        if (isEngLetter(Character.toLowerCase(ch))) {
            return codeLetters[engLetters.indexOf(Character.toLowerCase(ch))];
        }

        if (isRusLetter(Character.toLowerCase(ch))) {
            return codeLetters[rusLetters.indexOf(Character.toLowerCase(ch))];
        }

        return 0;
    }

    @Contract(pure = true)
    private boolean isRusLetter(int ch) {
        return (ch >= 1040) && (ch <= 1105);
    }

    @Contract(pure = true)
    private boolean isEngLetter(int ch) {
        return (ch >= 65) && (ch <= 122);
    }

    private void type(int i) {
        robot.delay(40);
        robot.keyPress(i);
        robot.keyRelease(i);
    }

    private void type(String s) {
        char[] chars = s.toCharArray();
        for (char b : chars) {
            int code = getRawCodeLetter(b);
            type(code);
//            // keycode only handles [A-Z] (which is ASCII decimal [65-90])
//            if (code > 96 && code < 123) code = code - 32;
////            robot.delay(40);
//            robot.delay(100);
//            System.out.print(code);
//            robot.keyPress(code);
//            robot.keyRelease(code);
        }
    }

    public void typeSomething(String string){
        string = string.toUpperCase();
        for(int i = 0; i < string.length(); i++){
            //press a key then release it
            robot.keyPress((int)(string.charAt(i)));
            robot.keyRelease((int)(string.charAt(i)));
            //delay
            robot.delay(200);
        }
    }

    public static void main(String[] args) {
        OneCBot bot = new OneCBot();
//        System.out.println(bot.getRawCodeLetter('['));
//        System.out.println(bot.getRawCodeLetter(']'));
//        System.out.println(bot.getRawCodeLetter('э'));
//        System.out.println(bot.getRawCodeLetter('ё'));
//        bot.type("Aa");
//        bot.type("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        bot.robot.delay(5000);
        bot.type("АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя");
    }
}
