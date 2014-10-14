package me.bruce.oschina.ui;

import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ListView;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import me.bruce.oschina.BootstrapServiceProvider;
import me.bruce.oschina.Injector;
import me.bruce.oschina.R;
import me.bruce.oschina.authenticator.LogoutService;
import me.bruce.oschina.core.News;
import me.bruce.oschina.core.NewsWrapper;
import me.bruce.oschina.ui.cards.NewsCard;

import com.github.kevinsawicki.wishlist.SingleTypeAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import static me.bruce.oschina.core.Constants.Extra.NEWS_ITEM;

public class NewsListFragment extends CardListFragment {

    @Inject protected BootstrapServiceProvider serviceProvider;
    @Inject protected LogoutService logoutService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.inject(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setEmptyText(R.string.no_news);
    }

    @Override
    protected void configureList(Activity activity, ListView listView) {
        super.configureList(activity, listView);

        listView.setFastScrollEnabled(true);
        listView.setDividerHeight(0);
    }

    @Override
    protected LogoutService getLogoutService() {
        return logoutService;
    }

    @Override
    public void onDestroyView() {
        setListAdapter(null);

        super.onDestroyView();
    }

    @Override
    public Loader<List<Card>> onCreateLoader(int id, Bundle args) {
        final List<Card> initialItems = items;
        return new ThrowableLoader<List<Card>>(getActivity(), items) {

            @Override
            public List<Card> loadData() throws Exception {
                try {
                    if (getActivity() != null) {
                        List<News> newsList = serviceProvider.getService(getActivity()).getNews();
                        final List<Card> cards = new ArrayList<>();
                        for (News news : newsList) {
                            NewsCard card = new NewsCard(getContext());
                            card.setTitle(news.getTitle());
                            card.setRating(0.5f);
                            card.setSecondaryTitle(news.getAuthor());
                            cards.add(card);
                        }
                        return cards;
                    } else {
                        return Collections.emptyList();
                    }

                } catch (OperationCanceledException e) {
                    Activity activity = getActivity();
                    if (activity != null)
                        activity.finish();
                    return initialItems;
                }
            }
        };
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        News news = ((News) l.getItemAtPosition(position));

        startActivity(new Intent(getActivity(), NewsActivity.class).putExtra(NEWS_ITEM, news));
    }

    @Override
    protected int getErrorMessage(Exception exception) {
        return R.string.error_loading_news;
    }
}
