package athulya.example.animalsounds;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
 
public class Main extends Activity {
 

 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	final MediaPlayer mp1 = MediaPlayer.create(this, R.raw.cat);
    	final MediaPlayer mp2 = MediaPlayer.create(this, R.raw.cow);
    	final MediaPlayer mp3 = MediaPlayer.create(this, R.raw.horse);
    	final MediaPlayer mp4 = MediaPlayer.create(this, R.raw.dog);
    	final MediaPlayer mp5 = MediaPlayer.create(this, R.raw.elephant);
    	final MediaPlayer mp6 = MediaPlayer.create(this, R.raw.tiger);
    	final MediaPlayer mp7 = MediaPlayer.create(this, R.raw.goat);
    	final MediaPlayer mp8 = MediaPlayer.create(this, R.raw.lion);
    	final MediaPlayer mp9 = MediaPlayer.create(this, R.raw.fox);
    	final MediaPlayer mp10 = MediaPlayer.create(this, R.raw.rooster);
    	final MediaPlayer mp11 = MediaPlayer.create(this, R.raw.frog);
    	final MediaPlayer mp12 = MediaPlayer.create(this, R.raw.squirel);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ImageView cat = (ImageView)this.findViewById(R.id.imageView1);
        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp1.start();
            }
        });
        
        ImageView cow = (ImageView)this.findViewById(R.id.imageView2);
        cow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp2.start();
            }
        });
        
        ImageView horse = (ImageView)this.findViewById(R.id.imageView9);
        horse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp3.start();
            }
        });
        
        ImageView dog = (ImageView)this.findViewById(R.id.imageView3);
        dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp4.start();
            }
        });
        
        ImageView elephant = (ImageView)this.findViewById(R.id.imageView4);
        elephant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp5.start();
            }
        });
        
        ImageView tiger = (ImageView)this.findViewById(R.id.imageView10);
        tiger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp6.start();
            }
        });
        
        ImageView goat = (ImageView)this.findViewById(R.id.imageView5);
        goat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp7.start();
            }
        });
        
        ImageView lion = (ImageView)this.findViewById(R.id.imageView6);
        lion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp8.start();
            }
        });
        
        ImageView fox= (ImageView)this.findViewById(R.id.imageView11);
        fox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp9.start();
            }
        });
        
        ImageView rooster = (ImageView)this.findViewById(R.id.imageView7);
        rooster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp10.start();
            }
        });
        
        ImageView frog = (ImageView)this.findViewById(R.id.imageView8);
        frog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp11.start();
            }
        });
        
        ImageView squirel = (ImageView)this.findViewById(R.id.imageView12);
        squirel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp12.start();
            }
        });
        
    	
        
        
    }
}