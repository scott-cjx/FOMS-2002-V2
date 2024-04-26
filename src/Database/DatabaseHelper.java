/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Database;

import Database.DataStructs.IDatabaseItem_T;

import java.io.*;
import java.util.ArrayList;

/**
 * generic database helper for serializing and deserializing objects to/from
 * a serialized file.
 */
public class DatabaseHelper {
    protected String databasePath = "";

    protected ArrayList<IDatabaseItem_T> databaseObject;

    public DatabaseHelper(String databasePath) {
        this.databasePath = databasePath;

//        initialize databaseObject
        this.databaseObject = new ArrayList<>();
    }

    public boolean saveToDb() {
        try(
                FileOutputStream fout = new FileOutputStream(this.databasePath);
                ObjectOutputStream oos = new ObjectOutputStream(fout);
        ){
            for (int i = 0; i < this.databaseObject.size(); i++) {
                oos.writeObject(this.databaseObject.get(i));
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean addToDatabase(IDatabaseItem_T obj) {
        this.databaseObject.add(obj);
        return true;
    }

    public void updateDataInDatabase(int idx, IDatabaseItem_T obj) {
        this.databaseObject.set(idx, obj);
    }

    public boolean isInDatabase(IDatabaseItem_T obj) {
        return this.databaseObject.contains(obj);
    }

    public int idxInDatabase(IDatabaseItem_T obj) {
        return this.databaseObject.indexOf(obj);
    }

    public void removeFromDatabase(int idx) {
        try {
            this.databaseObject.remove(idx);
        } catch (Exception e) {
            return;
        }
    }

    public void removeFromDatabase(IDatabaseItem_T obj) {
        this.databaseObject.remove(obj);
    }

    public void removeAllFromDatabase() {
        this.databaseObject = new ArrayList<IDatabaseItem_T>();
    }

    public IDatabaseItem_T getFromDatabase(int idx) {
        if (idx == -1) return null;
        return this.databaseObject.get(idx);
    }

    public ArrayList<IDatabaseItem_T> getAllFromDatabase() {
        return this.databaseObject;
    }

    public boolean getFromDb() {
        boolean isOk = false;
        ArrayList<IDatabaseItem_T> tempDatabaseObject = new ArrayList<>();

        ObjectInputStream ios = null;
        try {
            ios = new ObjectInputStream(new FileInputStream(this.databasePath));
        } catch (Exception e) {
            return false;
        }

//        read till EOF
        Object temp;
        try {
            while (true) {
                temp = ios.readObject();
                if(temp == null) break;
                tempDatabaseObject.add((IDatabaseItem_T) temp);
            }
        } catch (Exception ignored) { }

        try {
            ios.close();
            isOk = true;
        } catch (IOException ignored) { }

        this.databaseObject = (ArrayList<IDatabaseItem_T>) tempDatabaseObject.clone();
        return isOk;
    }

    public void printAllInDatabase(boolean prettyPrint) {
        for (IDatabaseItem_T obj : this.databaseObject) {
            System.out.println(prettyPrint? obj.prettyPrint() : obj.toString());
        }
    }
}

