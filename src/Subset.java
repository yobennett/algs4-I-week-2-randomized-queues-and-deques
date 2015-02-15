public class Subset {

    public static void main(String[] args) {

        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> q = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readString());
        }

        for (String s : q) {
            if (k <= 0) {
                break;
            }
            System.out.println(s);
            k -= 1;
        }

    }
}