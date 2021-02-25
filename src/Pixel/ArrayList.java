/**
 * @param Създаване на Арреи лист
 * @author Antoan
 */
package Pixel;
public class ArrayList<T>{
    private int placeholderPointer=0;
    private final Object[] collection;
    public ArrayList(){
        this.collection = new Object[6];
    }
    public void add(String str){
        this.collection[placeholderPointer++] = str;
    }
    public void display(){
        for (Object i : collection) {
            if (i != null) {
                System.out.println(i);
            }
        }
    }
}