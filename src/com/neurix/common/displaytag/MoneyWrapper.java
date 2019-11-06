package com.neurix.common.displaytag;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import javax.servlet.jsp.PageContext;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Ferdi on 17/09/2014.
 */
public class MoneyWrapper implements DisplaytagColumnDecorator {

    @Override
    public Object decorate(Object o, PageContext pageContext, MediaTypeEnum mediaTypeEnum) throws DecoratorException {

        BigDecimal money = (BigDecimal)o;

        NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMANY);
        DecimalFormat df = (DecimalFormat)nf;
        df.applyPattern("###,###.##");
        df.format(money);

        return df.format(money);
    }
}
