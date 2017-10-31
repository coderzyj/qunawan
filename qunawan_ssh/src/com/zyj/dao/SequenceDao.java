package com.zyj.dao;

import com.zyj.entity.Sequence;



public interface SequenceDao {
	public Sequence getSeqByValue(String value);
	public Sequence getSeqByKeyAndType(String key, String type);
	public Sequence getSequenceById(int id);
}
