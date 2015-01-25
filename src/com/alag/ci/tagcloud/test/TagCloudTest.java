package com.alag.ci.tagcloud.test;

import java.io.*;
import java.util.*;

import com.alag.ci.tagcloud.*;
import com.alag.ci.tagcloud.impl.*;
import junit.framework.TestCase;

public class TagCloudTest extends TestCase {
	public void testTagCloud() throws Exception {
		String firstString = "binary";
		int numSizes = 3;
		String fontPrefix = "font-size: ";
		
		List<TagCloudElement> l = new ArrayList<TagCloudElement>();
		l.add(new TagCloudElementImpl("tagging",1));
		l.add(new TagCloudElementImpl("schema",3));
		l.add(new TagCloudElementImpl("denormalized",1));
		l.add(new TagCloudElementImpl("database",2));
		l.add(new TagCloudElementImpl(firstString,1));
		l.add(new TagCloudElementImpl("normalized",1));
		
		FontSizeComputationStrategy strategy = new LinearFontSizeComputationStrategy(numSizes,fontPrefix);
		TagCloud cloudLinear = new TagCloudImpl(l,strategy);
		System.out.println(cloudLinear);
		
		strategy = new LogFontSizeComputationStrategy(numSizes,fontPrefix);
		TagCloud cloudLog = new TagCloudImpl(l,strategy);
		System.out.println(cloudLog);
		
		// write to file
		String fileName = "testTagCloudChap2.html";
		writeToFile(fileName,cloudLinear);
	}
	
	private static void writeToFile(String fileName, TagCloud cloud) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
		VisualizeTagCloudDecorator decorator = new HTMLTagCloudDecorator();
		out.write(decorator.decorateTagCloud(cloud));
		out.close();
	}
}
