/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import java.io.EOFException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;

public abstract class CommonFilterImpl implements CommonFilter {
	private int numOfInputStream = 2;
	private int numOfOutputStream = 2;
	
	protected ArrayList<PipedInputStream> in = new ArrayList<>();
	protected ArrayList<PipedOutputStream> out = new ArrayList<>();
	
	public CommonFilterImpl() {
		for(int i = 0; i < numOfInputStream; i++) in.add(new PipedInputStream());
		for(int i = 0; i < numOfOutputStream; i++) out.add(new PipedOutputStream());
	}
	
	public void connectOutputTo(CommonFilter nextFilter, int portNo) throws IOException {
		out.get(portNo).connect(nextFilter.getPipedInputStream(portNo));
	}
	public void connectInputTo(CommonFilter previousFilter, int portNo) throws IOException {
		in.get(portNo).connect(previousFilter.getPipedOutputStream(portNo));
	}
	public PipedInputStream getPipedInputStream(int portNo) { return in.get(portNo); }
	public PipedOutputStream getPipedOutputStream(int portNo) { return out.get(portNo); }
	
	abstract public boolean specificComputationForFilter() throws IOException;
	// Implementation defined in Runnable interface for thread
	public void run() {
		try {
			specificComputationForFilter();
		} catch (IOException e) {
			if (e instanceof EOFException) return;
			else System.out.println(e);
		} finally { closePorts(); }
	}
	
	private void closePorts() {
		try {
			for(int i = 0; i < numOfInputStream; i++) in.get(i).close();
			for(int i = 0; i < numOfOutputStream; i++) out.get(i).close();
		}
		catch (IOException e) { e.printStackTrace(); }
	}
}
