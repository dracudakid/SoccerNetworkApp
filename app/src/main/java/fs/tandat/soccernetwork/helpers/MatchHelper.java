package fs.tandat.soccernetwork.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import fs.tandat.soccernetwork.bean.Match;
import fs.tandat.soccernetwork.bean.User;

/**
 * Created by dracu on 24/04/2016.
 */
public class MatchHelper {
    DatabaseHelper dbHelper;
    private Context context;
    public MatchHelper(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }
    public ArrayList<Match> getYourMatches(String username){
        ArrayList<Match> matches = new ArrayList<>();
        UserHelper userHelper = new UserHelper(context);
        User user = userHelper.getUser(username);
        int user_id = user.getUser_id();
        String sql = "select match_id, field_name, username, maximum_players, price, start_time, end_time " +
                "from matches join user_profiles on matches.host_id = user_id " +
                "join fields on fields.field_id=matches.field_id where user_id = " + user_id +
                " order by start_time";
        try {
            SQLiteDatabase db = dbHelper.openDatabase();
            Log.d("getYourMatches", sql);
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    Match m = new Match();
                    m.setMatch_id(cursor.getInt(0));
                    m.setField_name(cursor.getString(1));
                    m.setUsername(cursor.getString(2));
                    m.setMaximum_players(cursor.getInt(3));
                    m.setPrice(cursor.getInt(4));
                    m.setStart_time(cursor.getString(5));
                    m.setEnd_time(cursor.getString(6));
                    matches.add(m);
                    cursor.moveToNext();
                }
                return matches;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Match> getUpcommingMatches(String username){
        ArrayList<Match> matches = new ArrayList<>();
        UserHelper userHelper = new UserHelper(context);
        User user = userHelper.getUser(username);
        String sql =  "select match_id, field_name, username, maximum_players, price, start_time, end_time " +
                "from matches join user_profiles on matches.host_id = user_id " +
                "join fields on fields.field_id=matches.field_id";
        try {
            SQLiteDatabase db = dbHelper.openDatabase();
            Log.d("getUpcommingMatches", sql);
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    Match m = new Match();
                    m.setMatch_id(cursor.getInt(0));
                    m.setField_name(cursor.getString(1));
                    m.setUsername(cursor.getString(2));
                    m.setMaximum_players(cursor.getInt(3));
                    m.setPrice(cursor.getInt(4));
                    m.setStart_time(cursor.getString(5));
                    m.setEnd_time(cursor.getString(6));
                    matches.add(m);
                    cursor.moveToNext();
                }
                return matches;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
