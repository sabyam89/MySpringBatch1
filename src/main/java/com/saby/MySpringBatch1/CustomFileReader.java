package com.saby.MySpringBatch1;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;

import com.saby.MySpringBatch1.Entity.Hospital;

public class CustomFileReader implements
ResourceAwareItemReaderItemStream<Object> {

	private String curItem = null;



	private ResourceAwareItemReaderItemStream<Object> delegate;

	public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (curItem == null) {
			String[] fieldSet = (String[]) delegate.read();
			curItem = fieldSet[0];

		}
		int count=0;
		StringBuilder item = new StringBuilder(curItem);
		count = StringUtils.countMatches(curItem, "$");
		curItem = null;

		while(count<=3) {
			peek();
			count+= StringUtils.countMatches(curItem, "$");
			if (count<=3) {
				item.append(curItem);
				curItem = null;
			}
		}
		Hospital record= new Hospital();
		String[] values = StringUtils.split(item.toString(),"$");
		record.setHospitalName(values[0]);
		record.setAddress(values[1]);
		record.setCity(values[2]);
		record.setNetwork(values[3]);
		return record;
	}

	public Object peek() throws Exception, UnexpectedInputException,
	ParseException {
		if (curItem == null) {
			String[] fieldSet = (String[]) delegate.read();
			if(null!=fieldSet){
				curItem = fieldSet[0];
			}
		}
		return curItem;
	}

	public void setDelegate(ResourceAwareItemReaderItemStream<Object> delegate) {
		this.delegate = delegate;
	}

	public void close() throws ItemStreamException {
		delegate.close();
	}

	public void open(ExecutionContext arg0) throws ItemStreamException {
		delegate.open(arg0);
	}

	public void update(ExecutionContext arg0) throws ItemStreamException {
		delegate.update(arg0);
	}

	public void setResource(Resource arg0) {
		delegate.setResource(arg0);
	}

}
