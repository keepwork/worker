package com.shop.good.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyTree implements Serializable
{
    private static final long serialVersionUID = -5708098691444512697L;
    
    private List trees;
    
    private String id;
    
    private String pid;
    
    private String name;
    
    private String url;
    
    private String title;
    
    private String target;
    
    private String icon;
    
    private String iconOpen;
    
    private boolean open;
    
    private String identity;
    
    private int status1;
    
    private String type;
    
    public MyTree(String id, String pid, String name, String url, String title, String target, String icon,
        String iconOpen, boolean open, String identity, int status1, String type)
    {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.url = url;
        this.title = title;
        this.target = target;
        this.icon = icon;
        this.iconOpen = iconOpen;
        this.open = open;
        this.identity = identity;
        this.status1 = status1;
        this.type = type;
    }
    
    public MyTree()
    {
        
    }
    
    /**
     * @return the icon
     */
    
    public String getIcon()
    {
        return icon;
    }
    
    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon)
    {
        this.icon = icon;
    }
    
    /**
     * @return the iconOpen
     */
    public String getIconOpen()
    {
        return iconOpen;
    }
    
    /**
     * @param iconOpen the iconOpen to set
     */
    public void setIconOpen(String iconOpen)
    {
        this.iconOpen = iconOpen;
    }
    
    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }
    
    /**
     * @return the identity
     */
    public String getIdentity()
    {
        return identity;
    }
    
    /**
     * @param identity the identity to set
     */
    public void setIdentity(String identity)
    {
        this.identity = identity;
    }
    
    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * @return the open
     */
    public boolean isOpen()
    {
        return open;
    }
    
    /**
     * @param open the open to set
     */
    public void setOpen(boolean open)
    {
        this.open = open;
    }
    
    /**
     * @return the pid
     */
    public String getPid()
    {
        return pid;
    }
    
    /**
     * @param pid the pid to set
     */
    public void setPid(String pid)
    {
        this.pid = pid;
    }
    
    /**
     * @return the status1
     */
    public int getStatus1()
    {
        return status1;
    }
    
    /**
     * @param status1 the status1 to set
     */
    public void setStatus1(int status1)
    {
        this.status1 = status1;
    }
    
    /**
     * @return the target
     */
    public String getTarget()
    {
        return target;
    }
    
    /**
     * @param target the target to set
     */
    public void setTarget(String target)
    {
        this.target = target;
    }
    
    /**
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * @param title the title to set
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    /**
     * @return the trees
     */
    public List getTrees()
    {
        if (trees == null)
            trees = new ArrayList();
        return trees;
    }
    
    /**
     * @param trees the trees to set
     */
    public void setTrees(List trees)
    {
        this.trees = trees;
    }
    
    /**
     * @return the url
     */
    public String getUrl()
    {
        return url;
    }
    
    /**
     * @param url the url to set
     */
    public void setUrl(String url)
    {
        this.url = url;
    }
    
    /**
     * @return the type
     */
    public String getType()
    {
        return type;
    }
    
    /**
     * @param type the type to set
     */
    public void setType(String type)
    {
        this.type = type;
    }
    
}
