package me.alessio.warehouse.repository.impl;

import me.alessio.warehouse.model.Warehouse;
import me.alessio.warehouse.repository.WarehouseRepository;

public class WarehouseRepositoryImpl extends CrudRepositoryImpl<Warehouse,Integer> implements WarehouseRepository{

	private static WarehouseRepositoryImpl instance = new WarehouseRepositoryImpl();

	private WarehouseRepositoryImpl() {
		super(Warehouse.class);
	}

	public static WarehouseRepositoryImpl getInstance() {
		return instance;
	}
}
