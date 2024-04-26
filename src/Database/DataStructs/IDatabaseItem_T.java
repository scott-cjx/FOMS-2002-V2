/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Database.DataStructs;

/**
 * Database Item interface for Serializable
 */
public interface IDatabaseItem_T extends java.io.Serializable {

    /**
     * string representation of the object
     * @return
     */
    String prettyPrint();

    /**
     * add the current object to the database
     * @return true if action is successful
     */
    boolean addMeToDB();
}
