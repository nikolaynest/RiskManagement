package nikolay.rm.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import nikolay.rm.vo.RMProject;

import java.util.*;

/**
 * Created by nikolay on 11.04.14.
 */
public class RMProjectDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "RM_DB";
    private static final String TABLE_PROJECT = "PROJECT";
    private static final String[] COLUMNS = {"id_project", "project_name"};
    private static final int DB_VERSION = 1;
//    private Cursor constantsCursor = null;
    private ArrayList<Map<String, Object>> data = new ArrayList<>();

    public RMProjectDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROJECT_TABLE = "CREATE TABLE " + TABLE_PROJECT + " (" +
                " id_project INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "project_name TEXT)";
        db.execSQL(CREATE_PROJECT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECT);
        this.onCreate(db);
    }

    public void addProject(RMProject project) {
        Log.d("add project: ", project.toString());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("project_name", project.getName());
        db.insert(TABLE_PROJECT, null, values);
        db.close();
        Log.d("after adding project: ", project.toString());
    }

    public RMProject getProject(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PROJECT, COLUMNS, " id_project=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        RMProject project = new RMProject();
        project.setId(Integer.parseInt(cursor.getString(0)));
        project.setName(cursor.getString(1));
        Log.d("getProject(" + id + ")", project.toString());
        return project;
    }

    public ArrayList<Map<String, Object>> getAllProjects() {
        Map<String, Object> map;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_PROJECT, COLUMNS, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            data.clear();
            do {
                map = new HashMap<>();
                map.put("id", cursor.getInt(cursor.getColumnIndex("id_project")));
                map.put("name", cursor.getString(cursor.getColumnIndex("project_name")));
                data.add(map);
            } while (cursor.moveToNext());
        }
        Log.d("getAllProjects()", data.toString());
        return data;
    }

    public void deleteProject(RMProject project) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROJECT, "id_project = ?", new String[]{String.valueOf(project.getId())});
        db.close();
        Log.d("deleteProject():", project.toString());
    }

    public void deleteProject(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROJECT, "id_project = ?", new String[]{String.valueOf(id)});
        db.close();
        Log.d("deleteProject with id: ", Integer.toString(id));
    }

    public int findIdByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT PROJECT.id_project FROM PROJECT WHERE PROJECT.project_name = '" + name + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        int id = -1;
        while (cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex("id_project"));
            Log.i("LOGGING:", " FIND ID BY NAME: ID=" + id);
        }
        return id;
    }


    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.query(TABLE_PROJECT, null, null, null, null, null, null);
    }
}
