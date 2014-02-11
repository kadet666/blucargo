package com.blusoft.blucargo.model;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum OfferType implements Serializable, IsSerializable {

	CARGO(1, "CARGO"), VEHICLE(10, "VEHICLE");

	private final String typeName;
	private final Integer typeId;

	OfferType() {
		typeName = null;
		typeId = 0;
	}

	OfferType(int typeId, String typeName) {
		this.typeId = typeId;
		this.typeName = typeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public int getTypeId() {
		return typeId;
	}

}