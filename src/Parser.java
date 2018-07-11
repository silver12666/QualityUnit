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
                        compare(listDataLine, query);
                    else System.out.println("-");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void compare(List<DataLine> listDataLine, Query query) {
        int divider = 0;
        int waitingTime = 0;

        for (DataLine dataLine : listDataLine) {

            if (compareServices(dataLine, query) &&
                    compareTypeCategory(dataLine, query) &&
                    query.getResponseType().equals(dataLine.getResponseType()) &&
                    includeDate(dataLine, query)) {
                waitingTime += dataLine.getWaitingTime();
                divider++;
            }
        }
        if (divider == 0)
            System.out.println("-");
        else System.out.println(waitingTime/divider);
    }

    private boolean compareServices(DataLine dataLine, Query query) {

        if (query.getServiceID() == 0)
            return true;
        else if (query.getServiceID() == dataLine.getServiceID()) {
            int queryVariation = query.getVariationID();
            int dataLineVariation = dataLine.getVariationID();
            return (queryVariation == 0 || dataLineVariation == 0 || queryVariation == dataLineVariation);
        }
        return false;
    }

    private boolean compareTypeCategory(DataLine dataLine, Query query) {
        if (query.getTypeID() == 0)
            return true;

        else if (query.getTypeID() == dataLine.getTypeID()) {
            int queryCategory = query.getCategoryID();
            int dataLineCategory = dataLine.getCategoryID();

            if (queryCategory == 0 || dataLineCategory == 0 || queryCategory == dataLineCategory) {
                int querySubcategory = query.getSubcategoryID();
                int dataLineSubcategory = dataLine.getSubcategoryID();
                return (querySubcategory == 0 || dataLineSubcategory == 0 || querySubcategory == dataLineSubcategory);
            }
        }
        return false;
    }

    private boolean includeDate(DataLine dataLine, Query query) {
        Date queryStartDate = query.getStartDate();
        Date queryEndDate = query.getEndDate();
        Date dataLineDate = dataLine.getDate();
        return (queryStartDate.before(dataLineDate) && queryEndDate.after(dataLineDate)) || dataLineDate.equals(queryStartDate);
    }
}