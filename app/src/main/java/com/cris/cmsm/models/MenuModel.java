package com.cris.cmsm.models;

/**
 * Created by Anand.kumar on 10/24/2016.
 */
public class MenuModel {

    int icon;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }


    String menuTitle;

    public int getCardColor() {
        return cardColor;
    }

    public void setCardColor(int cardColor) {
        this.cardColor = cardColor;
    }

    int cardColor;

    public MenuModel(int cardColor, int icon, String menuTitle) {
        this.cardColor = cardColor;
        this.icon = icon;
        this.menuTitle = menuTitle;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    String URL;

    public MenuModel(){

    }
}
