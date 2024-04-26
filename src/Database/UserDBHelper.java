/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Database;

import Database.DataStructs.User_T;

/**
 * helper for user database
 */
public class UserDBHelper extends DatabaseHelper {
    public UserDBHelper(String databaseString) {
        super(databaseString);
    }

    public int idxInDatabase_username(User_T userPartial, boolean getFirst) {
        int idx = -1;
        User_T userTemp;
        for (int i = 0; i < this.databaseObject.size(); i++) {
            userTemp = (User_T) this.databaseObject.get(i);
            if (userPartial.getUsername().equals(userTemp.getUsername())) {
                idx = i;
                if (getFirst) {
                    return idx;
                }
            }
        }
        return idx;
    }

    public int idxInDatabase_uuid(User_T userPartial, boolean getFirst) {
        int idx = -1;
        User_T userTemp;
        for (int i = 0; i < this.databaseObject.size(); i++) {
            userTemp = (User_T) this.databaseObject.get(i);
            if (userPartial.getUserUUID().equals(userTemp.getUserUUID())) {
                idx = i;
                if (getFirst) {
                    return idx;
                }
            }
        }
        return idx;
    }

    public boolean isInDatabase(User_T userPartial) {
        User_T userTemp;
        for (int i = 0; i < this.databaseObject.size(); i++) {
            userTemp = (User_T) this.databaseObject.get(i);
            if (userPartial.getUsername().equals(userTemp.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public boolean addToDatabase(User_T userFull) {
        if (this.isInDatabase(userFull)) {
//            username exists
            return false;
        }
        return this.databaseObject.add(userFull);
    }

//    we can set a enum for status
    public User_T verifyLogin(User_T userPartial) {
        int idx = this.idxInDatabase_username(userPartial, true);

        //        username DNE
        if (idx == -1) return null;

        User_T userTemp = (User_T) this.getFromDatabase(idx);

        boolean verified = false;
        verified = userTemp.verifyPassword(userPartial);
        if (verified) return userTemp;

        return null;
    }

    public boolean updateUserPassword(User_T userPartial) {
        int idx = this.idxInDatabase_username(userPartial, true);
        //        username DNE
        if (idx == -1) return false;

        User_T userTemp = (User_T) this.getFromDatabase(idx);
        userTemp.setPassword(userPartial.getPassword());

        this.updateDataInDatabase(idx, userTemp);
        return true;
    }

}
