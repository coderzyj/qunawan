package com.zyj.serviceImp;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.zyj.dao.SequenceDao;
import com.zyj.entity.Sequence;
import com.zyj.service.SequenceService;
@Service
@Transactional
public class SequenceServiceImp implements SequenceService{
	
	private SequenceDao dao;
	@Resource(name="sequenceDaoImp")
	public void setDao(SequenceDao dao) {
		this.dao = dao;
	}

	@Override
	public Sequence getSeqByValue(String value) {
		// TODO Auto-generated method stub
		return dao.getSeqByValue(value);
	}

	@Override
	public Sequence getSeqByKeyAndType(String key, String type) {
		// TODO Auto-generated method stub
		return dao.getSeqByKeyAndType(key, type);
	}

	@Override
	public Sequence getSequenceById(int id) {
		// TODO Auto-generated method stub
		return dao.getSequenceById(id);
	}

}
