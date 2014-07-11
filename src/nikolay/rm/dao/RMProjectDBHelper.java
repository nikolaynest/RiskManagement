package nikolay.rm.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import nikolay.rm.vo.RMProject;

import java.sql.SQLException;

/**
 * Created by nikolay on 15.04.14.
 */
public class RMProjectDBHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "RM_DB";
    private static final int DB_VERSION = 1;
    private Dao<RMProject, Integer> rmProjectDao = null;


    public RMProjectDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,RMProject.class);
        } catch (SQLException e) {
            Log.e(RMProjectDBHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        //I don't need it =)
    }

    public Dao<RMProject, Integer> getRmProjectDao(){
        if (rmProjectDao == null){
            try {
                rmProjectDao = getDao(RMProject.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rmProjectDao;
    }


}
