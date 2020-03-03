package yalantis.com.sidemenu.model;

import yalantis.com.sidemenu.interfaces.Resourceble;

/**
 * Created by Konstantin on 23.12.2014.
 */
public class SlideMenuItem implements Resourceble {
    private String name;
    private int imageRes;
    private String frag_name;

    public SlideMenuItem(String name, int imageRes,String frag_name) {
        this.name = name;
        this.imageRes = imageRes;
        this.frag_name=frag_name;
    }

    public String getName() {
        return name;
    }
    public String getfrag_name() {
        return frag_name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setfrag_name(String frag_name) {
        this.frag_name = frag_name;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }
}
