package ir.shabg.puzzle;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int nums[]={1,2,3,4,8,5,7,6,9};
    private int ninepos= 8;
    private int moves=0;
private void shuffle()
{
    Random rnd = new Random();
    moves=0;

    for(int i=0;i<100;i++) {

        int p1=   rnd.nextInt(9);
        int p2=   rnd.nextInt(9);
        int tmp = nums[p1];
        nums[p1] = nums[p2];
        nums[p2] = tmp;
        if (nums[p1]==9)
            ninepos=p1;
        if (nums[p2]==9)
            ninepos=p2;


    }
}

private int abs(int num)
{
    return num<0?-num:num;
}
    private boolean isneighbor(int nineposition, int aposition)
    {
        int posrow = aposition / 3;
        int poscol = aposition % 3;
        int ninerow = nineposition / 3;
        int ninecol = nineposition % 3;
        return (abs(poscol-ninecol)+abs(posrow-ninerow))<2;
    }

    private Boolean wonstate()
    {
        Boolean res=true;
        for (int i = 0; (9 > i) && res; i++) {
            res = (i+1 == nums[i]);
        }
        return res;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        shuffle();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GridView gridview = (GridView) findViewById(R.id.gridview);
        final TextView lblcnt  = (TextView) findViewById(R.id.lblcnt);
//        gridview.setAdapter(new ImageAdapter(this));
        String prestr = moves>1?"Moves:":"Move:";

        lblcnt.setText(prestr+" "+String.valueOf(moves));

        final numtileadapter puzdapter = new numtileadapter(this, nums);
        gridview.setAdapter(puzdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if (isneighbor(ninepos, position) && nums[position]!=9) {

                    nums[ninepos]=nums[position];
                    nums[position] = 9;
                    ninepos = position;
                    moves = moves +1 ;
                    String prestr = moves>1?"Moves:":"Move:";

                    lblcnt.setText(prestr+" "+String.valueOf(moves));


                    gridview.setAdapter(puzdapter);
                }

                if (wonstate()) {


                    //_________
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    //Yes button clicked
                                    shuffle();
                                    lblcnt.setText("Move: 0");
                                    gridview.setAdapter(puzdapter);

                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    //No button clicked
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                    builder.setMessage("Congradulations, You Won.\nWould you like to start a new game?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
                    //__________
//                    Toast.makeText(MainActivity.this, "Puzzle solved!. Let's shuflle it for a new game.",
//                            Toast.LENGTH_LONG).show();


                }
            }
        });
    }
}
