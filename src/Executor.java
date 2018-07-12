import java.util.Date;
import java.util.List;

public class Executor {

    void compare(List<DataLine> listDataLine, Query query) {
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
