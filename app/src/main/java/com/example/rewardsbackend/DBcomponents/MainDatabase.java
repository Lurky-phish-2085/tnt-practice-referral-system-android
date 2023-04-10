package com.example.rewardsbackend.DBcomponents;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class, Transaction.class, ReferenceKey.class}, version = 1)
public abstract class MainDatabase extends RoomDatabase {
    private static MainDatabase instance;
    public abstract UsrDao usrDao();
    public abstract TransactionDao transactionDao();
    public abstract ReferenceKeyDao referenceKeyDao();

    public static synchronized MainDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MainDatabase.class, "DB")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    private static RoomDatabase.Callback prepop = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new prepopulationTask(instance).execute();
        }
    };

    private static class prepopulationTask extends AsyncTask<Void, Void, Void>{
        private UsrDao usrDao;
        private TransactionDao transactionDao;
        private ReferenceKeyDao referenceKeyDao;

        public prepopulationTask(MainDatabase db) {
            usrDao = db.usrDao();
            transactionDao = db.transactionDao();
            referenceKeyDao = db.referenceKeyDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            User usr = new User("Admin", "Admin");
            usr.setId(1);
            usrDao.insert(usr);
            transactionDao.insert(new Transaction("Initial funds", 9000, 1));
            referenceKeyDao.insert(new ReferenceKey("DokitoMVP", 1));
            return null;
        }
    }


}
