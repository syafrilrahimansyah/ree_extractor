package com.tselree.extractor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	private static final Logger log = LoggerFactory.getLogger(ExtractTasks.class);
	@Scheduled(fixedRate = 5000)
	public void execute() throws Exception {
		String seq = omniformDAO.getMinID();
		if(seq != null) {
			log.info("New Omniform Found");
			log.info("Fetching Omniform Information");
			String payload = omniformDAO.getPayload(seq);
			String type = new XCheck().exec(payload).getType();
			String entity_id = new XCheck().exec(payload).getEntityID();
			List<XpathConfig> xgroup = checkGroupDAO.checkGroup(type);
			if(xgroup!=null){
				log.info("Processing Entity | Type: "+type+" | ID: "+entity_id);
				for(XpathConfig xpathConfig : xgroup){
					log.info("Preparing Extraction | Table: "+ xpathConfig.getEntity_table());
					String key_val = new XKeyVal().exec(payload, xpathConfig.getKey_path());
					Integer loop = new XLoop().exec(payload, xpathConfig.getLoop_path());
					List<XpathList> xpathList = xpathListDAO.xpathList(xpathConfig.getXgroup());
					if(xpathConfig.getTb_structure().equals("hts")){
						log.info("Starting H-TS Extraction");
						entityHTSDAO.delete_old(xpathConfig.getEntity_table(), xpathConfig.getColumn_key(), key_val);
						for(int x=1;x<=loop;x++) {
							log.info("Extracting: "+xpathConfig.getEntity_table()+" | Index: "+x);
							List<String> listColumn = new ArrayList<>();
							List<String> listValue = new XExtract().execute(payload, xpathList, x, key_val);
							listColumn.add(xpathConfig.getColumn_key());
							for(XpathList xpathSrc : xpathList) {
								listColumn.add(xpathSrc.getColumn());
							}
							entityHTSDAO.insert2(xpathConfig.getEntity_table(), String.join(",", listColumn), String.join(",", listValue), xpathConfig.getColumn_key(), key_val);
						}					
					}else{
						log.info("Starting V-TS Extraction");
						entityVTSDAO.del_existing(xpathConfig.getEntity_table(), key_val);
						Map<String,String> mapColValue = new XExtractVTS().execute(payload, xpathList);
						//OLD
						for(Entry<String,String> entry : mapColValue.entrySet()){
							log.info("Extracting: "+xpathConfig.getEntity_table()+" | Attribute: "+entry.getKey());
							entityVTSDAO.insert(xpathConfig.getEntity_table(), key_val, entry.getKey(), entry.getValue());
						}
					}
				}
				log.info("Extraction Complete | Entity ID: "+entity_id);
			}else{
				log.info("Omniform Type Mismatch!");
			}			
			omniformDAO.updateStage(seq);
		}else{
			log.info("Searching Omniform");
		}
	}
}
