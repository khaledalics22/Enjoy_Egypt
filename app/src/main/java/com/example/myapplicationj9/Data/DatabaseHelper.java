package com.example.myapplicationj9.Data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplicationj9.R;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String Database_Name = "Egypt.db";
    private static final String Create_Method_Cities = "CREATE TABLE " + Contract.Table_Cities_Name + "("
            + Contract.Column_Name_Cities_En + " STRING," + Contract.Column_Photo_Cities + " INTEGER," +
            Contract.Column_Info_Cities_En + " STRING ," + Contract.Column_Name_Cities_Ar + " STRING,"  +
            Contract.Column_Info_Cities_Ar + " STRING )";
    private static final String Drop_Table_Cities ="DROP IF EXISTS " +Contract.Table_Cities_Name ;

    public DatabaseHelper(Context context ) {
        super(context, Database_Name, null, 1);
    }



    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Create_Method_Cities);
        InsertOneTime(sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    private void InsertOneTime (SQLiteDatabase db)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(Contract.Column_Name_Cities_En,"Cairo");
        contentValues.put(Contract.Column_Photo_Cities, R.drawable.cairo);
        contentValues.put(Contract.Column_Info_Cities_En,"Cairo is the capital of Egypt. The city's metropolitan area is one of the largest in Africa, the largest in the Middle East and 15th-largest in the world, and is associated with ancient Egypt, as the famous Giza pyramid complex and the ancient city of Memphis are located in its geographical area. Located near the Nile Delta, modern Cairo was founded in 969 AD by the Fatimid dynasty, but the land composing the present-day city was the site of ancient national capitals whose remnants remain visible in parts of Old Cairo. Cairo has long been a centre of the region's political and cultural life, and is titled \"the city of a thousand minarets\" for its preponderance of Islamic architecture.  ");
        contentValues.put(Contract.Column_Name_Cities_Ar,"القاهرة");
        contentValues.put(Contract.Column_Photo_Cities, R.drawable.cairo);
        contentValues.put(Contract.Column_Info_Cities_Ar,"لقاهرة هي عاصمة جمهورية مصر العربية وأكبر وأهم مدنها على الإطلاق، وتعد أكبر مدينة عربية من حيث تعداد السكان والمساحة، وتحتل المركز الثاني أفريقياً والسابع عشر عالمياً من حيث التعداد السكاني، يبلغ عدد سكانها 9.7 مليون نسمة حسب إحصائيات عام 2018 يمثلون 10.6% من إجمالي تعداد سكان مصر.\n" +
                "\n" +
                "تعد مدينة القاهرة من أكثر المدن تنوعاً ثقافياً وحضارياً، حيث شهدت العديد من الحقب التاريخية المختلفة على مر العصور، وتوجد فيها العديد من المعالم القديمة والحديثة، فأصبحت متحفاً مفتوحاً يضم آثاراً فرعونية ويونانية ورومانية وقبطية وإسلامية. يعود تاريخ المدينة إلى نشأة مدينة أون الفرعونية أو هليوبوليس \"عين شمس حالياً\" والتي تعد واحدة من أقدم مدن العالم القديم. أما القاهرة بطرازها الحالي فيعود تاريخ إنشائها إلى الفتح الإسلامي لمصر على يد عمرو بن العاص عام 641 وإنشائه مدينة الفسطاط، ثم إنشاء العباسيين لمدينة العسكر، فبناء أحمد بن طولون لمدينة القطائع، ومع دخول الفاطميين مصر قادمين من إفريقية (تونس حالياً) بدأ القائد جوهر الصقلي في بناء العاصمة الجديدة للدولة الفاطمية بأمر من الخليفة الفاطمي المعز لدين الله وذلك في عام 969، وأطلق عليها الخليفة اسم \"القاهرة\". وأطلق على القاهرة- على مر العصور- العديد من الأسماء، فهي مدينة الألف مئذنة ومصر المحروسة وقاهرة المعز. شهدت القاهرة خلال العصر الإسلامي أرقى فنون العمارة التي تمثلت في بناء القلاع والحصون والأسوار والمدارس والمساجد، مما منحها لمحةً جماليةً لا زالت موجودة بأحيائها القديمة حتى الآن. ");
        db.insert(Contract.Table_Cities_Name,null,contentValues);
    }
}
