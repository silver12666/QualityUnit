import java.util.Date;

public class Query extends Line {

    private Date startDate;
    private Date endDate;

    Query(String serviceVariation, String typeCategorySubcategory, String responseType, Date startDate, Date endDate) {
        super(serviceVariation, typeCategorySubcategory, responseType);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public int getServiceID() {
        if (getServiceVariationArray()[0].equals("*"))
            return 0;
        else
        return super.getServiceID();
    }

    @Override
    public int getVariationID() {
        if (getServiceVariationArray()[0].equals("*"))
            return 0;
        else
            return super.getVariationID();
    }

    @Override
    public int getTypeID() {
        if (getTypeCategorySubcategoryArray()[0].equals("*"))
            return 0;
        else
            return super.getTypeID();
    }

    @Override
    public int getCategoryID() {
        if (getTypeCategorySubcategoryArray()[0].equals("*"))
            return 0;
        else
            return super.getCategoryID();
    }

    @Override
    public int getSubcategoryID() {
        if (getTypeCategorySubcategoryArray()[0].equals("*"))
            return 0;
        else
            return super.getSubcategoryID();
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
