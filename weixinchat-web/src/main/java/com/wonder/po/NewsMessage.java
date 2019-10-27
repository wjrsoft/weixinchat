package com.wonder.po;

import java.util.List;
/**
 * 图文消息封装体
 *
 */
public class NewsMessage extends BaseMessage{
	/**
	 * 图文消息条数
	 */
	private int ArticleCount;
	/**
	 * 将每个图文消息放到List集合中
	 */
	private List<News> Articles;
	
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<News> getArticles() {
		return Articles;
	}
	public void setArticles(List<News> articles) {
		Articles = articles;
	}
}
