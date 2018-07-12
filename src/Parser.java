import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Parser {

    public void run(String inputData) {
        parseString(inputData);
    }

    private void parseString(String inputData) {
        List<String> commandList = new ArrayList<>(Arrays.asList(inputData.split("\n")));
        commandList.remove(0);
        List<DataLine> listDataLine = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Executor executor = new Executor();

        for (String rawCommand: commandList) {
            String[] temp = rawCommand.split(" ");
            try {
                if (temp[0].equals("C")) {
                    listDataLine.add(new DataLine(
                            temp[1],
                            temp[2],
                            String.valueOf(temp[3]),
                            formatter.parse(temp[4]),
                            Integer.parseInt(temp[5])));
                } else {
                    Query query;
                    if (temp[4].length() > 10) {
                        String[] tempDate = temp[4].split("-");
                        query = new Query(
                                temp[1],
                                temp[2],
                                String.valueOf(temp[3]),
                                formatter.parse(tempDate[0]),
                                formatter.parse(tempDate[1]));
                    } else {
                        query = new Query(
                                temp[1],
                                temp[2],
                                String.valueOf(temp[3]),
                                formatter.parse(temp[4]),
                                formatter.parse(temp[4]));
                    }
                    if (!listDataLine.isEmpty())
                        executor.compare(listDataLine, query);
                    else System.out.println("-");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}