package com.timsmeet.spring;

import java.io.InputStream;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.springframework.core.io.Resource;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;

public class ColumnSensingReplacementDataSetLoader extends AbstractDataSetLoader {
    
    @Override
    protected IDataSet createDataSet(Resource resource) throws Exception {
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
            return createReplacementLmodFilteredDataSet(builder.build(inputStream));
        } finally {
            if(inputStream != null) {
                inputStream.close();
            }
        }
    }
 
    private IDataSet createReplacementLmodFilteredDataSet(FlatXmlDataSet dataSet) {
        ReplacementDataSet replacementDataSet = new ReplacementDataSet(dataSet);
         
        //Configure the replacement dataset to replace '[null]' strings with null.
        replacementDataSet.addReplacementObject("[null]", null);
        
        return replacementDataSet;
    }

}
