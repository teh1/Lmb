package Common;

import javafx.scene.input.KeyEvent;
import org.sikuli.script.Key;

public class MyKey extends Key {

    public static int[] toJavaKeyCode(char key) {
        switch (key) {
//Lowercase
            case 'ф':
            case 'Ф':
            case 'a': return new int[]{java.awt.event.KeyEvent.VK_A};
            case 'b': return new int[]{java.awt.event.KeyEvent.VK_B};
            case 'c': return new int[]{java.awt.event.KeyEvent.VK_C};
            case 'd': return new int[]{java.awt.event.KeyEvent.VK_D};
            case 'e': return new int[]{java.awt.event.KeyEvent.VK_E};
            case 'f': return new int[]{java.awt.event.KeyEvent.VK_F};
            case 'g': return new int[]{java.awt.event.KeyEvent.VK_G};
            case 'h': return new int[]{java.awt.event.KeyEvent.VK_H};
            case 'i': return new int[]{java.awt.event.KeyEvent.VK_I};
            case 'j': return new int[]{java.awt.event.KeyEvent.VK_J};
            case 'k': return new int[]{java.awt.event.KeyEvent.VK_K};
            case 'l': return new int[]{java.awt.event.KeyEvent.VK_L};
            case 'm': return new int[]{java.awt.event.KeyEvent.VK_M};
            case 'n': return new int[]{java.awt.event.KeyEvent.VK_N};
            case 'o': return new int[]{java.awt.event.KeyEvent.VK_O};
            case 'p': return new int[]{java.awt.event.KeyEvent.VK_P};
            case 'q': return new int[]{java.awt.event.KeyEvent.VK_Q};
            case 'r': return new int[]{java.awt.event.KeyEvent.VK_R};
            case 's': return new int[]{java.awt.event.KeyEvent.VK_S};
            case 't': return new int[]{java.awt.event.KeyEvent.VK_T};
            case 'u': return new int[]{java.awt.event.KeyEvent.VK_U};
            case 'v': return new int[]{java.awt.event.KeyEvent.VK_V};
            case 'w': return new int[]{java.awt.event.KeyEvent.VK_W};
            case 'x': return new int[]{java.awt.event.KeyEvent.VK_X};
            case 'y': return new int[]{java.awt.event.KeyEvent.VK_Y};
            case 'z': return new int[]{java.awt.event.KeyEvent.VK_Z};
//Uppercase
            case 'A': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_A};
            case 'B': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_B};
            case 'C': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_C};
            case 'D': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_D};
            case 'E': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_E};
            case 'F': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_F};
            case 'G': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_G};
            case 'H': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_H};
            case 'I': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_I};
            case 'J': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_J};
            case 'K': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_K};
            case 'L': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_L};
            case 'M': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_M};
            case 'N': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_N};
            case 'O': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_O};
            case 'P': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_P};
            case 'Q': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_Q};
            case 'R': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_R};
            case 'S': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_S};
            case 'T': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_T};
            case 'U': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_U};
            case 'V': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_V};
            case 'W': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_W};
            case 'X': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_X};
            case 'Y': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_Y};
            case 'Z': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_Z};
//Row 3 (below function keys)
//      case '§': return new int[]{192}; //not producable
            case '1': return new int[]{java.awt.event.KeyEvent.VK_1};
            case '!': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_1};
            case '2': return new int[]{java.awt.event.KeyEvent.VK_2};
            case '@': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_2};
            case '3': return new int[]{java.awt.event.KeyEvent.VK_3};
            case '#': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_3};
            case '4': return new int[]{java.awt.event.KeyEvent.VK_4};
            case '$': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_4};
            case '5': return new int[]{java.awt.event.KeyEvent.VK_5};
            case '%': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_5};
            case '6': return new int[]{java.awt.event.KeyEvent.VK_6};
            case '^': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_6};
            case '7': return new int[]{java.awt.event.KeyEvent.VK_7};
            case '&': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_7};
            case '8': return new int[]{java.awt.event.KeyEvent.VK_8};
            case '*': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_8};
            case '9': return new int[]{java.awt.event.KeyEvent.VK_9};
            case '(': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_9};
            case '0': return new int[]{java.awt.event.KeyEvent.VK_0};
            case ')': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_0};
            case '-': return new int[]{java.awt.event.KeyEvent.VK_MINUS};
            case '_': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_MINUS};
            case '=': return new int[]{java.awt.event.KeyEvent.VK_EQUALS};
            case '+': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_EQUALS};
//Row 2
// q w e r t y u i o p
            case '[': return new int[]{java.awt.event.KeyEvent.VK_OPEN_BRACKET};
            case '{': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_OPEN_BRACKET};
            case ']': return new int[]{java.awt.event.KeyEvent.VK_CLOSE_BRACKET};
            case '}': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_CLOSE_BRACKET};
//Row 1
// a s d f g h j k l
            case ';': return new int[]{java.awt.event.KeyEvent.VK_SEMICOLON};
            case ':': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_SEMICOLON};
            case '\'': return new int[]{java.awt.event.KeyEvent.VK_QUOTE};
            case '"': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_QUOTE};
            case '\\': return new int[]{java.awt.event.KeyEvent.VK_BACK_SLASH};
            case '|': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_BACK_SLASH};
//RETURN, BACKSPACE, TAB
            case '\b': return new int[]{java.awt.event.KeyEvent.VK_BACK_SPACE};
            case '\t': return new int[]{java.awt.event.KeyEvent.VK_TAB};
            case '\r': return new int[]{java.awt.event.KeyEvent.VK_ENTER};
            case '\n': return new int[]{java.awt.event.KeyEvent.VK_ENTER};
//SPACE
            case ' ': return new int[]{java.awt.event.KeyEvent.VK_SPACE};
//Row 0 (first above SPACE)
            case '`': return new int[]{java.awt.event.KeyEvent.VK_BACK_QUOTE};
            case '~': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_BACK_QUOTE};
// z x c v b n m
            case ',': return new int[]{java.awt.event.KeyEvent.VK_COMMA};
            case '<': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_COMMA};
            case '.': return new int[]{java.awt.event.KeyEvent.VK_PERIOD};
            case '>': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_PERIOD};
            case '/': return new int[]{java.awt.event.KeyEvent.VK_SLASH};
            case '?': return new int[]{java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_SLASH};
//Modifier
            case Key.C_SHIFT: return new int[]{java.awt.event.KeyEvent.VK_SHIFT};
            case Key.C_CTRL:  return new int[]{java.awt.event.KeyEvent.VK_CONTROL};
            case Key.C_ALT:   return new int[]{java.awt.event.KeyEvent.VK_ALT};
            case Key.C_META:  return new int[]{java.awt.event.KeyEvent.VK_META};
//Cursor movement
            case Key.C_UP:     return new int[]{java.awt.event.KeyEvent.VK_UP};
            case Key.C_RIGHT:     return new int[]{java.awt.event.KeyEvent.VK_RIGHT};
            case Key.C_DOWN:      return new int[]{java.awt.event.KeyEvent.VK_DOWN};
            case Key.C_LEFT:      return new int[]{java.awt.event.KeyEvent.VK_LEFT};
            case Key.C_PAGE_UP:   return new int[]{java.awt.event.KeyEvent.VK_PAGE_UP};
            case Key.C_PAGE_DOWN: return new int[]{java.awt.event.KeyEvent.VK_PAGE_DOWN};
            case Key.C_END:       return new int[]{java.awt.event.KeyEvent.VK_END};
            case Key.C_HOME:      return new int[]{java.awt.event.KeyEvent.VK_HOME};
            case Key.C_DELETE:    return new int[]{java.awt.event.KeyEvent.VK_DELETE};
//Function keys
            case Key.C_ESC: return new int[]{java.awt.event.KeyEvent.VK_ESCAPE};
            case Key.C_F1:  return new int[]{java.awt.event.KeyEvent.VK_F1};
            case Key.C_F2:  return new int[]{java.awt.event.KeyEvent.VK_F2};
            case Key.C_F3:  return new int[]{java.awt.event.KeyEvent.VK_F3};
            case Key.C_F4:  return new int[]{java.awt.event.KeyEvent.VK_F4};
            case Key.C_F5:  return new int[]{java.awt.event.KeyEvent.VK_F5};
            case Key.C_F6:  return new int[]{java.awt.event.KeyEvent.VK_F6};
            case Key.C_F7:  return new int[]{java.awt.event.KeyEvent.VK_F7};
            case Key.C_F8:  return new int[]{java.awt.event.KeyEvent.VK_F8};
            case Key.C_F9:  return new int[]{java.awt.event.KeyEvent.VK_F9};
            case Key.C_F10: return new int[]{java.awt.event.KeyEvent.VK_F10};
            case Key.C_F11: return new int[]{java.awt.event.KeyEvent.VK_F11};
            case Key.C_F12: return new int[]{java.awt.event.KeyEvent.VK_F12};
            case Key.C_F13: return new int[]{java.awt.event.KeyEvent.VK_F13};
            case Key.C_F14: return new int[]{java.awt.event.KeyEvent.VK_F14};
            case Key.C_F15: return new int[]{java.awt.event.KeyEvent.VK_F15};
//Toggling kezs
            case Key.C_SCROLL_LOCK: return new int[]{java.awt.event.KeyEvent.VK_SCROLL_LOCK};
            case Key.C_NUM_LOCK:    return new int[]{java.awt.event.KeyEvent.VK_NUM_LOCK};
            case Key.C_CAPS_LOCK:   return new int[]{java.awt.event.KeyEvent.VK_CAPS_LOCK};
            case Key.C_INSERT:      return new int[]{java.awt.event.KeyEvent.VK_INSERT};
//Windows special
            case Key.C_PAUSE:       return new int[]{java.awt.event.KeyEvent.VK_PAUSE};
            case Key.C_PRINTSCREEN: return new int[]{java.awt.event.KeyEvent.VK_PRINTSCREEN};
//Num pad
            case Key.C_NUM0: return new int[]{java.awt.event.KeyEvent.VK_NUMPAD0};
            case Key.C_NUM1: return new int[]{java.awt.event.KeyEvent.VK_NUMPAD1};
            case Key.C_NUM2: return new int[]{java.awt.event.KeyEvent.VK_NUMPAD2};
            case Key.C_NUM3: return new int[]{java.awt.event.KeyEvent.VK_NUMPAD3};
            case Key.C_NUM4: return new int[]{java.awt.event.KeyEvent.VK_NUMPAD4};
            case Key.C_NUM5: return new int[]{java.awt.event.KeyEvent.VK_NUMPAD5};
            case Key.C_NUM6: return new int[]{java.awt.event.KeyEvent.VK_NUMPAD6};
            case Key.C_NUM7: return new int[]{java.awt.event.KeyEvent.VK_NUMPAD7};
            case Key.C_NUM8: return new int[]{java.awt.event.KeyEvent.VK_NUMPAD8};
            case Key.C_NUM9: return new int[]{java.awt.event.KeyEvent.VK_NUMPAD9};
//Num pad special
            case Key.C_SEPARATOR: return new int[]{java.awt.event.KeyEvent.VK_SEPARATOR};
            case Key.C_ADD:       return new int[]{java.awt.event.KeyEvent.VK_ADD};
            case Key.C_MINUS:     return new int[]{java.awt.event.KeyEvent.VK_SUBTRACT};
            case Key.C_MULTIPLY:  return new int[]{java.awt.event.KeyEvent.VK_MULTIPLY};
            case Key.C_DIVIDE:    return new int[]{java.awt.event.KeyEvent.VK_DIVIDE};
            case Key.C_DECIMAL:   return new int[]{java.awt.event.KeyEvent.VK_DECIMAL};
            case Key.C_CONTEXT:   return new int[]{java.awt.event.KeyEvent.VK_CONTEXT_MENU};
            case Key.C_WIN:   return new int[]{java.awt.event.KeyEvent.VK_WINDOWS};
//hack: alternative tab in GUI
            case Key.C_NEXT:   return new int[]{-java.awt.event.KeyEvent.VK_TAB};

            default:
                throw new IllegalArgumentException("Cannot convert character " + key);
        }
    }

}
