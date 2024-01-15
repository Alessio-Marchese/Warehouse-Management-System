package me.alessio.warehouse.repository.impl;

import me.alessio.warehouse.model.Supplier;
import me.alessio.warehouse.repository.SupplierRepository;

public class SupplierRepositoryImpl extends CrudRepositoryImpl<Supplier,Integer> implements SupplierRepository{

	private static SupplierRepositoryImpl instance = new SupplierRepositoryImpl();

	private SupplierRepositoryImpl() {
		super(Supplier.class);
	}

	public static SupplierRepositoryImpl getInstance() {
		return instance;
	}
}
