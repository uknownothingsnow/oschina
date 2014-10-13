package me.bruce.oschina.ui.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import me.bruce.oschina.R;

public class NewsCard extends Card {

    protected TextView mTitle;
    protected TextView mSecondaryTitle;
    protected RatingBar mRatingBar;
    protected int resourceIdThumbnail;
    protected int count;

    protected String title;
    protected String secondaryTitle;
    protected float rating;


    public NewsCard(Context context) {
        this(context, R.layout.card_layout);
    }

    public NewsCard(Context context, int innerLayout) {
        super(context, innerLayout);
        //init();
    }

    public void init() {

        //Add thumbnail
        CardThumbnail cardThumbnail = new CardThumbnail(mContext);

        if (resourceIdThumbnail==0)
            cardThumbnail.setDrawableResource(R.drawable.ic_std_launcher);
        else{
            cardThumbnail.setDrawableResource(resourceIdThumbnail);
        }

        addCardThumbnail(cardThumbnail);

        //Only for test, some cards have different clickListeners
        if (count==12){

            setTitle(title + " No Click");
            setClickable(false);

        }else if (count==20){

            setTitle(title + " Partial Click");
            addPartialOnClickListener(Card.CLICK_LISTENER_CONTENT_VIEW, (card, view) -> Toast.makeText(getContext(), "Partial click Listener card=" + title, Toast.LENGTH_SHORT).show());

        }else{

            //Add ClickListener
            setOnClickListener((card, view) -> Toast.makeText(getContext(), "Click Listener card=" + title, Toast.LENGTH_SHORT).show());

        }


        //Swipe
        if (count>5 && count<13){

            setTitle(title + " Swipe enabled");
            setSwipeable(true);
            setOnSwipeListener(card -> Toast.makeText(getContext(), "Removed card=" + title, Toast.LENGTH_SHORT).show());
        }

    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        //Retrieve elements
        mTitle = (TextView) parent.findViewById(R.id.carddemo_myapps_main_inner_title);
        mSecondaryTitle = (TextView) parent.findViewById(R.id.carddemo_myapps_main_inner_secondaryTitle);
        mRatingBar = (RatingBar) parent.findViewById(R.id.carddemo_myapps_main_inner_ratingBar);

        if (mTitle != null)
            mTitle.setText(title);

        if (mSecondaryTitle != null)
            mSecondaryTitle.setText(secondaryTitle);

        if (mRatingBar != null) {
            mRatingBar.setNumStars(5);
            mRatingBar.setMax(5);
            mRatingBar.setStepSize(0.5f);
            mRatingBar.setRating(rating);
        }

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSecondaryTitle() {
        return secondaryTitle;
    }

    public void setSecondaryTitle(String secondaryTitle) {
        this.secondaryTitle = secondaryTitle;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getResourceIdThumbnail() {
        return resourceIdThumbnail;
    }

    public void setResourceIdThumbnail(int resourceIdThumbnail) {
        this.resourceIdThumbnail = resourceIdThumbnail;
    }
}