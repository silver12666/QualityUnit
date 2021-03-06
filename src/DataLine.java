import java.util.Date;

public class DataLine extends Line {

    private Date date;
    private int waitingTime;

    DataLine(String serviceVariation, String typeCategorySubcategory, String responseType, Date date, int waitingTime) {
        super(serviceVariation, typeCategorySubcategory, responseType);
        this.date = date;
        this.waitingTime = waitingTime;
    }

    public Date getDate() {
        return date;
    }

    public int getWaitingTime() {
        return waitingTime;
    }
}
