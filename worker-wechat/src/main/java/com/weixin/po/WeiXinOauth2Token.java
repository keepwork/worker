package com.weixin.po;

public class WeiXinOauth2Token {

	// 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同 
	private String accessToken;

	// access_token接口调用凭证超时时间，单位（秒） 
	private int expiresIn;

	// 用户刷新access_token 
	private String refeshToken;

	// 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID 
	private String openId;

	// 用户授权的作用域，使用逗号（,）分隔 
	private String scope;
	
	// 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
	private String unionid;

	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getRefeshToken() {
		return refeshToken;
	}

	public void setRefeshToken(String refeshToken) {
		this.refeshToken = refeshToken;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
	
	@Override
	public String toString() {
		return "WeiXinOauth2Token [accessToken=" + accessToken + ", expiresIn=" + expiresIn
		+ ", refeshToken=" + refeshToken + ", openId=" + openId + ", scope=" + scope + ", unionid=" + unionid  + "]";
	}
	
}
