package com.juc.concurrency.test2;

public class concurrencyTest3 {
//    public boolean processRow(StepMetaInterface smi, StepDataInterface sdi) throws KettleException {
//        String firstparam;
//        RowMetaInterface inputRowMeta = getInputRowMeta();
//        if (first) {
//
//
//    /* TODO: Your code here. (Using info fields)
//
//    FieldHelper infoField = get(Fields.Info, "info_field_name");
//
//    RowSet infoStream = findInfoRowSet("info_stream_tag");
//
//    Object[] infoRow = null;
//
//    int infoRowCount = 0;
//
//    // Read all rows from info step before calling getRow() method, which returns first row from any
//    // input rowset. As rowMeta for info and input steps varies getRow() can lead to errors.
//    while((infoRow = getRowFrom(infoStream)) != null){
//
//      // do something with info data
//      infoRowCount++;
//    }
//    */
//
//            //ValueMetaInterface customer = inputRowMeta.searchValueMeta("message");
//            messageIndex = getInputRowMeta().indexOfValue(getParameter("message"));
//            if (messageIndex<0) {
//                throw new KettleException("message field not found in the input row, check parameter 'Message'!");
//            }
//            first = false;
//        }
//
//
//        Object[] r = getRow();
//
//        if (r == null) {
//            setOutputDone();
//            return false;
//        }
//        Long year = inputRowMeta.getBinary(r, yearIndex);
//
//        // It is always safest to call createOutputRow() to ensure that your output row's Object[] is large
//        // enough to handle any new fields you are creating in this step.
//        r = createOutputRow(r, data.outputRowMeta.size());
//
//  /* TODO: Your code here. (See Sample)
//
//  // Get the value from an input field
//  String foobar = get(Fields.In, "a_fieldname").getString(r);
//
//  foobar += "bar";
//
//  // Set a value in a new output field
//  get(Fields.Out, "output_fieldname").setValue(r, foobar);
//
//  */
//        // Send the row on to the next step.
//        putRow(data.outputRowMeta, r);
//
//        return true;
//    }

}
