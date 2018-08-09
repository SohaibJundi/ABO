package lb.edu.aub.cmps253.yzo01.ABO;

public class Request {

    protected String mobile;
    protected String center;
    protected String neededBloodType;
    protected String unitsNumber;

    public Request(String mobile, String center, String neededBloodType, String unitsNumber) {
        this.mobile = mobile;
        this.center = center;
        this.neededBloodType = neededBloodType;
        this.unitsNumber = unitsNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public String getCenter() {
        return center;
    }

    public String getNeededBloodType() {
        return neededBloodType;
    }

    public String getUnitsNumber() {
        return unitsNumber;
    }

    @Override
    public String toString() {
        return "Mobile: " + mobile + "Blood Type: " + neededBloodType + "Unit/s:" + unitsNumber + "\n" + center;
    }
}