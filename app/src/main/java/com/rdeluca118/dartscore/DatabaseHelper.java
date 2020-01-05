package com.rdeluca118.dartscore;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Table Names
    public static final String TABLE_GAME = "game";
    public static final String TABLE_PLAYER = "player";
    public static final String TABLE_LEG = "leg";
    public static final String TABLE_TURN = "turn";

    // Game Table columns
    public static final String GAME_ID = "_id";
    public static final String game_date = "date";
    public static final String game_player1 = "player1_id";
    public static final String game_player2 = "player2_id";
    public static final String game_num_legs = "max_legs";

    // Player Table columns

    public static final String PLAYER_ID = "_id";
    public static final String player_name = "name";

    // Leg Table columns
    public static final String LEG_ID = "_id";
    public static final String game_id = "game_id";

    // Turn Table columns
    public static final String TURN_ID = "_id";
    public static final String player_id = "player_id";
    public static final String leg_id = "leg_id";
    public static final String dart_one = "dart_1";
    public static final String dart_two = "dart_2";
    public static final String dart_three = "dart_3";

    // Database Information
    static final String DB_NAME = "darts118";

    // database version
    static final int DB_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db){

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
