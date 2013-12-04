package com.extraslice.walknpay.adapter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

	private static String DB_PATH = "/data/data/com.extraslice.walknpay/databases/";
	private static String DB_NAME = "Walk_N_Pay_DB";
	String DEFAULT_DESTINATION = DB_PATH + DB_NAME;

	private SQLiteDatabase myDataBase;
	private final Context myContext;

	public static String KEY_ID = "ID";
	public static String KEY_NAME = "Name";
	public static String KEY_SHORTNAME = "ShortName";
	public static String KEY_VERSE = "Verse";
	public static String KEY_DATA = "Data";

	InputStream is;

	public DataBaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}

	public void createDataBase() {
		try {
			copyDataBase();
		} catch (IOException e) {
			throw new Error("Error copying database");
		}

	}

	public void copyDataBase() throws IOException {
		this.getWritableDatabase();

		try {

			is = myContext.getAssets().open(DB_NAME);
			byte[] buffer = new byte[1024];
			int length;
			OutputStream os = new FileOutputStream(DEFAULT_DESTINATION);

			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}

			

			os.flush();
			os.close();
			is.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void openDataBase() throws SQLException {
		// Open the database
		myDataBase = SQLiteDatabase.openDatabase(DEFAULT_DESTINATION, null,
				SQLiteDatabase.OPEN_READWRITE);
	}

	public Cursor getProductDetails(String upcCode) {

		Cursor productDetails = myDataBase.rawQuery(
				"SELECT * FROM Price where Product_Code=\"" + upcCode + "\"",
				null);

		return productDetails;
	}


	@Override
	public synchronized void close() {
		if (myDataBase != null)
			myDataBase.close();
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
