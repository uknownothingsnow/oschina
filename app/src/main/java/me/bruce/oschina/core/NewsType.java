package me.bruce.oschina.core;

import org.simpleframework.xml.Element;

/**
 * Created by bruce on 10/10/14.
 */
public class NewsType {
    @Element
    private int type;

    @Element(required = false)
    private String attachment;

    @Element(name = "authoruid2")
    private long authorUid;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public long getAuthorUid() {
        return authorUid;
    }

    public void setAuthorUid(long authorUid) {
        this.authorUid = authorUid;
    }
}
