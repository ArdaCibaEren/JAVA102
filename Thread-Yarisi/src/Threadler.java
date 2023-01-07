import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Threadler implements Runnable {
    private int firstNumber = 1;

    private final Object LOCK = new Object();
    private ArrayList<Integer> dividedList1 = new ArrayList<>();
    private ArrayList<Integer> dividedList2 = new ArrayList<>();
    private ArrayList<Integer> dividedList3 = new ArrayList<>();
    private ArrayList<Integer> dividedList4 = new ArrayList<>();
    private ArrayList<Integer> oddNumbers = new ArrayList<>();
    private ArrayList<Integer> evenNumbers = new ArrayList<>();

    @Override
    public void run() {
        synchronized (LOCK) {
            System.out.println(getFirstNumber());

            if (this.firstNumber <= 2500) {
                this.dividedList1.add(this.firstNumber);
                System.out.println(this.firstNumber + " " + " added to the Divided List 1");
            } else if (this.firstNumber > 2500 && this.firstNumber <= 5000) {
                this.dividedList2.add(this.firstNumber);
                System.out.println(this.firstNumber + " " + " added to the Divided List 2");
            } else if (this.firstNumber > 5000 && this.firstNumber <= 7500) {
                this.dividedList3.add(this.firstNumber);
                System.out.println(this.firstNumber + " " + " added to the Divided List 3");
            } else {
                this.dividedList4.add(this.firstNumber);
                System.out.println(this.firstNumber + " " + " added to the Divided List 4");
            }
            if (this.firstNumber % 2 == 0) {
                this.evenNumbers.add(this.firstNumber);
                System.out.println(this.firstNumber + " " + " added to the Even Numbers List");
            } else {
                this.oddNumbers.add(this.firstNumber);
                System.out.println(this.firstNumber + " " + " added to the Odd Numbers List");
            }
            this.firstNumber++;
        }
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public ArrayList<Integer> getDividedList1() {
        return dividedList1;
    }

    public ArrayList<Integer> getDividedList2() {
        return dividedList2;
    }

    public synchronized ArrayList<Integer> getDividedList3() {
        return dividedList3;
    }

    public synchronized ArrayList<Integer> getDividedList4() {
        return dividedList4;
    }

    public synchronized ArrayList<Integer> getOddNumbers() {
        return oddNumbers;
    }

    public synchronized ArrayList<Integer> getEvenNumbers() {
        return evenNumbers;
    }
}
//www.patika.dev