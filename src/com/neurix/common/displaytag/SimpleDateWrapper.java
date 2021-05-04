package com.neurix.common.displaytag;

import org.apache.commons.lang.time.FastDateFormat;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import javax.servlet.jsp.PageContext;
import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 20/02/13
 * Time: 18:41
 * To change this template use File | Settings | File Templates.
 */
public class SimpleDateWrapper implements DisplaytagColumnDecorator {

    private FastDateFormat dateFormat = FastDateFormat.getInstance("dd-MM-yyyy");

    public Object decorate(Object o, PageContext pageContext, MediaTypeEnum mediaTypeEnum) throws DecoratorException {
        if (o!=null) {
            Date date = (Date)o;
            return this.dateFormat.format(date.getTime());
        } else {
            return o;
        }
    }
}
