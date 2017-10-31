package com.zyj.service;

import com.zyj.entity.Sequence;

public interface SequenceService {
	public Sequence getSeqByValue(String value);
	public Sequence getSeqByKeyAndType(String key, String type);
	public Sequence getSequenceById(int id);
}
