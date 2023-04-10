package com.example.rewardsbackend.DBcomponents;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MainRepository {
    private UsrDao usrDao;
    private TransactionDao transactionDao;
    private ReferenceKeyDao referenceKeyDao;

    private LiveData<List<User>> allUsers;
    private LiveData<List<Transaction>> allTransactions;
    private LiveData<List<ReferenceKey>> allReferences;

    public MainRepository(Application application) {
        MainDatabase db = MainDatabase.getInstance(application);
        usrDao = db.usrDao();
        transactionDao = db.transactionDao();
        referenceKeyDao = db.referenceKeyDao();

        allUsers = usrDao.getAllUsers();
        allTransactions = transactionDao.getAllTransactions();
        allReferences = referenceKeyDao.getAllReferences();
    }

    private static class AsyncRepoTask extends AsyncTask<Void, Void, Void>{
        private UsrDao usrDao;
        private TransactionDao transactionDao;
        private ReferenceKeyDao referenceKeyDao;
        private int mode;
        private User user;
        private Transaction transaction;
        private ReferenceKey referenceKey;

        public AsyncRepoTask(UsrDao usrDao, int mode, User user) {
            this.usrDao = usrDao;
            this.mode = mode;
            this.user = user;
        }

        public AsyncRepoTask(TransactionDao transactionDao, int mode, Transaction transaction) {
            this.transactionDao = transactionDao;
            this.mode = mode;
            this.transaction = transaction;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
