package com.tselree.extractor;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tselree.extractor.DAO.CheckGroupDAO;
import com.tselree.extractor.DAO.EntityHTSDAO;
import com.tselree.extractor.DAO.OmniformDAO;
import com.tselree.extractor.DAO.XpathListDAO;
import com.tselree.extractor.model.XpathConfig;
import com.tselree.extractor.model.XpathList;
@Component
public class ExtractTasks {
	@Autowired
	private OmniformDAO omniformDAO;
	@Autowired
	private CheckGroupDAO checkGroupDAO;
	@Autowired
	private XpathListDAO xpathListDAO;
	@Autowired
	private EntityHTSDAO entityHTSDAO;
	@Scheduled(fixedRate = 5000)
	public void execute() throws Exception {
		String sec = omniformDAO.getMinID();
		String payload = omniformDAO.getPayload(sec);
		String type = new XCheckType().exec(payload).getType();
		String key_val = new XCheckType().exec(payload).getBusinessID();
		XpathConfig xgroup = checkGroupDAO.checkGroup(type);
		List<XpathList> xpathList = xpathListDAO.xpathList(xgroup.getXgroup());
		if(xgroup.getTb_structure().equals("hts")) {
			entityHTSDAO.insert_key(xgroup.getEntity_table(), xgroup.getColumn_key(), key_val);
			//
			
			//
			for(XpathList xpathSrc : xpathList) {
				System.out.println("extracting: "+xpathSrc.getColumn());
				String value = new XExtract().execute(payload, xpathSrc.getPath());
				entityHTSDAO.insert(xgroup.getEntity_table(), xgroup.getColumn_key(), key_val, xpathSrc.getColumn(), value);
			}
		}
		
		//
		//new XExtract().execute(xgroup.getEntity_table(), xgroup.getColumn_key(), key_val, payload, xgroup.getTb_structure(), xpathList);
		
		
	}
}
