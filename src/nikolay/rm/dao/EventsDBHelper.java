package nikolay.rm.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import nikolay.rm.vo.Event;
import nikolay.rm.vo.Plan;
import nikolay.rm.vo.Risk;

import java.sql.SQLException;

/**
 * Created by nikolay on 15.04.14.
 */
public class EventsDBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "RM_DB";
    private static final int DB_VERSION = 1;
    private Dao<Event, Integer> eventDao = null;
    private RuntimeExceptionDao<Event, Integer> eventExcRuntimeDao = null;

    public EventsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            Log.i(EventsDBHelper.class.getName(), "onCreate");
            Log.i(Event.class.toString(), " creates table");
            TableUtils.createTable(connectionSource, Event.class);

            Log.i(Risk.class.toString(), " creates table");
            TableUtils.createTable(connectionSource, Risk.class);

            Log.i(Plan.class.toString(), " creates table");
            TableUtils.createTable(connectionSource, Plan.class);
//            TableUtils.createTable(connectionSource, )
        } catch (SQLException e) {
            Log.e(EventsDBHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }

//        // here we try inserting data in the on-create as a test
//        RuntimeExceptionDao<Event, Integer> dao = getSimpleDataDao();
//
//        // create some entries in the onCreate
//        Event event = new Event("first event");
//        dao.create(event);
//        event = new Event("second event");
//        dao.create(event);
//        Log.i(EventsDBHelper.class.getName(), "created new entries in onCreate: " + event.getName());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            Log.i(EventsDBHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Event.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            Log.e(EventsDBHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }
    /**
     * Returns the Database Access Object (DAO) for our Event class.
     * It will create it or just give the cached value.
     */
    public Dao<Event, Integer> getEventDao() throws SQLException {
        if (eventDao == null) {
            eventDao = getDao(Event.class);
        }
        return eventDao;
    }
    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our SimpleData class. It will
     * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     */
    public RuntimeExceptionDao<Event, Integer> getSimpleDataDao() {
        if (eventExcRuntimeDao == null) {
            eventExcRuntimeDao = getRuntimeExceptionDao(Event.class);
        }
        return eventExcRuntimeDao;
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        eventDao = null;
        eventExcRuntimeDao = null;
    }


}
