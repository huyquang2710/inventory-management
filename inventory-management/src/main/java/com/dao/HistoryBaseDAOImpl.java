package com.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.History;

@Repository
@Transactional(rollbackFor = Exception.class)
public class HistoryBaseDAOImpl extends BaseDAOImpl<History> implements HistoryDAO<History> {

}
