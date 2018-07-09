package com.shop.good.model.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.sinovatech.common.model.dto.DtoSupport;

/**
 * 实体对象，请勿做客户化操作， 对应表:PUB_GOOD 商品
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:39:58 PM
 */
public abstract class AbstractGoodDTO extends DtoSupport
{ 
	private String id;
    private String catCode;// 菜品分类编号
    private String catName;// 菜品分类名称
    private String code;// 商品编码 
    private String name;// 名称
    private String keyWord;// 关键字
    private String spic;// 小图
    private String bpic;// 大图
    private String author;// 顶级分类编号（作者）
    private String press;// 顶级分类名称（出版社）
    private String isbn;// 库存
    private String spec;// 规格
    private String pages;// 页数
    private String bookbinding;// 装订
    private String weight;// 重量
    private String words;// 字数
    private String descr1;//简介
    private String descr2;//详细介绍
    private BigDecimal price;// 零售价
    private BigDecimal priceMarket;// 市场价
    private BigDecimal promotionPrice;// 促销单价-单本
    private BigDecimal priceY;// 进价
    private BigDecimal priceH;// 团购价
    private BigDecimal priceQ;
    private Integer status;// 状态：0-上架1-下架2-缺货
    private Integer orderNum;// 排序
    private Integer totalHits;//点击次数
    private Integer point;//积分价
    private Date updateTime;//修改时间 
   
    private String categoryIds;// 存放红酒类型，多个以,号隔开
    
    private Map thirdCategoryMap;// //存放类型树id
    
	public Map getThirdCategoryMap()
    {
        if (thirdCategoryMap == null)
            thirdCategoryMap = new HashMap();// 不能为空
        return thirdCategoryMap;
    }
    public void setThirdCategoryMap(Map thirdCategoryMap)
    {
        this.thirdCategoryMap = thirdCategoryMap;
    }
    
    public AbstractGoodDTO(String id2) {
		// TODO Auto-generated constructor stub
	}
	public AbstractGoodDTO() {
		// TODO Auto-generated constructor stub
	}
    
    
    
  
	public String getCategoryIds()
    {
        return categoryIds;
    }
    
    public String getCatName()
    {
        return catName;
    }
    
    public void setCatName(String catName)
    {
        this.catName = catName;
    }
    
    public void setCategoryIds(String categoryIds)
    {
        this.categoryIds = categoryIds;
    }
    
  
    
    public BigDecimal getPriceMarket()
    {
        return priceMarket;
    }
    
    public void setPriceMarket(BigDecimal priceMarket)
    {
        this.priceMarket = priceMarket;
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCatCode()
    {
        return catCode;
    }
    
    public void setCatCode(String catCode)
    {
        this.catCode = catCode;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getKeyWord()
    {
        return keyWord;
    }
    
    public void setKeyWord(String keyWord)
    {
        this.keyWord = keyWord;
    }
    
    public String getAuthor()
    {
        return author;
    }
    
    public void setAuthor(String author)
    {
        this.author = author;
    }
    
    public String getPress()
    {
        return press;
    }
    
    public void setPress(String press)
    {
        this.press = press;
    }
    
    public String getIsbn()
    {
        return isbn;
    }
    
    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }
    
    public String getSpec()
    {
        return spec;
    }
    
    public void setSpec(String spec)
    {
        this.spec = spec;
    }
    
    public String getPages()
    {
        return pages;
    }
    
    public void setPages(String pages)
    {
        this.pages = pages;
    }
    
    public String getBookbinding()
    {
        return bookbinding;
    }
    
    public void setBookbinding(String bookbinding)
    {
        this.bookbinding = bookbinding;
    }
    
    public String getWeight()
    {
        return weight;
    }
    
    public void setWeight(String weight)
    {
        this.weight = weight;
    }
    
    public String getWords()
    {
        return words;
    }
    
    public void setWords(String words)
    {
        this.words = words;
    }
    
    public BigDecimal getPrice()
    {
        return price;
    }
    
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }
    
    public BigDecimal getPromotionPrice()
    {
        return promotionPrice;
    }
    
    public void setPromotionPrice(BigDecimal promotionPrice)
    {
        this.promotionPrice = promotionPrice;
    }
    
    public BigDecimal getPriceY()
    {
        return priceY;
    }
    
    public void setPriceY(BigDecimal priceY)
    {
        this.priceY = priceY;
    }
    
    public BigDecimal getPriceH()
    {
        return priceH;
    }
    
    public void setPriceH(BigDecimal priceH)
    {
        this.priceH = priceH;
    }
    
    public BigDecimal getPriceQ()
    {
        return priceQ;
    }
    
    public void setPriceQ(BigDecimal priceQ)
    {
        this.priceQ = priceQ;
    }

	public String getDescr1() {
		return descr1;
	}

	public void setDescr1(String descr1) {
		this.descr1 = descr1;
	}

	public String getDescr2() {
		return descr2;
	}

	public void setDescr2(String descr2) {
		this.descr2 = descr2;
	}
	
	public String getSpic() {
		return spic;
	}
	public void setSpic(String spic) {
		this.spic = spic;
	}
	public String getBpic() {
		return bpic;
	}
	public void setBpic(String bpic) {
		this.bpic = bpic;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getTotalHits() {
		return totalHits;
	}
	public void setTotalHits(Integer totalHits) {
		this.totalHits = totalHits;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}


}