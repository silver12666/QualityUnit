public class Main {
    public static void main(String[] args) {
        String string = "9\n" +
                "C 5.2 6.14.4 N 20.09.2012 1000\n" +
                "C 1.1 8.15.1 P 15.10.2012 83\n" +
                "C 1 10.1 P 01.12.2012 65\n" +
                "C 1.1 5.5.1 P 01.11.2012 117\n" +
                "D 1.1 8 P 01.01.2012-01.12.2012\n" +
                "C 3 10.2 N 02.10.2012 100\n" +
                "D 1 * P 8.10.2012-20.11.2012\n" +
                "D 3 10 P 01.12.2012\n" +
                "D 5 6.14 N 01.01.2012-03.11.2012";
        Parser parser = new Parser();
        parser.run(string);

    }
}
