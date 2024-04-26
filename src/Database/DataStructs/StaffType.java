/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Database.DataStructs;

/**
 * enum for type of staff
 */
public enum StaffType {
    ADMIN('A'), BRANCH_MANAGER('B'), NORMAL_STAFF('S'), NA('N');

    public char toChar() {
        return toChar;
    }

    private final char toChar;
    StaffType(char toChar) {
        this.toChar = toChar;
    }
}