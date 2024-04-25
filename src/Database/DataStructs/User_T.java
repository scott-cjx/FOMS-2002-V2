package Database.DataStructs;

import Main.SharedResources;

import java.util.UUID;

public class User_T implements IDatabaseItem_T {
    private UUID userUUID;
    private String username;
    private String password;
    private boolean isStaff;
    private StaffType staffType;
    private UUID branchUUID;
    private Branch_T branchT;
    private int gender;

    public void setUserUUID(UUID userUUID) {
        this.userUUID = userUUID;
    }

    public void setStaff(boolean staff) {
        isStaff = staff;
    }

    public UUID getBranchUUID() {
        return branchUUID;
    }

    public void setBranchUUID(UUID branchUUID) {
        this.branchUUID = branchUUID;
    }

    public Branch_T getBranchT() {
        return branchT;
    }

    public void setBranchT(Branch_T branchT) {
        this.branchT = branchT;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStaffType(StaffType staffType) {
        this.staffType = staffType;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public User_T() {}
    public User_T(String username) {
        this.username = username;
        this.password = "password";
    }

    public User_T(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User_T(UUID userId, String username, String password, boolean isStaff, StaffType staffType) {
        this.userUUID = userId;
        this.username = username;
        this.password = password;
        this.isStaff = isStaff;
        this.staffType = staffType;
    }

    public User_T(String username, String password, boolean isStaff, StaffType staffType) {
        this.userUUID = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.isStaff = isStaff;
        this.staffType = staffType;
    }

    public User_T(String username, String password, boolean isStaff, Branch_T branchT, StaffType staffType) {
        this.userUUID = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.isStaff = isStaff;
        this.branchT = branchT;
        this.staffType = staffType;
    }

    @Override
    public boolean addMeToDB() {
        return SharedResources.getUserDatabaseHelper().addToDatabase(this);
    }

    public boolean isInDB() {
        return SharedResources.getUserDatabaseHelper().isInDatabase(this);
    }

    public String prettyPrint() {
        return "User_T{" +
                ", username='" + username + '\'' +
                ", staffType=" + staffType +
                ", branchT=" + branchT +
                '}';
    }

    public UUID getUserUUID() {
        return userUUID;
    }

    public String getUsername() {
        return username;
    }

    public boolean isStaff() {
        return isStaff;
    }

    public StaffType getStaffType() {
        return staffType;
    }

    public String getPassword() {
        return password;
    }

    public boolean verifyPassword(User_T userPartial) {
        return this.password.equals(userPartial.getPassword());
    }

    public void printUser() {
        System.out.println(">> " + this.getUsername() + ", " + this.getStaffType());
    }

    @Override
    public String toString() {
        return "User_T{" +
                "userUUID=" + userUUID +
                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
                ", password=" + UUID.nameUUIDFromBytes(password.getBytes()) +
//                ", isStaff=" + isStaff +
                ", staffType=" + staffType +
                ", branchUUID=" + branchUUID +
                ", branchT=" + branchT +
                ", gender=" + gender +
                '}';
    }
}
