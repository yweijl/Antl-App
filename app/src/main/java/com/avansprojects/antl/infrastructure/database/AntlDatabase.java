package com.avansprojects.antl.infrastructure.database;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.avansprojects.antl.AntlApp;
import com.avansprojects.antl.R;
import com.avansprojects.antl.helpers.CalendarHelper;
import com.avansprojects.antl.infrastructure.daos.FriendDao;
import com.avansprojects.antl.infrastructure.daos.EventDao;
import com.avansprojects.antl.infrastructure.daos.EventDateDao;
import com.avansprojects.antl.infrastructure.daos.UserDao;
import com.avansprojects.antl.infrastructure.entities.Friend;
import com.avansprojects.antl.infrastructure.entities.DateConverter;
import com.avansprojects.antl.infrastructure.entities.Event;
import com.avansprojects.antl.infrastructure.entities.EventDate;
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

@Database(entities = {User.class, Event.class, Friend.class,
                      Group.class, UserEvent.class, EventDate.class,
                      UserGroup.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})

public abstract class AntlDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract EventDao eventDao();
    public abstract FriendDao contactDao();
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

    public static class DropDatabaseAsync extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(final Void... params) {
            AntlDatabase.getDatabase(AntlApp.getContext()).clearAllTables();
            return null;
        }
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final EventDao _EventDao;
        private final EventDateDao mEventDateDao;
        private final UserDao _UserDao;
        private final FriendDao _FriendDao;

        PopulateDbAsync(AntlDatabase db) {
            _EventDao = db.eventDao();
            mEventDateDao = db.eventDateDao();
            _UserDao = db.userDao();
            _FriendDao = db.contactDao();
        }

        public String getURLForResource (int resourceId) {
            return Uri.parse("android.resource://"+R.class.getPackage().getName()+"/" +resourceId).toString();
        }

        @Override
        protected Void doInBackground(final Void... params) {
             mEventDateDao.deleteAll();
            _EventDao.deleteAll();
            Event event = new Event("New years Eve", "1234-1234-1234", CalendarHelper.setDate(2018,12,31),"den haag", "hoi",  getURLForResource(R.drawable.newyear),true,1);
            _EventDao.insert(event);
            event = new Event("berlin trip","1234-1234-1234", CalendarHelper.setDate(2019, 2,10), "Berlijn", "hoi", getURLForResource(R.drawable.event),true, 1);
            _EventDao.insert(event);
            event = new Event("B-day Party","1234-1234-1234", CalendarHelper.setDate(2019,6,9), "Den Haag", "hoi", getURLForResource(R.drawable.presentation),false, 1);
            _EventDao.insert(event);
            event = new Event("Bordspellen dag","1234-1234-1234", CalendarHelper.setDate(2018,12,25), "Den Haag", "hoi", getURLForResource(R.drawable.boardgame),false,1);
            _EventDao.insert(event);
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
            user.externalId = mUserDto.getExternalId();

            mUserDao.insert(user);

            Log.i(this.toString(), "user created");
            return null;
        }
    }
}