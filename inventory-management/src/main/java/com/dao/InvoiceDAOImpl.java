package com.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.Invoice;

@Repository
@Transactional(rollbackFor = Exception.class) // rollback khi ex xay ra
public class InvoiceDAOImpl extends BaseDAOImpl<Invoice> implements InvoiceDAO<Invoice> {

}
