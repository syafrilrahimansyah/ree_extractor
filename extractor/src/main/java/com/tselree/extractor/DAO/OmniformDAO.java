package com.tselree.extractor.DAO;

public interface OmniformDAO {
	public String getMinID();
	public String getPayload(String id);
	public void updateStage(String id);
}
