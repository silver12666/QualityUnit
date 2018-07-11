public abstract class Line {

    private String[] serviceVariationArray;
    private String[] typeCategorySubcategoryArray;
    private String responseType;

    Line(String serviceVariation, String typeCategorySubcategory, String responseType) {
        this.responseType = responseType;
        serviceVariationArray = serviceVariation.split("\\.");
        typeCategorySubcategoryArray = typeCategorySubcategory.split("\\.");
    }

    public int getServiceID() {
        return Integer.parseInt(serviceVariationArray[0]);
    }

    public int getVariationID() {
        if (serviceVariationArray.length > 1)
            return Integer.parseInt(serviceVariationArray[1]);
        else return 0;
    }

    public int getTypeID() {
        return Integer.parseInt(typeCategorySubcategoryArray[0]);
    }

    public int getCategoryID() {
        if (typeCategorySubcategoryArray.length > 1)
            return Integer.parseInt(typeCategorySubcategoryArray[1]);
        else return 0;
    }

    public int getSubcategoryID() {
        if (typeCategorySubcategoryArray.length > 2)
            return Integer.parseInt(typeCategorySubcategoryArray[2]);
        else return 0;
    }

    public String getResponseType() {
        return responseType;
    }

    String[] getServiceVariationArray() {
        return serviceVariationArray;
    }

    String[] getTypeCategorySubcategoryArray() {
        return typeCategorySubcategoryArray;
    }
}