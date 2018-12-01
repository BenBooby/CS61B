
public class ArrayDeque <T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    private static final int INITIAL_CAPACITY = 8;
    private static final int RFACTOR = 2;

    public ArrayDeque(){
        items = (T[]) new Object[INITIAL_CAPACITY];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    private void calculateFront(){
        nextFirst = (nextFirst + items.length - 1) % (items.length);
    }

    private void calculateLast(){
        nextLast = (nextLast + 1) % items.length;
    }

    private int plusOne(int index){
        return (index + 1) % items.length;
    }

    private int minusOne(int index){
        return (index + items.length -1) % items.length;
    }

    private boolean isFull(){
        return items.length == size;
    }

    private void extend(){
        reSize(size, RFACTOR * size);
    }

    private void shrink(){
        reSize(items.length, items.length / RFACTOR);
    }

    private boolean checkLarger(int nextFirst, int nextLast){
        return nextFirst > nextLast;
    }

    private void reSize(int size, int capacity){
        T[] a = (T[]) new Object[capacity];

        if (capacity < size){
            int start = Math.min(nextFirst, nextLast);
            for (int i = 0; i < capacity; i++) {
                a[i] = items[start];
                start ++;
            }
            if (checkLarger(nextFirst,nextLast)){
                nextLast = 0;
                nextFirst = capacity / 2;
            }else {
                nextFirst = 0;
                nextLast = capacity / 2;
            }
        }else {
            
        }
        items = a;
        System.out.println();

    }

    public void addFirst(T item){
        if (isFull()){
            extend();
        }

        items[nextFirst] = item;
        calculateFront();
        size ++;
    }

    public void addLast(T item){
        if (isFull()){
            extend();
        }
        items[nextLast] = item;
        calculateLast();
        size ++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    private boolean isSparse(){
        return items.length >= 16 && size() < items.length / 4;
    }

    public T removeFirst(){
        if (isSparse()){
            shrink();
        }
        nextFirst = plusOne(nextFirst);
        T returnItem = items[nextFirst];
        items[nextFirst] = null;
        size --;
        return returnItem;
    }

    public T removeLast(){
        if (isSparse()){
            shrink();
        }
        nextLast = minusOne(nextLast);
        T returnItem = items[nextLast];
        items[nextLast] = null;
        size --;
        return returnItem;
    }


    public void printDeque() {
        for (int i = 0; i < items.length; i++) {
            T item = get(i);
            if (item == null){
                System.out.println();
                return;
            }
            System.out.print(item);
            System.out.print(" ");
        }
    }

    public T get(int index){
        return items[(nextFirst + 1 + index) % items.length ];
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst(1);
//        arrayDeque.addFirst(2);
//        arrayDeque.addFirst(3);
//        arrayDeque.addFirst(4);
//        arrayDeque.addFirst(5);
        arrayDeque.addLast(-1);
        arrayDeque.addLast(-2);
        arrayDeque.addLast(-3);
        arrayDeque.addLast(-4);
        arrayDeque.addLast(-5);
        arrayDeque.addLast(-6);
        arrayDeque.addLast(-7);
        arrayDeque.addLast(-8);
        arrayDeque.addLast(-9);
        arrayDeque.addLast(-10);
        arrayDeque.addLast(-11);
        arrayDeque.addLast(-12);
        arrayDeque.addLast(-13);
        arrayDeque.addLast(-14);
        arrayDeque.addLast(-15);

        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();


        arrayDeque.removeFirst();

        System.out.println();
    }



}
