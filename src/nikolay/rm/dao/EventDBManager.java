package nikolay.rm.dao;

import android.content.Context;
import android.util.Log;
import nikolay.rm.vo.Event;

import java.sql.SQLException;
import java.util.List;

public class EventDBManager {

    private EventsDBHelper eventsDBHelper;

    private static EventDBManager instance;

    public EventDBManager(Context context) {

        eventsDBHelper = new EventsDBHelper(context);

    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new EventDBManager(context);
        }

    }
    public static EventDBManager getInstance(){

        return instance;
    }
//    public List<RMProject> getAllProjects() {
//        List<RMProject> projects = null;
//        try {
//            projects = projectDBHelper.getRmProjectDao().queryForAll();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return projects;
//    }

    public List<Event> getAllEvents() {
        List<Event> events = null;
        try {
            events = eventsDBHelper.getEventDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events ;
    }

    public void addEvent(Event event){
        try {
            eventsDBHelper.getEventDao().create(event);
        } catch (SQLException e) {
            Log.e(event.getName(), "can't add to databas", e);
            throw new RuntimeException(e);
        }
    }
}
