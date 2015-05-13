package com.bicrement.jaml.tag;

import java.util.List;

import com.bicrement.jaml.content.PreparedDom;

public interface Tag {

    public String getName();
    
    public List<Attribute> getAttributes();

    public List<Tag> getChildElements();
    
    /**
     * Get the DOM content of this tag, including its children
     * 
     * @return this {@link Tag} content
     */
    public String getContent();

    /**
     * Get the DOM content of this tag, written into the StringBuilder
     * 
     * @param sb
     * @return {@link StringBuilder} sb
     */
    public StringBuilder getContent(StringBuilder sb);

    /**
     * Get the prepared DOM content that is ready for binding values
     * 
     * @return {@link PreparedDom}
     */
    public PreparedDom prepare();

	public Text persist();

}
