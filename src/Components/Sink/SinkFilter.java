/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Sink;

import java.io.FileWriter;
import java.io.IOException;

import Framework.CommonFilterImpl;

public class SinkFilter extends CommonFilterImpl{
    private String sinkFile;
    private int portNo;
    
    public SinkFilter(String outputFile, int portNo) {
        this.sinkFile = outputFile;
        this.portNo = portNo;
    }
    @Override
    public boolean specificComputationForFilter() throws IOException {
        int byte_read;
        FileWriter fw = new FileWriter(this.sinkFile);
        while(true) {
            byte_read = in.get(portNo).read(); 
            if (byte_read == -1) {
            	 fw.close();
                 System.out.print( "::Filtering is finished; Output file is created.\n" );  
                 return true;
            }
            fw.write((char)byte_read);
        }   
    }
}
