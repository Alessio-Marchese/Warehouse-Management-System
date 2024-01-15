package me.alessio.warehouse.repository.impl;

import me.alessio.warehouse.model.Transaction;
import me.alessio.warehouse.repository.TransactionRepository;

public class TransactionRepositoryImpl extends CrudRepositoryImpl<Transaction, Integer> implements TransactionRepository{

	private static TransactionRepositoryImpl instance = new TransactionRepositoryImpl();

	private TransactionRepositoryImpl() {
		super(Transaction.class);
	}

	public static TransactionRepositoryImpl getInstance() {
		return instance;
	}
}
