public class HelloNumbers {
    public static void main (String[] args) {
        int x = 0;
        for (int i = 0; i < 10; i += 1) {
            x = x + i;
            System.out.print(x + " "); // it's different from println - not change the line.
        }
    }
}