package com.avansprojects.antl.infrastructure.database;

import android.content.Context;
import android.os.AsyncTask;
import com.avansprojects.antl.R;
import com.avansprojects.antl.helpers.CalendarHelper;
import com.avansprojects.antl.infrastructure.daos.EventDao;
import com.avansprojects.antl.infrastructure.daos.EventDateDao;
import com.avansprojects.antl.infrastructure.daos.RelationshipDao;
import com.avansprojects.antl.infrastructure.daos.UserDao;
import com.avansprojects.antl.infrastructure.entities.DateConverter;
import com.avansprojects.antl.infrastructure.entities.Event;
import com.avansprojects.antl.infrastructure.entities.EventDate;
import com.avansprojects.antl.infrastructure.entities.Relationship;
import com.avansprojects.antl.infrastructure.entities.Group;
import com.avansprojects.antl.infrastructure.entities.User;
import com.avansprojects.antl.infrastructure.entities.UserEvent;
import com.avansprojects.antl.infrastructure.entities.UserGroup;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class, Event.class, Relationship.class,
        EventDate.class, Group.class, UserEvent.class, UserGroup.class},
        version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})

public abstract class AntlDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract EventDao eventDao();
    public abstract RelationshipDao relationshipDao();
    public abstract EventDateDao eventDateDao();

    private static volatile AntlDatabase INSTANCE;

    public static AntlDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AntlDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AntlDatabase.class, "antl_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final EventDao _EventDao;
        private final EventDateDao mEventDateDao;
        private final RelationshipDao _RelationshipDao;
        private final UserDao _UserDao;

        PopulateDbAsync(AntlDatabase db) {
            _EventDao = db.eventDao();
            mEventDateDao = db.eventDateDao();
            _RelationshipDao = db.relationshipDao();
            _UserDao = db.userDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            _EventDao.deleteAll();
            Event event = new Event("New years Eve", CalendarHelper.setDate(2018,12,31), "den haag", R.drawable.newyear);
            _EventDao.insert(event);
            event = new Event("berlin trip", CalendarHelper.setDate(2019, 2,10), "Berlijn", R.drawable.event);
            _EventDao.insert(event);
            event = new Event("B-day Party", CalendarHelper.setDate(2019,6,9), "Den Haag", R.drawable.presentation);
            _EventDao.insert(event);
            event = new Event("Bordspellen dag", CalendarHelper.setDate(2018,12,25), "Den Haag", R.drawable.boardgame);
            _EventDao.insert(event);

            _RelationshipDao.deleteAll();
            _UserDao.deleteAll();
            User userOne = new User(1);
            _UserDao.insert(userOne);
            User userTwo = new User(3);
            _UserDao.insert(userTwo);
            Relationship relationship = new Relationship(userOne.id, userTwo.id);
            _RelationshipDao.insert(relationship);
            return null;
        }
    }
}