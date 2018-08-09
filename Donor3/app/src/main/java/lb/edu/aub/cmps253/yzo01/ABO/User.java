package lb.edu.aub.cmps253.yzo01.ABO;

public class User {
    private String UID;
    private String fullName;
    private String livingPlace;
    private String gender;
    private String bloodType;

    private String DOB;

    public User() { }

    public User(String UID, User user){
        this(UID, user.bloodType, user.DOB,  user.fullName, user.gender, user.livingPlace);
    }

    public User(String UID, String bloodType, String DOB, String fullName, String gender, String livingPlace) {
        this.UID = UID;
        this.bloodType = bloodType;
        this.DOB = DOB;
        this.fullName = fullName;
        this.gender = gender;
        this.livingPlace = livingPlace;
    }

    public String getUID(){
        return UID;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLivingPlace() {
        return livingPlace;
    }

    public void setLivingPlace(String livingPlace) {
        this.livingPlace = livingPlace;
    }

    @Override
    public String toString()
    {
        return UID+": " + fullName;
    }
}
