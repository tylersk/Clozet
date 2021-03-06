package com.hackathon.dealeron.clozet;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hackathon.dealeron.clozet.Models.Bottoms.BottomType;
import com.hackathon.dealeron.clozet.Models.Footwear.FootwearType;
import com.hackathon.dealeron.clozet.Models.Headwear.HeadwearType;
import com.hackathon.dealeron.clozet.Models.Tops.TopType;
import com.hackathon.dealeron.clozet.Settings.Settings;

import java.util.ArrayList;
import java.util.List;

public class FullInventoryActivity extends AppCompatActivity {

  private TextView headwearTitle;
  private LinearLayout headwearSelector;
  private ImageView headwearItem;
  private ImageButton headwearLeft;
  private ImageButton headwearRight;
  private int currentHeadwearPosition;
  //
  private TextView topTitle;
  private LinearLayout topSelector;
  private ImageView topItem;
  private ImageButton topLeft;
  private ImageButton topRight;
  private int currentTopPosition;
  //
  private TextView bottomTitle;
  private LinearLayout bottomSelector;
  private ImageView bottomItem;
  private ImageButton bottomLeft;
  private ImageButton bottomRight;
  private int currentBottomPosition;
  //
  private TextView footwearTitle;
  private LinearLayout footwearSelector;
  private ImageView footwearItem;
  private ImageButton footwearLeft;
  private ImageButton footwearRight;
  private int currentFootwearPosition;
  //
  private FullInventory inventory;
  private List<LinearLayout> allSelectors;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_full_inventory);
    getSupportActionBar().setTitle(R.string.inventory);

    WeatherRetriever weatherRetriever = new WeatherRetriever(this);
    weatherRetriever.execute(Settings.ZIP_CODE);

    inventory = new FullInventory();
    allSelectors = new ArrayList<>();

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    LayoutInflater inflater = LayoutInflater.from(FullInventoryActivity.this);
    View settingsDialog = inflater.inflate(R.layout.customize_clothes, null);

    builder.setView(R.layout.customize_clothes)
            .setTitle(R.string.customize_item)
            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
              }
            })
            .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {

              }
            });

    InitializeHeadwearSection(builder.create(), settingsDialog);
    InitializeTopSection(builder.create(), settingsDialog);
    InitializeBottomSection(builder.create(), settingsDialog);
    InitializeFootwearSection(builder.create(), settingsDialog);
  }

  private void InitializeHeadwearSection(final AlertDialog dialog, View settingsDialog){
    final ImageView dialogImage = (ImageView)settingsDialog.findViewById(R.id.custom_clothes_image);
    currentHeadwearPosition = 0;
    headwearSelector = (LinearLayout)findViewById(R.id.headwear_selector);
    allSelectors.add(headwearSelector);
    headwearItem = (ImageView)findViewById(R.id.headwear_item);

    headwearTitle = (TextView)findViewById(R.id.collapse_headwear);
    headwearTitle.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (headwearSelector.getVisibility() != View.VISIBLE) {
          headwearSelector.setVisibility(View.VISIBLE);
        }
        else{
          headwearSelector.setVisibility(View.GONE);
        }
        CloseOtherSelectors(headwearSelector);
        if(headwearItem.getDrawable() == null){
          headwearItem.setImageDrawable(inventory.GetHeadwearImage(getApplicationContext(), inventory.HEADWEAR_TYPES.get(0)));
        }
      }

    });

    headwearRight = (ImageButton)findViewById(R.id.headwear_right);
    headwearRight.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (currentHeadwearPosition < inventory.HEADWEAR_TYPES.size() - 1){
          currentHeadwearPosition++;
        }
        else{
          currentHeadwearPosition = 0;
        }
        headwearItem.setImageDrawable(inventory.GetHeadwearImage(getApplicationContext(), inventory.HEADWEAR_TYPES.get(currentHeadwearPosition)));
      }
    });

    headwearLeft = (ImageButton)findViewById(R.id.headwear_left);
    headwearLeft.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (currentHeadwearPosition > 0){
          currentHeadwearPosition--;
        }
        else{
          currentHeadwearPosition = inventory.HEADWEAR_TYPES.size() - 1;
        }
        headwearItem.setImageDrawable(inventory.GetHeadwearImage(getApplicationContext(), inventory.HEADWEAR_TYPES.get(currentHeadwearPosition)));
      }
    });

    headwearItem.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dialogImage.setImageDrawable(((ImageView)v).getDrawable());
        dialog.show();
      }
    });
  }

  private void InitializeTopSection(final AlertDialog dialog, View settingsDialog){
    final ImageView dialogImage = (ImageView)settingsDialog.findViewById(R.id.custom_clothes_image);
    currentTopPosition = 0;
    topSelector = (LinearLayout)findViewById(R.id.top_selector);
    allSelectors.add(topSelector);
    topItem = (ImageView)findViewById(R.id.top_item);

    topTitle = (TextView)findViewById(R.id.collapse_top);
    topTitle.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (topSelector.getVisibility() != View.VISIBLE) {
          topSelector.setVisibility(View.VISIBLE);
        }
        else{
          topSelector.setVisibility(View.GONE);
        }
        CloseOtherSelectors(topSelector);
        if(topItem.getDrawable() == null){
          topItem.setImageDrawable(inventory.GetTopImage(getApplicationContext(), inventory.TOP_TYPES.get(0)));
        }
      }

    });

    topRight = (ImageButton)findViewById(R.id.top_right);
    topRight.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (currentTopPosition < inventory.TOP_TYPES.size() - 1){
          currentTopPosition++;
        }
        else{
          currentTopPosition = 0;
        }
        topItem.setImageDrawable(inventory.GetTopImage(getApplicationContext(), inventory.TOP_TYPES.get(currentTopPosition)));
      }
    });

    topLeft = (ImageButton)findViewById(R.id.top_left);
    topLeft.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (currentTopPosition > 0){
          currentTopPosition--;
        }
        else{
          currentTopPosition = inventory.TOP_TYPES.size() - 1;
        }
        topItem.setImageDrawable(inventory.GetTopImage(getApplicationContext(), inventory.TOP_TYPES.get(currentTopPosition)));
      }
    });

    topItem.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dialogImage.setImageDrawable(((ImageView)v).getDrawable());
        dialog.show();
      }
    });
  }

  private void InitializeBottomSection(final AlertDialog dialog, View settingsDialog){
    final ImageView dialogImage = (ImageView)settingsDialog.findViewById(R.id.custom_clothes_image);
    currentBottomPosition = 0;
    bottomSelector = (LinearLayout)findViewById(R.id.bottom_selector);
    allSelectors.add(bottomSelector);
    bottomItem = (ImageView)findViewById(R.id.bottom_item);

    bottomTitle = (TextView)findViewById(R.id.collapse_bottom);
    bottomTitle.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (bottomSelector.getVisibility() != View.VISIBLE) {
          bottomSelector.setVisibility(View.VISIBLE);
        }
        else{
          bottomSelector.setVisibility(View.GONE);
        }
        CloseOtherSelectors(bottomSelector);
        if(bottomItem.getDrawable() == null){
          bottomItem.setImageDrawable(inventory.GetBottomImage(getApplicationContext(), inventory.BOTTOM_TYPES.get(0)));
        }
      }

    });

    bottomRight = (ImageButton)findViewById(R.id.bottom_right);
    bottomRight.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (currentBottomPosition < inventory.BOTTOM_TYPES.size() - 1){
          currentBottomPosition++;
        }
        else{
          currentBottomPosition = 0;
        }
        bottomItem.setImageDrawable(inventory.GetBottomImage(getApplicationContext(), inventory.BOTTOM_TYPES.get(currentBottomPosition)));
      }
    });

    bottomLeft = (ImageButton)findViewById(R.id.bottom_left);
    bottomLeft.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (currentBottomPosition > 0){
          currentBottomPosition--;
        }
        else{
          currentBottomPosition = inventory.BOTTOM_TYPES.size() - 1;
        }
        bottomItem.setImageDrawable(inventory.GetBottomImage(getApplicationContext(), inventory.BOTTOM_TYPES.get(currentBottomPosition)));
      }
    });

    bottomItem.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dialogImage.setImageDrawable(((ImageView)v).getDrawable());
        dialog.show();
      }
    });
  }

  private void InitializeFootwearSection(final AlertDialog dialog, View settingsDialog){
    final ImageView dialogImage = (ImageView)settingsDialog.findViewById(R.id.custom_clothes_image);
    currentFootwearPosition = 0;
    footwearSelector = (LinearLayout)findViewById(R.id.footwear_selector);
    allSelectors.add(footwearSelector);
    footwearItem = (ImageView)findViewById(R.id.footwear_item);

    footwearTitle = (TextView)findViewById(R.id.collapse_footwear);
    footwearTitle.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (footwearSelector.getVisibility() != View.VISIBLE) {
          footwearSelector.setVisibility(View.VISIBLE);
        }
        else{
          footwearSelector.setVisibility(View.GONE);
        }
        CloseOtherSelectors(footwearSelector);
        if(footwearItem.getDrawable() == null){
          footwearItem.setImageDrawable(inventory.GetFootwearImage(getApplicationContext(), inventory.FOOTWEAR_TYPES.get(0)));
        }
      }

    });

    footwearRight = (ImageButton)findViewById(R.id.footwear_right);
    footwearRight.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (currentFootwearPosition < inventory.FOOTWEAR_TYPES.size() - 1){
          currentFootwearPosition++;
        }
        else{
          currentFootwearPosition = 0;
        }
        footwearItem.setImageDrawable(inventory.GetFootwearImage(getApplicationContext(), inventory.FOOTWEAR_TYPES.get(currentFootwearPosition)));
      }
    });

    footwearLeft = (ImageButton)findViewById(R.id.footwear_left);
    footwearLeft.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (currentFootwearPosition > 0){
          currentFootwearPosition--;
        }
        else{
          currentFootwearPosition = inventory.FOOTWEAR_TYPES.size() - 1;
        }
        footwearItem.setImageDrawable(inventory.GetFootwearImage(getApplicationContext(), inventory.FOOTWEAR_TYPES.get(currentFootwearPosition)));
      }
    });

    footwearItem.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dialogImage.setImageDrawable(((ImageView)v).getDrawable());
        dialog.show();
      }
    });
  }

  private void CloseOtherSelectors(LinearLayout dontClose) {
    for(LinearLayout selector : allSelectors){
      if (!selector.equals(dontClose)){
        selector.setVisibility(View.GONE);
      }
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    boolean selected;
    Intent intent;

    switch (item.getItemId()) {
      case R.id.menu_home:
        intent = new Intent(FullInventoryActivity.this, MainActivity.class);
        startActivity(intent);
        selected = true;
        break;
      case R.id.menu_settings:
        intent = new Intent(FullInventoryActivity.this, SettingsActivity.class);
        startActivity(intent);
        selected = true;
        break;
      default:
        selected = super.onOptionsItemSelected(item);
    }

    return selected;
  }
}
