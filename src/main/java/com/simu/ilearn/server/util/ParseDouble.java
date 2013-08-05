package com.simu.ilearn.server.util;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.DoubleCellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

public class ParseDouble extends CellProcessorAdaptor implements StringCellProcessor {
    public ParseDouble() {
        super();
    }

    public ParseDouble(final DoubleCellProcessor next) {
        super(next);
    }

    public Object execute(final Object value, final CsvContext context) {
        validateInputNotNull(value, context);

        final Double result;
        if( value instanceof Double ) {
            result = (Double) value;
        } else if( value instanceof String ) {
            try {
                String cleanedValue = ((String) value).trim().replace(".", "");
                cleanedValue = cleanedValue.replace(",", ".");
                result = new Double(cleanedValue);
            }
            catch(final NumberFormatException e) {
                throw new SuperCsvCellProcessorException(String.format("'%s' could not be parsed as a Double", value),
                        context, this, e);
            }
        } else {
            final String actualClassName = value.getClass().getName();
            throw new SuperCsvCellProcessorException(String.format(
                    "the input value should be of type Double or String but is of type %s", actualClassName), context, this);
        }

        return next.execute(result, context);
    }
}
