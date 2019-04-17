package ru.kpfu.itis.util;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class DateTag extends SimpleTagSupport {

    private long date;

    public void setDate(long date) {
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    @Override
    public void doTag() throws IOException {
        long millisAgo = System.currentTimeMillis() - date;
        if (millisAgo < 1000 * 60) {
            long seconds = millisAgo / 1000;
            getJspContext().getOut().write(seconds + " seconds ago");
        } else if (millisAgo < 1000 * 60 * 60) {
            long minutes = millisAgo / (1000 * 60);
            getJspContext().getOut().write(minutes + " minutes ago");
        } else if (millisAgo < 1000 * 60 * 60 * 24) {
            long hours = millisAgo / (1000 * 60 * 60);
            getJspContext().getOut().write(hours + " hours ago");
        } else if (millisAgo < 1000L * 60 * 60 * 24 * 30) {
            long days = millisAgo / (1000 * 60 * 60 * 24);
            getJspContext().getOut().write(days + " days ago");
        } else if (millisAgo < 1000L * 60 * 60 * 24 * 365) {
            long months = millisAgo / (1000L * 60 * 60 * 24 * 30);
            getJspContext().getOut().write(months + " months ago");
        } else {
            getJspContext().getOut().write("more than a year ago");
        }
    }
}
