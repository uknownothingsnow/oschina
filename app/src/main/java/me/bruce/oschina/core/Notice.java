package me.bruce.oschina.core;

import org.simpleframework.xml.Element;

/**
 * Created by bruce on 10/10/14.
 */
public class Notice {
    @Element
    private int atmeCount;

    @Element
    private int msgCount;

    @Element
    private int reviewCount;

    @Element
    private int newFansCount;

    public int getAtmeCount() {
        return atmeCount;
    }

    public void setAtmeCount(int atmeCount) {
        this.atmeCount = atmeCount;
    }

    public int getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(int msgCount) {
        this.msgCount = msgCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public int getNewFansCount() {
        return newFansCount;
    }

    public void setNewFansCount(int newFansCount) {
        this.newFansCount = newFansCount;
    }
}
