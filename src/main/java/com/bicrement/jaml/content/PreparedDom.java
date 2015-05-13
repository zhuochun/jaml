package com.bicrement.jaml.content;

import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PreparedDom {
	
	// partials can only be String or BindMarker
    private final List<Object> partials;
    // a copy of BindMarkers for faster access
    private final List<BindMarker> markers;

    PreparedDom(List<Object> partials, List<BindMarker> markers) {
        this.partials = Collections.unmodifiableList(partials);
        this.markers = Collections.unmodifiableList(markers);
    }

    public BoundedDom bind(Object... values) {
    	checkState(markers.size() == values.length);
        return new BoundedDom(this, Arrays.asList(values));
    }

    public BoundedDom bind(Map<String, Object> valueMap) {
    	checkState(markers.size() == valueMap.size());

        List<Object> values = new ArrayList<>(valueMap.size());
        for (BindMarker marker : markers) {
        	values.add(valueMap.get(marker.getName()));
        }

        return new BoundedDom(this, values);
    }
    
    List<Object> getPreparedPartials() {
    	return partials;
    }
    
    List<BindMarker> getBindMarkers() {
    	return markers;
    }
    
    public static class Builder {
    	private StringBuilder stringBuilder;

    	private List<Object> partials;
    	private List<BindMarker> markers;
    	
    	public Builder() {
    		this.stringBuilder = new StringBuilder();
    		this.partials = new LinkedList<>();
    		this.markers = new LinkedList<>();
		}
    	
    	public Builder add(String... partials) {
    		for (String partial : partials) {
    			stringBuilder.append(partial);
    		}

    		return this;
    	}
    	
    	public Builder add(BindMarker marker) {
    		flushStringBuilder();

    		partials.add(marker);
    		markers.add(marker);

    		return this;
    	}

    	/**
    	 * Concatenate the preparedDom to this builder
    	 * 
    	 * @param preparedDom
    	 * @return this {@link PreparedDom.Builder}
    	 */
    	public Builder add(PreparedDom preparedDom) {
    		return add(preparedDom.getPreparedPartials().toArray());
    	}

    	/**
    	 * Partials can be type of {@link String} or {@link BindMarker}
    	 * 
    	 * @param partials
    	 * @return this {@link PreparedDom.Builder}
    	 */
    	public Builder add(Object... partials) {
    		for (Object partial : partials) {
    			if (partial instanceof BindMarker) {
    				add((BindMarker) partial);
    			} else if (partial instanceof String) {
    				add((String) partial);
    			} else {
    				throw new IllegalArgumentException();
    			}
    		}

			return this;
    	}
    	
    	/**
    	 * Flush the text in StringBuilder to partial
    	 */
    	private void flushStringBuilder() {
    		if (stringBuilder.length() > 0) {
    			partials.add(stringBuilder.toString());
    			stringBuilder = new StringBuilder();
    		}
    	}
    	
    	public PreparedDom build() {
    		flushStringBuilder();
    		return new PreparedDom(partials, markers);
    	}
    }

}
