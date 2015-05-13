package com.bicrement.jaml.content;

import java.util.List;

import com.bicrement.jaml.tag.Text;

public class BoundedDom {
	
    private final PreparedDom preparedDom;
    private final List<Object> values;

    BoundedDom(PreparedDom preparedDom, List<Object> values) {
        this.preparedDom = preparedDom;
        this.values = values;
    }
    
    public String getContent() {
        return getContent(new StringBuilder()).toString();
    }
    
    @Override
    public String toString() {
    	return getContent();
    }

    public StringBuilder getContent(StringBuilder sb) {
        int valueIdx = 0;
        for (Object partial : preparedDom.getPreparedPartials()) {
        	if (partial instanceof BindMarker) {
        		sb.append(values.get(valueIdx) == null ? "" : values.get(valueIdx));
        		valueIdx += 1;
        	} else {
        		sb.append(partial.toString());
        	}
        }

        return sb;
    }
    
    public Text toHtml() {
    	return new Text(getContent());
    }

}
