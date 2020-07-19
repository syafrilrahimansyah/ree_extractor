package com.tselree.extractor.DAO;

import java.util.List;

import com.tselree.extractor.model.XpathConfig;

public interface CheckGroupDAO {
	public List<XpathConfig> checkGroup(String type);
}
