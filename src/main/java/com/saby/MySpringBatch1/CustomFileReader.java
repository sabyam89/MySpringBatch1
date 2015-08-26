package com.saby.MySpringBatch1;

import java.util.ArrayList;

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

    private Object curItem = null;
    
    

    private ResourceAwareItemReaderItemStream<Object> delegate;
    
    public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		 if (curItem == null) {
	            curItem = (String) delegate.read();
	        }
		 	int count=0;
		 	StringBuilder item = (StringBuilder) curItem;
		 	count = curItem.toString().split("$").length-1;
	        curItem = null;

	        while(count<=3) {
	        	peek();
	        	count+= curItem.toString().split("$").length-1;
	            if (count<=3) {
	            	item.append(curItem);
	            }
	            curItem = null;
	        }

	        return item;
	}

    public Object peek() throws Exception, UnexpectedInputException,
            ParseException {
        if (curItem == null) {
            curItem = delegate.read();
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
