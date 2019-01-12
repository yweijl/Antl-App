package com.avansprojects.antl.infrastructure.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.avansprojects.antl.AntlApp;
import com.avansprojects.antl.R;
import com.avansprojects.antl.helpers.CalendarHelper;
import com.avansprojects.antl.infrastructure.daos.ContactDao;
import com.avansprojects.antl.infrastructure.daos.EventDao;
import com.avansprojects.antl.infrastructure.daos.UserDao;
import com.avansprojects.antl.infrastructure.entities.Contact;
import com.avansprojects.antl.infrastructure.entities.DateConverter;
import com.avansprojects.antl.infrastructure.entities.Event;
import com.avansprojects.antl.infrastructure.entities.Group;
import com.avansprojects.antl.infrastructure.entities.User;
import com.avansprojects.antl.infrastructure.entities.UserEvent;
import com.avansprojects.antl.infrastructure.entities.UserGroup;
import com.avansprojects.antl.ui.login.dto.UserDto;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class, Event.class, Contact.class,
                      Group.class, UserEvent.class,
                      UserGroup.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})

public abstract class AntlDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract EventDao eventDao();
    public abstract ContactDao contactDao();

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

    public static class DropDatabaseAsync extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(final Void... params) {
            AntlDatabase.getDatabase(AntlApp.getContext()).clearAllTables();
            return null;
        }
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final EventDao _EventDao;
        private final UserDao _UserDao;
        private final ContactDao _ContactDao;

        PopulateDbAsync(AntlDatabase db) {
            _EventDao = db.eventDao();
            _UserDao = db.userDao();
            _ContactDao = db.contactDao();
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

            _UserDao.deleteAll();

            _ContactDao.deleteAll();
            Contact userTwo = new Contact(3);
            userTwo.userName = "Test";
            _ContactDao.insert(userTwo);
            return null;
        }
    }

    public static class SetMainUser extends AsyncTask<Void, Void, Void> {
        private final UserDao mUserDao;
        private final UserDto mUserDto;

        public SetMainUser(AntlDatabase db, UserDto userDto)
        {
            mUserDao = db.userDao();
            mUserDto = userDto;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mUserDao.deleteAll();
            User user = new User(1);

            user.setUserName( mUserDto.getUserName());
            user.firstName = mUserDto.getFirstName();
            user.lastName = mUserDto.getLastName();
            user.email = mUserDto.getEmail();
            user.phoneNumber = mUserDto.getPhoneNumber();
            user.webServerId = mUserDto.getExternalId();

            mUserDao.insert(user);

            Log.i(this.toString(), "user created");
            return null;
        }
    }
}