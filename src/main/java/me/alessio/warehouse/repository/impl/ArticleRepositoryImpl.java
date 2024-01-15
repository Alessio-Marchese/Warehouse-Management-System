package me.alessio.warehouse.repository.impl;

import me.alessio.warehouse.model.Article;
import me.alessio.warehouse.repository.ArticleRepository;

public class ArticleRepositoryImpl extends CrudRepositoryImpl<Article,Integer> implements ArticleRepository{
	
	private static ArticleRepositoryImpl instance = new ArticleRepositoryImpl();

	private ArticleRepositoryImpl() {
		super(Article.class);
	}

	public static ArticleRepositoryImpl getInstance() {
		return instance;
	}
}
