package com.app.inventory.entity;

import java.io.Serializable;
import java.util.Properties;


import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.type.Type;


public class SequenceIdGenrerator extends SequenceStyleGenerator {
	 public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";
	    public static final String VALUE_PREFIX_DEFAULT = "";
	    private String valuePrefix;
	 
	    public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
	    public static final String NUMBER_FORMAT_DEFAULT = "%d";
	    private String numberFormat;
	 
	    @Override
	    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
	    	// TODO Auto-generated method stub
	    	 return valuePrefix + String.format(numberFormat, super.generate(session, object));
	    	 
	    }
	 
	    @Override
	    public void configure(Type type, Properties params, org.hibernate.service.ServiceRegistry serviceRegistry)
	    		throws org.hibernate.MappingException {
	    	// TODO Auto-generated method stub
	    	super.configure(type, params, serviceRegistry);
	    	valuePrefix = ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER,
	                params, VALUE_PREFIX_DEFAULT);
	        numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER,
	                params, NUMBER_FORMAT_DEFAULT);
	    }
	
}
