package com.tommy.taglib;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.Writer;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
 
public class Paginator extends SimpleTagSupport {
    private String uri;
    private int currPage;
    private int totalPages;
    private int maxLinks = 10;
    private int size;
 
    private Writer getWriter() {
        JspWriter out = getJspContext().getOut();
        return out;
    }
 
    @Override
    public void doTag() throws JspException {
        Writer out = getWriter();
 
        boolean lastPage = (currPage == totalPages);
        int pgStart = Math.max(currPage - maxLinks / 2, 1);
        int pgEnd = pgStart + maxLinks;
        if (pgEnd > totalPages + 1) {
            int diff = pgEnd - totalPages;
            pgStart -= diff - 1;
            if (pgStart < 1)
                pgStart = 1;
            pgEnd = totalPages + 1;
            System.out.println("totalPages="+totalPages);
            System.out.println("pgEnd="+pgEnd);
        }
 
        try {
            out.write("<ul class=\"pagination pagination-sm\">");
 
            if (currPage > 1)
                out.write(constructLink(currPage - 1, "Previous", "paginatorPrev"));
 
            for (int i = pgStart; i <pgEnd; i++) {
               
                    out.write(constructLink(i));
            }
 
            if (!lastPage)
                out.write(constructLink(currPage + 1, "Next", "paginatorNext paginatorLast"));
 
            out.write("</ul>");
 
        } catch (java.io.IOException ex) {
            throw new JspException("Error in Paginator tag", ex);
        }
    }
 
    private String constructLink(int page) {
        return constructLink(page, String.valueOf(page), null);
    }
 
    private String constructLink(int page, String text, String className) {
        StringBuilder link = new StringBuilder("<li");
        if (className != null) {
            link.append(" class=\"");
            link.append(className);
            link.append("\"");
        }
        if(size<=0){
        	size=10;
        }
        link.append(">")
            .append("<a href=\"")
            .append(uri.replace("page=##", "page="+String.valueOf(page)).replace("size=##", "size="+String.valueOf(size)))
            .append("\">")
            .append(text)
            .append("</a></li>");
        return link.toString();
    }
 

	public void setSize(int size) {
		this.size = size;
	}

	public void setUri(String uri) {
        this.uri = uri;
    }
 
    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }
 
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
 
    public void setMaxLinks(int maxLinks) {
        this.maxLinks = maxLinks;
    }
}
