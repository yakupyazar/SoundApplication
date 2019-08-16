package sound.soundapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemListener {

    private MediaPlayer mediaPlayer = null;

    Button buttonPlayPause;
    TextView textViewTitle;
    RecyclerView recyclerView;
    ArrayList arrayList;
    String soundID= "vacuumsound";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_player_layout);
        buttonPlayPause = findViewById(R.id.buttonPlayPause);
        textViewTitle = findViewById(R.id.textViewTitle);

        buttonPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playPause();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        arrayList = new ArrayList();

        arrayList.add(new Model("Süpürge", R.drawable.vacuum, "#09A9FF"));
        arrayList.add(new Model("Saç Kurutma", R.drawable.hairdryer, "#3E51B1"));
        arrayList.add(new Model("Okyanus", R.drawable.sunset, "#1F75FE"));
        arrayList.add(new Model("Vantilatör", R.drawable.fan, "#673BB7"));
        arrayList.add(new Model("Anne Karnı", R.drawable.embryo, "#FF007F"));
        arrayList.add(new Model("Duş", R.drawable.shower, "#4BAA50"));
        arrayList.add(new Model("Dalga", R.drawable.sea, "#0A9B88"));
        arrayList.add(new Model("Yağmur", R.drawable.rain, "#F94336"));
        arrayList.add(new Model("Su", R.drawable.water, "#77B5FE"));
        arrayList.add(new Model("Şelale", R.drawable.waterfall, "#465945"));


        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, arrayList, this);
        recyclerView.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

    }

    @Override
    public void onItemClick(Model item) {

        switch (item.text){
            case "Süpürge":
                soundID= "vacuumsound";
                textViewTitle.setText("Süpürge");
                break;
            case "Saç Kurutma":
                soundID= "dryersound";
                textViewTitle.setText("Saç Kurutma");
                break;
            case "Vantilatör":
                soundID= "fansound";
                textViewTitle.setText("Vantilatör");
                break;
            case "Duş":
                soundID= "showersound";
                textViewTitle.setText("Duş");
                break;
            case "Yağmur":
                soundID= "rainsound";
                textViewTitle.setText("Yağmur");
                break;
            case "Su":
                soundID= "watersound";
                textViewTitle.setText("Su");
                break;
            case "Okyanus":
                soundID= "oceansound";
                textViewTitle.setText("Okyanus");
                break;
            case "Şelale":
                soundID= "waterfallsound";
                textViewTitle.setText("Şelale");
                break;
            case "Anne Karnı":
                soundID= "mothersound";
                textViewTitle.setText("Anne Karnı");
                break;
            case "Dalga":
                soundID= "wavessound";
                textViewTitle.setText("Dalga");
                break;


        }


        changeSound(soundID);
    }

    private void changeSound(String soundID)
    {
        if (mediaPlayer != null)
        {
        buttonPlayPause.setBackgroundResource(R.drawable.pause);
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = MediaPlayer.create(this,getResourceIDFromCase(soundID));
            mediaPlayer.setLooping(true);

            mediaPlayer.start();
        }
        else {
            buttonPlayPause.setBackgroundResource(R.drawable.pause);
            mediaPlayer = MediaPlayer.create(this,getResourceIDFromCase(soundID));
            mediaPlayer.setLooping(true);

            mediaPlayer.start();

        }
    }


    private void playPause() {

        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                buttonPlayPause.setBackgroundResource(R.drawable.playbutton);

            } else {
                mediaPlayer.release();
                mediaPlayer = MediaPlayer.create(this, getResourceIDFromCase(soundID));
                mediaPlayer.setLooping(true);

                mediaPlayer.start();
                buttonPlayPause.setBackgroundResource(R.drawable.pause);

            }
        }
        else {
            mediaPlayer = MediaPlayer.create(this,getResourceIDFromCase(soundID));
            mediaPlayer.setLooping(true);

            mediaPlayer.start();
            buttonPlayPause.setBackgroundResource(R.drawable.pause);
        }

    }



    private int getResourceIDFromCase(String soundIDCase)
    {
        int soundId  = getResources().getIdentifier(soundIDCase,"raw",this.getPackageName());
        return soundId;
    }


}
