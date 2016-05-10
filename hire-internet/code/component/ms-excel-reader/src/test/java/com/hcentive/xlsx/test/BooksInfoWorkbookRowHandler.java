/**
 * 
 */
package com.hcentive.xlsx.test;

import com.hcentive.xlsx.parser.SheetHandler;
import com.hcentive.xlsx.test.poi.objects.Book;
import com.hcentive.xlsx.test.poi.objects.PublisherInfo;

/**
 * @author prashant
 *
 */
public class BooksInfoWorkbookRowHandler implements SheetHandler {

		@Override
		public void processRow(Integer rowNum, Object row) throws Exception {
			
			if(row instanceof Book)
				handlerBook((Book) row);
			else if(row instanceof PublisherInfo)
				handlerPublisherInfo((PublisherInfo) row);
			
		}

	private void handlerBook(Book book)
	{
		// write your own handling for Book objects e.g. DB insertion 
	}
	
	private void handlerPublisherInfo(PublisherInfo pubInfo)
	{
		// write your own handling for PublisherInfo objects e.g. DB insertion 
	}

}
