package com.example.foodplanner.model.Backup;


import android.util.Log;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.List;
import java.util.concurrent.Executors;

import com.example.foodplanner.model.dp.MealDAO;
import com.example.foodplanner.model.mealDetails.MealDetails;
import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMeal;
import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMealDao;
import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMealDetails;
import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMealDetailsDao;

public class BackupDB {
    private final FirebaseFirestore firebaseFirestore;
    private final MealDAO mealDAO;
    private final WeeklyPlanMealDao weeklyPlanMealDao;
    private final WeeklyPlanMealDetailsDao weeklyPlanMealDetailsDao;
    private final FirebaseAuth auth;

    public BackupDB(MealDAO mealDAO, WeeklyPlanMealDao myWeekDao,WeeklyPlanMealDetailsDao weeklyPlanMealDetailsDao) {
        firebaseFirestore = FirebaseFirestore.getInstance();
        this.mealDAO = mealDAO;
        this.weeklyPlanMealDao = myWeekDao;
        this.weeklyPlanMealDetailsDao = weeklyPlanMealDetailsDao;
        auth = FirebaseAuth.getInstance();

    }

    public void backupDataToFirestore() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();

            // Backup FavMeals
            CollectionReference collectionRefForFav = firebaseFirestore.collection("users")
                    .document(userId)
                    .collection("FavMeals");
            Log.i("FirebaseAuth", "backupDataToFirestore: " + userId);
            collectionRefForFav.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    WriteBatch batch = firebaseFirestore.batch();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        batch.delete(document.getReference());
                    }
                    batch.commit().addOnCompleteListener(deleteTask -> {
                        if (deleteTask.isSuccessful()) {
                            Executors.newSingleThreadExecutor().execute(() -> {
                                List<MealDetails> items = mealDAO.getAllMealsForBackup();
                                for (MealDetails item : items) {
                                    collectionRefForFav.document(item.getIdMeal()).set(item)
                                            .addOnSuccessListener(aVoid -> Log.d("FavMealsBackup", "Data backed up successfully - Meal : " + item.getIdMeal()))
                                            .addOnFailureListener(e -> Log.e("FavMealsBackup", "Error backing up data", e));
                                }
                            });
                        } else {
                            Log.e("FavMealsBackup", "Error deleting old data", deleteTask.getException());
                        }
                    });
                } else {
                    Log.e("FavMealsBackup", "Error getting documents for deletion: ", task.getException());
                }
            });

            // Backup WeekPlan
            CollectionReference collectionRefForWeekPlan = firebaseFirestore.collection("users")
                    .document(userId)
                    .collection("WeekPlan");

            collectionRefForWeekPlan.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    WriteBatch batch = firebaseFirestore.batch();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        batch.delete(document.getReference());
                    }
                    batch.commit().addOnCompleteListener(deleteTask -> {
                        if (deleteTask.isSuccessful()) {
                            Executors.newSingleThreadExecutor().execute(() -> {
                                List<WeeklyPlanMeal> itemsWeek = weeklyPlanMealDao.getAllPlanMealsforBackup();
                                for (WeeklyPlanMeal item : itemsWeek) {
                                    collectionRefForWeekPlan.document(item.getIdMeal()).set(item)
                                            .addOnSuccessListener(aVoid -> Log.d("WeekPlanBackup", "Data backed up successfully - Meal : " + item.getIdMeal()))
                                            .addOnFailureListener(e -> Log.e("WeekPlanBackup", "Error backing up data", e));
                                }
                            });
                        } else {
                            Log.e("WeekPlanBackup", "Error deleting old data", deleteTask.getException());
                        }
                    });
                } else {
                    Log.e("WeekPlanBackup", "Error getting documents for deletion: ", task.getException());
                }
            });

            // Backup WeekPlanDetails
            CollectionReference collectionRefWeekPlanDetails = firebaseFirestore.collection("users")
                    .document(userId)
                    .collection("WeekPlanDetails");

            collectionRefWeekPlanDetails.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    WriteBatch batch = firebaseFirestore.batch();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        batch.delete(document.getReference());
                    }
                    batch.commit().addOnCompleteListener(deleteTask -> {
                        if (deleteTask.isSuccessful()) {
                            Executors.newSingleThreadExecutor().execute(() -> {
                                List<WeeklyPlanMealDetails> itemsWeek = weeklyPlanMealDetailsDao.getAllPlanMealsforBackup();
                                for (WeeklyPlanMealDetails item : itemsWeek) {
                                    collectionRefForWeekPlan.document(item.getIdMeal()).set(item)
                                            .addOnSuccessListener(aVoid -> Log.i("WeekPlanBackup", "Data backed up successfully - Meal : " + item.getIdMeal()))
                                            .addOnFailureListener(e -> Log.i("WeekPlanBackup", "Error backing up data", e));
                                }
                            });
                        } else {
                            Log.e("WeekPlanBackup", "Error deleting old data", deleteTask.getException());
                        }
                    });
                } else {
                    Log.e("WeekPlanBackup", "Error getting documents for deletion: ", task.getException());
                }
            });
        }
    }
    public void restoreDataFromFirestore() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();

            Executors.newSingleThreadExecutor().execute(mealDAO::deleteAll);

            CollectionReference collectionRefForFav = firebaseFirestore.collection("users")
                    .document(userId)
                    .collection("FavMeals");

            collectionRefForFav.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        MealDetails item = document.toObject(MealDetails.class);
                        Executors.newSingleThreadExecutor().execute(() -> {
                            mealDAO.insertMany(item);
                        });
                    }
                } else {
                    Log.i("FavMealsRestore", "Error getting documents: ", task.getException());
                }
            });

            Executors.newSingleThreadExecutor().execute(weeklyPlanMealDao::deleteAll);

            CollectionReference collectionRefFoWeekPlan = firebaseFirestore.collection("users")
                    .document(userId)
                    .collection("WeekPlan");

            collectionRefFoWeekPlan.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        WeeklyPlanMeal item = document.toObject(WeeklyPlanMeal.class);
                        Executors.newSingleThreadExecutor().execute(() -> {
                            weeklyPlanMealDao.insertMany(item);
                        });
                    }
                } else {
                    Log.e("WeekPlanRestore", "Error getting documents: ", task.getException());
                }
            });

            Executors.newSingleThreadExecutor().execute(weeklyPlanMealDetailsDao::deleteAll);

            CollectionReference collectionRefForWeekPlanDetails = firebaseFirestore.collection("users")
                    .document(userId)
                    .collection("WeekPlanDetails");

            collectionRefForWeekPlanDetails.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        WeeklyPlanMealDetails item = document.toObject(WeeklyPlanMealDetails.class);
                        Executors.newSingleThreadExecutor().execute(() -> {
                            weeklyPlanMealDetailsDao.insertMany(item);
                        });
                    }
                } else {
                    Log.e("WeekPlanRestore", "Error getting documents: ", task.getException());
                }
            });
        }
    }
}
