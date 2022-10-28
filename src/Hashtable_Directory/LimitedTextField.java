package Hashtable_Directory;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitedTextField extends PlainDocument {
    private int limit;  //限制的长度

    public LimitedTextField() {
        super(); //调用父类构造
        this.limit = 15;
    }
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if(str == null) return;
        if((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);//调用父类方法
        }
    }
}