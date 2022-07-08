package com.example.excel;

import androidx.appcompat.app.AppCompatActivity;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.WorkSource;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
TextView tv,tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
    }

    public void order(View view) throws IOException, BiffException {
        tv = findViewById (R.id.tv);
        AssetManager am = getAssets ();
        InputStream is = am.open ("jxlrwtest.xls");
        Workbook wb = Workbook.getWorkbook (is);
        Sheet s = wb.getSheet (0);
        int row = s.getRows ();
        int col = s.getColumns ();
        String xn ="";
        for (int i=0;i<row;i++){
            for (int c=0 ; c<col ; c++){
                Cell cell = s.getCell (c,i);
                xn= xn+ (cell.getContents ());

            }
            xn =xn+ ("\n");
            tv.setText (String.valueOf (i+1));
        }

        display (xn);
    }

    public  void display(String value){
        tv1 = findViewById (R.id.tv1);
        tv1.setText (value);
    }
}
