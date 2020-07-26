package com.tselree.extractor;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.relational.core.sql.IsNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tselree.extractor.DAO.CheckGroupDAO;
import com.tselree.extractor.DAO.EntityHTSDAO;
import com.tselree.extractor.DAO.EntityVTSDAO;
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
	@Autowired
	private EntityVTSDAO entityVTSDAO;
	
	@Scheduled(fixedRate = 5000)
	public void execute() throws Exception {
		String seq = omniformDAO.getMinID();
		if(seq != null) {
			String payload = omniformDAO.getPayload(seq);
			String type = new XCheck().exec(payload).getType();
			String entity_id = new XCheck().exec(payload).getEntityID();
			
			List<XpathConfig> xgroup = checkGroupDAO.checkGroup(type);
			for(XpathConfig xpathConfig : xgroup) {
				String key_val = new XKeyVal().exec(payload, xpathConfig.getKey_path());
				List<XpathList> xpathList = xpathListDAO.xpathList(xpathConfig.getXgroup());
				if(xpathConfig.getTb_structure().equals("hts")) {
					entityHTSDAO.insert_key(xpathConfig.getEntity_table(), xpathConfig.getColumn_key(), key_val);
					
					for(XpathList xpathSrc : xpathList) {
						System.out.println("extracting: "+xpathSrc.getColumn());
						String value = new XExtract().execute(payload, xpathSrc.getPath(), xpathSrc.getMultiplevalue());
						entityHTSDAO.insert(xpathConfig.getEntity_table(), xpathConfig.getColumn_key(), key_val, xpathSrc.getColumn(), value);
					}
				}else {
					entityVTSDAO.del_existing(xpathConfig.getEntity_table(), key_val);
					for(XpathList xpathSrc : xpathList) {
						System.out.println("extracting: "+xpathSrc.getColumn());
						String value = new XExtract().execute(payload, xpathSrc.getPath(), xpathSrc.getMultiplevalue());
						entityVTSDAO.insert(xpathConfig.getEntity_table(), key_val, xpathSrc.getColumn(), value);
					}
				}
			}
			omniformDAO.updateStage(seq);

			System.out.println("Extraction for entity id:"+entity_id+" already finished!");
		}else {
			System.out.println("New omniform not found");
		}
		
		
	}
}
