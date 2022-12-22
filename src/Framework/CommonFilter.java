/**
 * Copyright(c) 2019 All rights reserved by JU Consulting
 */
package Framework;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public interface CommonFilter extends Runnable{
    public void connectOutputTo(CommonFilter filter, int portNo) throws IOException;
    public void connectInputTo(CommonFilter filter, int portNo) throws IOException;
    public PipedInputStream getPipedInputStream(int portNo);
    public PipedOutputStream getPipedOutputStream(int portNo);
}
